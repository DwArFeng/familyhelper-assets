package com.dwarfeng.familyhelper.assets.impl.handler;

import com.dwarfeng.familyhelper.assets.sdk.util.Constants;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemFileInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Poac;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.familyhelper.assets.stack.exception.*;
import com.dwarfeng.familyhelper.assets.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 操作处理器验证器。
 *
 * <p>
 * 为操作处理器提供公共的验证方法。
 *
 * @author DwArFeng
 * @since 1.0.5
 */
@Component
public class OperateHandlerValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperateHandlerValidator.class);

    private final AssetCatalogMaintainService assetCatalogMaintainService;
    private final ItemMaintainService itemMaintainService;
    private final ItemCoverInfoMaintainService itemCoverInfoMaintainService;
    private final ItemLabelMaintainService itemLabelMaintainService;
    private final ItemFileInfoMaintainService itemFileInfoMaintainService;
    private final UserMaintainService userMaintainService;
    private final PoacMaintainService poacMaintainService;

    public OperateHandlerValidator(
            AssetCatalogMaintainService assetCatalogMaintainService,
            ItemMaintainService itemMaintainService,
            ItemCoverInfoMaintainService itemCoverInfoMaintainService,
            ItemLabelMaintainService itemLabelMaintainService,
            ItemFileInfoMaintainService itemFileInfoMaintainService,
            UserMaintainService userMaintainService,
            PoacMaintainService poacMaintainService
    ) {
        this.assetCatalogMaintainService = assetCatalogMaintainService;
        this.itemMaintainService = itemMaintainService;
        this.itemCoverInfoMaintainService = itemCoverInfoMaintainService;
        this.itemLabelMaintainService = itemLabelMaintainService;
        this.itemFileInfoMaintainService = itemFileInfoMaintainService;
        this.userMaintainService = userMaintainService;
        this.poacMaintainService = poacMaintainService;
    }

    public void makeSureAssetCatalogExists(LongIdKey assetCatalogKey) throws HandlerException {
        try {
            if (Objects.isNull(assetCatalogKey) || !assetCatalogMaintainService.exists(assetCatalogKey)) {
                throw new AssetCatalogNotExistsException(assetCatalogKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureItemExists(LongIdKey itemKey) throws HandlerException {
        try {
            if (Objects.isNull(itemKey) || !itemMaintainService.exists(itemKey)) {
                throw new ItemNotExistsException(itemKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureItemCoverExists(LongIdKey itemCoverKey) throws HandlerException {
        try {
            if (!itemCoverInfoMaintainService.exists(itemCoverKey)) {
                throw new ItemCoverNotExistsException(itemCoverKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureLabelExists(List<StringIdKey> labelKeys) throws HandlerException {
        try {
            if (itemLabelMaintainService.allExists(labelKeys)) {
                return;
            }
            for (StringIdKey labelKey : labelKeys) {
                if (!itemLabelMaintainService.exists(labelKey)) {
                    throw new ItemLabelNotExistsException(labelKey);
                }
            }
            LOGGER.warn("代码不应该执行到此处，应该是有 bug，请排查");
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureItemFileExists(LongIdKey itemFileKey) throws HandlerException {
        try {
            if (!itemFileInfoMaintainService.exists(itemFileKey)) {
                throw new ItemFileNotExistsException(itemFileKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (!userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserPermittedForAssetCatalog(StringIdKey userKey, LongIdKey assetCatalogKey)
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

    public void makeSureUserPermittedForItem(StringIdKey userKey, LongIdKey itemKey) throws HandlerException {
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

    public void makeSureUserPermittedForItemFileInfo(StringIdKey userKey, LongIdKey itemFileInfoKey)
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

    public void makeSureItemCoverHasSameItem(List<LongIdKey> itemCoverKeys) throws HandlerException {
        try {
            LongIdKey parent = itemCoverInfoMaintainService.get(itemCoverKeys.get(0)).getItemKey();
            if (Objects.isNull(parent)) {
                throw new IllegalItemCoverStateException(itemCoverKeys.get(0));
            }
            for (int i = 1; i < itemCoverKeys.size(); i++) {
                LongIdKey currKey = itemCoverInfoMaintainService.get(itemCoverKeys.get(i)).getItemKey();
                if (!Objects.equals(parent, currKey)) {
                    throw new IllegalItemCoverStateException(itemCoverKeys.get(i));
                }
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSurePermissionLevelValid(int permissionLevel) throws HandlerException {
        if (permissionLevel == Constants.PERMISSION_LEVEL_GUEST) {
            return;
        }
        if (permissionLevel == Constants.PERMISSION_LEVEL_MODIFIER) {
            return;
        }
        throw new InvalidPermissionLevelException(permissionLevel);
    }

    public void makeSureAssetCatalogIdenticalForAssetCatalog(LongIdKey parentKey, LongIdKey assetCatalogKey)
            throws HandlerException {
        try {
            // 如果 parentKey 为 null，则表示该项目是根项目，不需要进行任何判断。
            if (Objects.isNull(parentKey)) {
                return;
            }

            Item parentItem = itemMaintainService.get(parentKey);
            LongIdKey parentAssetCatalogKey = parentItem.getAssetCatalogKey();
            if (Objects.isNull(parentAssetCatalogKey)) {
                throw new IllegalItemStateException(parentKey);
            }
            if (!Objects.equals(parentAssetCatalogKey, assetCatalogKey)) {
                throw new AssetCatalogNotIdenticalException(parentAssetCatalogKey, assetCatalogKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureAssetCatalogIdenticalForAssetItem(LongIdKey parentKey, LongIdKey itemKey)
            throws HandlerException {
        try {
            Item item = itemMaintainService.get(itemKey);
            LongIdKey assetCatalogKey = item.getAssetCatalogKey();
            if (Objects.isNull(assetCatalogKey)) {
                throw new IllegalItemStateException(parentKey);
            }
            makeSureAssetCatalogIdenticalForAssetCatalog(parentKey, assetCatalogKey);
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
