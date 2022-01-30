package com.dwarfeng.familyhelper.assets.impl.handler;

import com.dwarfeng.familyhelper.assets.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.assets.sdk.util.Constants;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemFile;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemFileUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemFileUploadInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemFileInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Poac;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.familyhelper.assets.stack.exception.*;
import com.dwarfeng.familyhelper.assets.stack.handler.ItemFileOperateHandler;
import com.dwarfeng.familyhelper.assets.stack.service.ItemFileInfoMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.ItemMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.PoacMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.UserMaintainService;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class ItemFileOperateHandlerImpl implements ItemFileOperateHandler {

    private final UserMaintainService userMaintainService;
    private final ItemFileInfoMaintainService itemFileInfoMaintainService;
    private final ItemMaintainService itemMaintainService;
    private final PoacMaintainService poacMaintainService;
    private final FtpHandler ftpHandler;

    private final KeyFetcher<LongIdKey> keyFetcher;

    public ItemFileOperateHandlerImpl(
            UserMaintainService userMaintainService,
            ItemFileInfoMaintainService itemFileInfoMaintainService,
            ItemMaintainService itemMaintainService,
            PoacMaintainService poacMaintainService,
            FtpHandler ftpHandler,
            KeyFetcher<LongIdKey> keyFetcher
    ) {
        this.userMaintainService = userMaintainService;
        this.itemFileInfoMaintainService = itemFileInfoMaintainService;
        this.itemMaintainService = itemMaintainService;
        this.poacMaintainService = poacMaintainService;
        this.ftpHandler = ftpHandler;
        this.keyFetcher = keyFetcher;
    }

    @Override
    public ItemFile downloadItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认项目文件存在。
            makeSureItemFileExists(itemFileKey);

            // 3. 获取项目文件对应的项目，并确认用户有权限操作项目。
            ItemFileInfo itemFileInfo = itemFileInfoMaintainService.get(itemFileKey);
            makeSureUserPermittedForItem(userKey, itemFileInfo.getItemKey());

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
            makeSureUserExists(userKey);

            // 2. 确认项目文件所属的项目存在。
            LongIdKey itemKey = itemFileUploadInfo.getItemKey();
            makeSureItemExists(itemKey);

            // 3. 确认用户有权限操作项目。
            makeSureUserPermittedForItem(userKey, itemKey);

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
            makeSureUserExists(userKey);

            // 2. 确认项目文件信息存在。
            makeSureItemFileExists(itemFileKey);

            // 3. 确认用户有权限操作项目文件信息。
            makeSureUserPermittedForItemFileInfo(userKey, itemFileKey);

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
            makeSureUserExists(userKey);

            // 2. 确认项目文件存在。
            makeSureItemFileExists(itemFileKey);

            // 3. 获取项目文件对应的项目，并确认用户有权限操作项目。
            ItemFileInfo itemFileInfo = itemFileInfoMaintainService.get(itemFileKey);
            makeSureUserPermittedForItem(userKey, itemFileInfo.getItemKey());

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

    private void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (!userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureItemFileExists(LongIdKey itemFileKey) throws HandlerException {
        try {
            if (!itemFileInfoMaintainService.exists(itemFileKey)) {
                throw new ItemFileNotExistsException(itemFileKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserPermittedForItem(StringIdKey userKey, LongIdKey itemKey) throws HandlerException {
        try {
            // 1. 查找指定的项目文件信息是否绑定项目，如果不绑定项目，则抛出项目文件信息状态异常。
            Item item = itemMaintainService.get(itemKey);
            if (Objects.isNull(item.getAssetCatalogKey())) {
                throw new IllegalItemStateException(itemKey);
            }

            // 2. 取出项目文件信息的项目外键，判断用户是否拥有该项目的权限。
            makeSureUserPermittedForAssetCatalog(userKey, item.getAssetCatalogKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void makeSureUserPermittedForAssetCatalog(StringIdKey userKey, LongIdKey assetCatalogKey)
            throws HandlerException {
        try {
            // 1. 构造 Poac 主键。
            PoacKey poacKey = new PoacKey(assetCatalogKey.getLongId(), userKey.getStringId());

            // 2. 查看 Poac 实体是否存在，如果不存在，则没有权限。
            if (!poacMaintainService.exists(poacKey)) {
                throw new UserNotPermittedException(userKey, assetCatalogKey);
            }

            // 3. 查看 Poac.permissionLevel 是否为 Poac.PERMISSION_LEVEL_OWNER 或 Poac.PERMISSION_LEVEL_MODIFIER，
            // 如果不是，则没有权限。
            Poac poac = poacMaintainService.get(poacKey);
            if (Objects.equals(poac.getPermissionLevel(), Constants.PERMISSION_LEVEL_OWNER)) {
                return;
            }
            if (Objects.equals(poac.getPermissionLevel(), Constants.PERMISSION_LEVEL_MODIFIER)) {
                return;
            }
            throw new UserNotPermittedException(userKey, assetCatalogKey);
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureItemExists(LongIdKey itemKey) throws HandlerException {
        try {
            if (Objects.isNull(itemKey) || !itemMaintainService.exists(itemKey)) {
                throw new ItemNotExistsException(itemKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserPermittedForItemFileInfo(StringIdKey userKey, LongIdKey itemFileInfoKey)
            throws HandlerException {
        try {
            // 1. 查找指定的项目文件信息是否绑定项目，如果不绑定项目，则抛出项目文件信息状态异常。
            ItemFileInfo itemFileInfo = itemFileInfoMaintainService.get(itemFileInfoKey);
            if (Objects.isNull(itemFileInfo.getItemKey())) {
                throw new IllegalItemFileStateException(itemFileInfoKey);
            }

            // 2. 取出项目文件信息的项目外键，判断用户是否拥有该项目的权限。
            makeSureUserPermittedForItem(userKey, itemFileInfo.getItemKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
