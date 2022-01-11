package com.dwarfeng.familyhelper.assets.impl.handler;

import com.dwarfeng.familyhelper.assets.impl.exception.FileException;
import com.dwarfeng.familyhelper.assets.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.assets.sdk.util.Constants;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCover;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCoverUploadInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemCoverInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Poac;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.familyhelper.assets.stack.exception.*;
import com.dwarfeng.familyhelper.assets.stack.handler.ItemCoverOperateHandler;
import com.dwarfeng.familyhelper.assets.stack.service.ItemCoverInfoMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.ItemMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.PoacMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.UserMaintainService;
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class ItemCoverOperateHandlerImpl implements ItemCoverOperateHandler {

    private final UserMaintainService userMaintainService;
    private final ItemCoverInfoMaintainService itemCoverInfoMaintainService;
    private final ItemMaintainService itemMaintainService;
    private final PoacMaintainService poacMaintainService;
    private final FtpHandler ftpHandler;

    private final KeyFetcher<LongIdKey> keyFetcher;

    public ItemCoverOperateHandlerImpl(
            UserMaintainService userMaintainService,
            ItemCoverInfoMaintainService itemCoverInfoMaintainService,
            ItemMaintainService itemMaintainService,
            PoacMaintainService poacMaintainService,
            FtpHandler ftpHandler,
            KeyFetcher<LongIdKey> keyFetcher
    ) {
        this.userMaintainService = userMaintainService;
        this.itemCoverInfoMaintainService = itemCoverInfoMaintainService;
        this.itemMaintainService = itemMaintainService;
        this.poacMaintainService = poacMaintainService;
        this.ftpHandler = ftpHandler;
        this.keyFetcher = keyFetcher;
    }

    @Override
    public ItemCover downloadItemCover(StringIdKey userKey, LongIdKey itemCoverKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认项目封面存在。
            makeSureItemCoverExists(itemCoverKey);

            // 3. 获取项目封面对应的项目，并确认用户有权限操作项目。
            ItemCoverInfo itemCoverInfo = itemCoverInfoMaintainService.get(itemCoverKey);
            makeSureUserPermittedForItem(userKey, itemCoverInfo.getItemKey());

            // 4. 下载项目封面。
            byte[] content = ftpHandler.getFileContent(
                    new String[]{FtpConstants.PATH_ITEM_COVER}, getFileName(itemCoverKey)
            );

            // 5. 拼接 ItemCover 并返回。
            return new ItemCover(itemCoverInfo.getOriginName(), content);
        } catch (FileException e) {
            throw new ItemCoverTransportException(e);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void uploadItemCover(StringIdKey userKey, ItemCoverUploadInfo itemCoverUploadInfo) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认项目封面所属的项目存在。
            LongIdKey itemKey = itemCoverUploadInfo.getItemKey();
            makeSureItemExists(itemKey);

            // 3. 确认用户有权限操作项目。
            makeSureUserPermittedForItem(userKey, itemKey);

            // 4. 分配主键。
            LongIdKey itemCoverKey = keyFetcher.fetchKey();

            // 5. 项目封面内容并存储（覆盖）。
            byte[] content = itemCoverUploadInfo.getContent();
            ftpHandler.storeFile(new String[]{FtpConstants.PATH_ITEM_COVER}, getFileName(itemCoverKey), content);

            // 6. 根据 itemCoverUploadInfo 构造 ItemCoverInfo，插入或更新。
            Date currentDate = new Date();
            // 映射属性。
            ItemCoverInfo itemCoverInfo = new ItemCoverInfo();
            itemCoverInfo.setKey(itemCoverKey);
            itemCoverInfo.setItemKey(itemKey);
            itemCoverInfo.setOriginName(itemCoverUploadInfo.getOriginName());
            itemCoverInfo.setLength(itemCoverUploadInfo.getContent().length);
            itemCoverInfo.setCreateDate(currentDate);
            itemCoverInfo.setModifiedDate(currentDate);
            itemCoverInfo.setRemark("通过 familyhelper-assets 服务上传/更新项目封面");
            // 查找当前 index 最大的 ItemCoverInfo 并且设置 index 为 最大的 index + 1。
            int index = itemCoverInfoMaintainService.lookup(
                    ItemCoverInfoMaintainService.CHILD_FOR_ITEM_INDEX_DESC, new Object[]{itemKey}, new PagingInfo(0, 1)
            ).getData().stream().findFirst().map(i -> i.getIndex() + 1).orElse(0);
            itemCoverInfo.setIndex(index);
            itemCoverInfoMaintainService.insertOrUpdate(itemCoverInfo);
        } catch (FileException e) {
            throw new ItemCoverTransportException(e);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeItemCover(StringIdKey userKey, LongIdKey itemCoverKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认项目封面存在。
            makeSureItemCoverExists(itemCoverKey);

            // 3. 获取项目封面对应的项目，并确认用户有权限操作项目。
            ItemCoverInfo itemCoverInfo = itemCoverInfoMaintainService.get(itemCoverKey);
            makeSureUserPermittedForItem(userKey, itemCoverInfo.getItemKey());

            // 4. 如果存在 ItemCover 文件，则删除。
            if (ftpHandler.existsFile(new String[]{FtpConstants.PATH_ITEM_COVER}, getFileName(itemCoverKey))) {
                ftpHandler.deleteFile(new String[]{FtpConstants.PATH_ITEM_COVER}, getFileName(itemCoverKey));
            }

            // 5. 如果存在 ItemCoverInfo 实体，则删除。
            itemCoverInfoMaintainService.deleteIfExists(itemCoverKey);
        } catch (FileException e) {
            throw new ItemCoverTransportException(e);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private String getFileName(LongIdKey itemCoverKey) {
        return Long.toString(itemCoverKey.getLongId());
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

    private void makeSureItemCoverExists(LongIdKey itemCoverKey) throws HandlerException {
        try {
            if (!itemCoverInfoMaintainService.exists(itemCoverKey)) {
                throw new ItemCoverNotExistsException(itemCoverKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    private void makeSureUserPermittedForItem(StringIdKey userKey, LongIdKey itemKey) throws HandlerException {
        try {
            // 1. 查找指定的银行卡是否绑定账本，如果不绑定账本，则抛出银行卡状态异常。
            Item item = itemMaintainService.get(itemKey);
            if (Objects.isNull(item.getAssetCatalogKey())) {
                throw new IllegalItemStateException(itemKey);
            }

            // 2. 取出银行卡的账本外键，判断用户是否拥有该账本的权限。
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
}
