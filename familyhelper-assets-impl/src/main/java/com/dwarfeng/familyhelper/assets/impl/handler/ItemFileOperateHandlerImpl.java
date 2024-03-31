package com.dwarfeng.familyhelper.assets.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.familyhelper.assets.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.assets.sdk.util.Constants;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.*;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemFileInfo;
import com.dwarfeng.familyhelper.assets.stack.handler.ItemFileOperateHandler;
import com.dwarfeng.familyhelper.assets.stack.service.ItemFileInfoMaintainService;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

@Component
public class ItemFileOperateHandlerImpl implements ItemFileOperateHandler {

    private final ItemFileInfoMaintainService itemFileInfoMaintainService;

    private final FtpHandler ftpHandler;

    private final KeyGenerator<LongIdKey> keyGenerator;

    private final OperateHandlerValidator operateHandlerValidator;

    public ItemFileOperateHandlerImpl(
            ItemFileInfoMaintainService itemFileInfoMaintainService,
            FtpHandler ftpHandler,
            KeyGenerator<LongIdKey> keyGenerator,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.itemFileInfoMaintainService = itemFileInfoMaintainService;
        this.ftpHandler = ftpHandler;
        this.keyGenerator = keyGenerator;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public ItemFile downloadItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认项目文件存在。
            operateHandlerValidator.makeSureItemFileExists(itemFileKey);

            // 获取项目文件对应的项目，并确认用户有权限操作项目。
            ItemFileInfo itemFileInfo = itemFileInfoMaintainService.get(itemFileKey);
            operateHandlerValidator.makeSureUserInspectPermittedForItem(userKey, itemFileInfo.getItemKey());

            // 下载项目文件。
            byte[] content = ftpHandler.retrieveFile(FtpConstants.PATH_ITEM_FILE, getFileName(itemFileKey));

            // 更新文件的查看时间。
            itemFileInfo.setInspectedDate(new Date());
            itemFileInfoMaintainService.update(itemFileInfo);

            // 拼接 ItemFile 并返回。
            return new ItemFile(itemFileInfo.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public ItemFileStream downloadItemFileStream(StringIdKey userKey, LongIdKey itemFileKey) throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认项目文件存在。
            operateHandlerValidator.makeSureItemFileExists(itemFileKey);

            // 获取项目文件对应的项目，并确认用户有权限操作项目。
            ItemFileInfo itemFileInfo = itemFileInfoMaintainService.get(itemFileKey);
            operateHandlerValidator.makeSureUserInspectPermittedForItem(userKey, itemFileInfo.getItemKey());

            // 获取项目文件的内容。
            InputStream content = ftpHandler.openInputStream(FtpConstants.PATH_ITEM_FILE, getFileName(itemFileKey));

            // 更新文件的查看时间。
            itemFileInfo.setInspectedDate(new Date());
            itemFileInfoMaintainService.update(itemFileInfo);

            // 拼接 ItemFileStream 并返回。
            return new ItemFileStream(itemFileInfo.getOriginName(), itemFileInfo.getLength(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("ExtractMethodRecommender")
    @Override
    public void uploadItemFile(StringIdKey userKey, ItemFileUploadInfo uploadInfo) throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认项目文件所属的项目存在。
            LongIdKey itemKey = uploadInfo.getItemKey();
            operateHandlerValidator.makeSureItemExists(itemKey);

            // 确认用户有权限操作项目。
            operateHandlerValidator.makeSureUserModifyPermittedForItem(userKey, itemKey);

            // 分配主键。
            LongIdKey itemFileKey = keyGenerator.generate();

            // 项目文件内容并存储（覆盖）。
            byte[] content = uploadInfo.getContent();
            ftpHandler.storeFile(FtpConstants.PATH_ITEM_FILE, getFileName(itemFileKey), content);

            // 根据 itemFileUploadInfo 构造 ItemFileInfo，插入或更新。
            Date currentDate = new Date();
            // 映射属性。
            ItemFileInfo itemFileInfo = new ItemFileInfo();
            itemFileInfo.setKey(itemFileKey);
            itemFileInfo.setItemKey(itemKey);
            itemFileInfo.setOriginName(uploadInfo.getOriginName());
            itemFileInfo.setLength(uploadInfo.getContent().length);
            itemFileInfo.setCreatedDate(currentDate);
            itemFileInfo.setModifiedDate(currentDate);
            itemFileInfo.setInspectedDate(currentDate);
            itemFileInfo.setRemark("通过 familyhelper-assets 服务上传/更新项目文件");
            itemFileInfoMaintainService.insertOrUpdate(itemFileInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("ExtractMethodRecommender")
    @Override
    public void uploadItemFileStream(StringIdKey userKey, ItemFileStreamUploadInfo uploadInfo) throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认项目文件所属的项目存在。
            LongIdKey itemKey = uploadInfo.getItemKey();
            operateHandlerValidator.makeSureItemExists(itemKey);

            // 确认用户有权限操作项目。
            operateHandlerValidator.makeSureUserModifyPermittedForItem(userKey, itemKey);

            // 分配主键。
            LongIdKey itemFileKey = keyGenerator.generate();

            // 项目文件内容并存储（覆盖）。
            InputStream cin = uploadInfo.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(
                    FtpConstants.PATH_ITEM_FILE, getFileName(itemFileKey)
            )) {
                IOUtil.trans(cin, fout, Constants.IO_TRANS_BUFFER_SIZE);
            }

            // 根据 itemFileStreamUploadInfo 构造 ItemFileInfo，插入或更新。
            Date currentDate = new Date();
            // 映射属性。
            ItemFileInfo itemFileInfo = new ItemFileInfo();
            itemFileInfo.setKey(itemFileKey);
            itemFileInfo.setItemKey(itemKey);
            itemFileInfo.setOriginName(uploadInfo.getOriginName());
            itemFileInfo.setLength(uploadInfo.getLength());
            itemFileInfo.setCreatedDate(currentDate);
            itemFileInfo.setModifiedDate(currentDate);
            itemFileInfo.setInspectedDate(currentDate);
            itemFileInfo.setRemark("通过 familyhelper-assets 服务上传/更新项目文件");
            itemFileInfoMaintainService.insertOrUpdate(itemFileInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void updateItemFile(StringIdKey userKey, ItemFileUpdateInfo updateInfo) throws HandlerException {
        try {
            LongIdKey itemFileKey = updateInfo.getItemFileKey();

            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认项目文件信息存在。
            operateHandlerValidator.makeSureItemFileExists(itemFileKey);

            // 确认用户有权限操作项目文件信息。
            operateHandlerValidator.makeSureUserModifyPermittedForItemFileInfo(userKey, itemFileKey);

            // 项目文件内容并存储（覆盖）。
            byte[] content = updateInfo.getContent();
            ftpHandler.storeFile(FtpConstants.PATH_ITEM_FILE, getFileName(itemFileKey), content);

            // 根据 itemFileUpdateInfo 更新字段。
            ItemFileInfo itemFileInfo = itemFileInfoMaintainService.get(itemFileKey);
            itemFileInfo.setOriginName(updateInfo.getOriginName());
            itemFileInfo.setLength(content.length);
            itemFileInfo.setModifiedDate(new Date());
            itemFileInfoMaintainService.update(itemFileInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void updateItemFileStream(StringIdKey userKey, ItemFileStreamUpdateInfo updateInfo) throws HandlerException {
        try {
            LongIdKey itemFileKey = updateInfo.getItemFileKey();

            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认项目文件信息存在。
            operateHandlerValidator.makeSureItemFileExists(itemFileKey);

            // 确认用户有权限操作项目文件信息。
            operateHandlerValidator.makeSureUserModifyPermittedForItemFileInfo(userKey, itemFileKey);

            // 项目文件内容并存储（覆盖）。
            InputStream cin = updateInfo.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(
                    FtpConstants.PATH_ITEM_FILE, getFileName(itemFileKey)
            )) {
                IOUtil.trans(cin, fout, Constants.IO_TRANS_BUFFER_SIZE);
            }

            // 根据 itemFileStreamUpdateInfo 更新字段。
            ItemFileInfo itemFileInfo = itemFileInfoMaintainService.get(itemFileKey);
            itemFileInfo.setOriginName(updateInfo.getOriginName());
            itemFileInfo.setLength(updateInfo.getLength());
            itemFileInfo.setModifiedDate(new Date());
            itemFileInfoMaintainService.update(itemFileInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认项目文件存在。
            operateHandlerValidator.makeSureItemFileExists(itemFileKey);

            // 获取项目文件对应的项目，并确认用户有权限操作项目。
            ItemFileInfo itemFileInfo = itemFileInfoMaintainService.get(itemFileKey);
            operateHandlerValidator.makeSureUserModifyPermittedForItem(userKey, itemFileInfo.getItemKey());

            // 如果存在 ItemFile 文件，则删除。
            if (ftpHandler.existsFile(FtpConstants.PATH_ITEM_FILE, getFileName(itemFileKey))) {
                ftpHandler.deleteFile(FtpConstants.PATH_ITEM_FILE, getFileName(itemFileKey));
            }

            // 如果存在 ItemFileInfo 实体，则删除。
            itemFileInfoMaintainService.deleteIfExists(itemFileKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private String getFileName(LongIdKey itemFileKey) {
        return Long.toString(itemFileKey.getLongId());
    }
}
