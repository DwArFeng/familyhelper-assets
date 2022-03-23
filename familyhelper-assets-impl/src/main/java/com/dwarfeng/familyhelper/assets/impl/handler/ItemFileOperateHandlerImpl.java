package com.dwarfeng.familyhelper.assets.impl.handler;

import com.dwarfeng.familyhelper.assets.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemFile;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemFileUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemFileUploadInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemFileInfo;
import com.dwarfeng.familyhelper.assets.stack.handler.ItemFileOperateHandler;
import com.dwarfeng.familyhelper.assets.stack.service.ItemFileInfoMaintainService;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ItemFileOperateHandlerImpl implements ItemFileOperateHandler {

    private final ItemFileInfoMaintainService itemFileInfoMaintainService;
    private final FtpHandler ftpHandler;

    private final KeyFetcher<LongIdKey> keyFetcher;

    private final OperateHandlerValidator operateHandlerValidator;

    public ItemFileOperateHandlerImpl(
            ItemFileInfoMaintainService itemFileInfoMaintainService,
            FtpHandler ftpHandler,
            KeyFetcher<LongIdKey> keyFetcher,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.itemFileInfoMaintainService = itemFileInfoMaintainService;
        this.ftpHandler = ftpHandler;
        this.keyFetcher = keyFetcher;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public ItemFile downloadItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目文件存在。
            operateHandlerValidator.makeSureItemFileExists(itemFileKey);

            // 3. 获取项目文件对应的项目，并确认用户有权限操作项目。
            ItemFileInfo itemFileInfo = itemFileInfoMaintainService.get(itemFileKey);
            operateHandlerValidator.makeSureUserInspectPermittedForItem(userKey, itemFileInfo.getItemKey());

            // 4. 下载项目文件。
            byte[] content = ftpHandler.getFileContent(
                    new String[]{FtpConstants.PATH_ITEM_FILE}, getFileName(itemFileKey)
            );

            // 5. 更新文件的查看时间。
            itemFileInfo.setInspectedDate(new Date());
            itemFileInfoMaintainService.update(itemFileInfo);

            // 6. 拼接 ItemFile 并返回。
            return new ItemFile(itemFileInfo.getOriginName(), content);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void uploadItemFile(StringIdKey userKey, ItemFileUploadInfo itemFileUploadInfo) throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目文件所属的项目存在。
            LongIdKey itemKey = itemFileUploadInfo.getItemKey();
            operateHandlerValidator.makeSureItemExists(itemKey);

            // 3. 确认用户有权限操作项目。
            operateHandlerValidator.makeSureUserModifyPermittedForItem(userKey, itemKey);

            // 4. 分配主键。
            LongIdKey itemFileKey = keyFetcher.fetchKey();

            // 5. 项目文件内容并存储（覆盖）。
            byte[] content = itemFileUploadInfo.getContent();
            ftpHandler.storeFile(new String[]{FtpConstants.PATH_ITEM_FILE}, getFileName(itemFileKey), content);

            // 6. 根据 itemFileUploadInfo 构造 ItemFileInfo，插入或更新。
            Date currentDate = new Date();
            // 映射属性。
            ItemFileInfo itemFileInfo = new ItemFileInfo();
            itemFileInfo.setKey(itemFileKey);
            itemFileInfo.setItemKey(itemKey);
            itemFileInfo.setOriginName(itemFileUploadInfo.getOriginName());
            itemFileInfo.setLength(itemFileUploadInfo.getContent().length);
            itemFileInfo.setCreatedDate(currentDate);
            itemFileInfo.setModifiedDate(currentDate);
            itemFileInfo.setInspectedDate(currentDate);
            itemFileInfo.setRemark("通过 familyhelper-assets 服务上传/更新项目文件");
            itemFileInfoMaintainService.insertOrUpdate(itemFileInfo);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateItemFile(StringIdKey userKey, ItemFileUpdateInfo itemFileUpdateInfo) throws HandlerException {
        try {
            LongIdKey itemFileKey = itemFileUpdateInfo.getItemFileKey();

            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目文件信息存在。
            operateHandlerValidator.makeSureItemFileExists(itemFileKey);

            // 3. 确认用户有权限操作项目文件信息。
            operateHandlerValidator.makeSureUserModifyPermittedForItemFileInfo(userKey, itemFileKey);

            // 4. 项目文件内容并存储（覆盖）。
            byte[] content = itemFileUpdateInfo.getContent();
            ftpHandler.storeFile(new String[]{FtpConstants.PATH_ITEM_FILE}, getFileName(itemFileKey), content);

            // 5. 根据 itemFileUpdateInfo 更新字段。
            ItemFileInfo itemFileInfo = itemFileInfoMaintainService.get(itemFileKey);
            itemFileInfo.setOriginName(itemFileUpdateInfo.getOriginName());
            itemFileInfo.setLength(content.length);
            itemFileInfo.setModifiedDate(new Date());
            itemFileInfoMaintainService.update(itemFileInfo);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目文件存在。
            operateHandlerValidator.makeSureItemFileExists(itemFileKey);

            // 3. 获取项目文件对应的项目，并确认用户有权限操作项目。
            ItemFileInfo itemFileInfo = itemFileInfoMaintainService.get(itemFileKey);
            operateHandlerValidator.makeSureUserModifyPermittedForItem(userKey, itemFileInfo.getItemKey());

            // 4. 如果存在 ItemFile 文件，则删除。
            if (ftpHandler.existsFile(new String[]{FtpConstants.PATH_ITEM_FILE}, getFileName(itemFileKey))) {
                ftpHandler.deleteFile(new String[]{FtpConstants.PATH_ITEM_FILE}, getFileName(itemFileKey));
            }

            // 5. 如果存在 ItemFileInfo 实体，则删除。
            itemFileInfoMaintainService.deleteIfExists(itemFileKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private String getFileName(LongIdKey itemFileKey) {
        return Long.toString(itemFileKey.getLongId());
    }
}
