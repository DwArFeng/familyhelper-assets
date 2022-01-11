package com.dwarfeng.familyhelper.assets.impl.handler;

import com.dwarfeng.familyhelper.assets.impl.exception.FileDeleteException;
import com.dwarfeng.familyhelper.assets.impl.exception.FileRetrieveException;
import com.dwarfeng.familyhelper.assets.impl.exception.FileStoreException;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class FtpHandlerImpl implements FtpHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FtpHandlerImpl.class);

    /**
     * FTP协议所使用的标准字符集。
     */
    private static final String STANDARD_FILE_NAME_CHARSET = "ISO-8859-1";

    private final ThreadPoolTaskScheduler scheduler;

    private ScheduledFuture<?> noopSendTaskFuture;

    private FTPClient ftpClient;

    @Value("${ftp.host}")
    private String ftpHost;
    @Value("${ftp.port}")
    private int ftpPort;
    @Value("${ftp.username}")
    private String ftpUserName;
    @Value("${ftp.password}")
    private String ftpPassword;
    @Value("${ftp.server_charset}")
    private String serverCharset;
    @Value("${ftp.connect_timeout}")
    private int connectTimeout;
    @Value("${ftp.noop_interval}")
    private long noopInterval;

    private final Lock lock = new ReentrantLock();

    public FtpHandlerImpl(ThreadPoolTaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void init() throws Exception {
        lock.lock();
        try {
            //检查参数是否合法。
            if (connectTimeout <= 1000) {
                throw new IllegalArgumentException("配置ftp.connect_timeout的值太小，应该大于1000");
            }
            if (noopInterval >= connectTimeout) {
                throw new IllegalArgumentException("配置ftp.noop_interval的值太大，应该小于ftp.connect_timeout");
            }

            ftpClient = new FTPClient();
            // 设置FTP服务器为本地被动模式。
            ftpClient.enterLocalPassiveMode();
            // 设置连接超时时间。
            // 连接的超时时间一定要在调用connect方法之前设置。
            ftpClient.setConnectTimeout(connectTimeout);
            // 连接FTP服务器,设置IP及端口
            ftpClient.connect(ftpHost, ftpPort);
            // 设置用户名和密码
            ftpClient.login(ftpUserName, ftpPassword);
            // 设置文件传输为模式为binary。
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 添加noop周期发送计划。
            noopSendTaskFuture = scheduler.scheduleWithFixedDelay(new NoopSendTask(), noopInterval);

            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                LOGGER.error("未连接到FTP，用户名或密码错误，将抛出异常并退出程序...");
                throw new IllegalStateException("未连接到FTP，用户名或密码错误");
            } else {
                LOGGER.info("FTP连接成功");
            }
        } finally {
            lock.unlock();
        }
    }

    @PreDestroy
    public void dispose() {
        lock.lock();
        try {
            // 停止noop发送计划。
            noopSendTaskFuture.cancel(true);
            // FTP服务器登出。
            ftpClient.logout();
        } catch (Exception e) {
            LOGGER.error("FTP关闭失败", e);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    LOGGER.error("FTP关闭失败", e);
                }
            }
            lock.unlock();
        }
    }

    @Override
    @BehaviorAnalyse
    public boolean existsFile(String[] filePaths, String fileName) throws HandlerException {
        lock.lock();
        try {
            enterDirection(filePaths);
            FTPFile[] ftpFiles = ftpClient.listFiles(fileName);
            return Objects.nonNull(ftpFiles) && ftpFiles.length > 0;
        } catch (Exception e) {
            throw new HandlerException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    @BehaviorAnalyse
    public void storeFile(String[] filePaths, String fileName, @SkipRecord byte[] content) throws HandlerException {
        lock.lock();
        try (ByteArrayInputStream bin = new ByteArrayInputStream(content)) {
            enterDirection(filePaths);
            if (!ftpClient.storeFile(serverFileNameEncoding(fileName), bin)) {
                throw new FileStoreException(
                        humanReadableFileNameEncoding(ftpClient.printWorkingDirectory()) + '/' + fileName
                );
            }
        } catch (Exception e) {
            throw new HandlerException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    public byte[] getFileContent(String[] filePaths, String fileName) throws HandlerException {
        lock.lock();
        try (ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
            enterDirection(filePaths);
            if (!ftpClient.retrieveFile(serverFileNameEncoding(fileName), bout)) {
                throw new FileRetrieveException(
                        humanReadableFileNameEncoding(ftpClient.printWorkingDirectory()) + '/' + fileName
                );
            }
            bout.flush();
            return bout.toByteArray();
        } catch (Exception e) {
            throw new HandlerException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    @BehaviorAnalyse
    public void deleteFile(String[] filePaths, String fileName) throws HandlerException {
        lock.lock();
        try {
            enterDirection(filePaths);
            if (!ftpClient.deleteFile(serverFileNameEncoding(fileName))) {
                throw new FileDeleteException(
                        humanReadableFileNameEncoding(ftpClient.printWorkingDirectory()) + '/' + fileName
                );
            }
        } catch (Exception e) {
            throw new HandlerException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    @BehaviorAnalyse
    public void removeDirectory(String[] filePaths, String directoryName) throws HandlerException {
        lock.lock();
        try {
            enterDirection(filePaths);
            if (!ftpClient.removeDirectory(serverFileNameEncoding(directoryName))) {
                throw new FileDeleteException(
                        humanReadableFileNameEncoding(ftpClient.printWorkingDirectory()) + '/' + directoryName
                );
            }
        } catch (Exception e) {
            throw new HandlerException(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 按照 filePaths 依次打开指定的目录，如果目录不存在就创建。
     *
     * @param filePaths 指定的文件目录。
     * @throws IOException IO异常。
     */
    private void enterDirection(String[] filePaths) throws IOException {
        ftpClient.changeWorkingDirectory(serverFileNameEncoding("/"));
        for (String filePath : filePaths) {
            String adjustedFilePath = serverFileNameEncoding(filePath);
            boolean result = ftpClient.changeWorkingDirectory(adjustedFilePath);
            if (!result) {
                ftpClient.mkd(adjustedFilePath);
                ftpClient.changeWorkingDirectory(adjustedFilePath);
            }
        }
    }

    private String serverFileNameEncoding(String fileName) throws UnsupportedEncodingException {
        return new String(fileName.getBytes(serverCharset), STANDARD_FILE_NAME_CHARSET);
    }

    private String humanReadableFileNameEncoding(String fileName) throws UnsupportedEncodingException {
        return new String(fileName.getBytes(STANDARD_FILE_NAME_CHARSET), serverCharset);
    }

    private class NoopSendTask implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                LOGGER.debug("向 FTP 服务器 发送 NoOp 指令，以保持 FTP 服务器的正常连接...");
                ftpClient.sendNoOp();
            } catch (IOException e) {
                LOGGER.error("向 FTP 服务器发送 NoOp 指令失败，异常信息如下: ", e);
            } finally {
                lock.unlock();
            }
        }
    }
}
