package com.dwarfeng.familyhelper.assets.impl.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * FTP 处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface FtpHandler extends Handler {

    boolean existsFile(String[] filePaths, String fileName) throws HandlerException;

    void storeFile(String[] filePaths, String fileName, byte[] content) throws HandlerException;

    byte[] getFileContent(String[] filePaths, String fileName) throws HandlerException;

    void deleteFile(String[] filePaths, String fileName) throws HandlerException;

    void removeDirectory(String[] filePaths, String directoryName) throws HandlerException;
}
