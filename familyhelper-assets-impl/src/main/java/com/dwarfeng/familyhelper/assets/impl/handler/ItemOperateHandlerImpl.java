package com.dwarfeng.familyhelper.assets.impl.handler;

import com.dwarfeng.familyhelper.assets.sdk.util.Constants;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCreateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemLabel;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Poac;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.familyhelper.assets.stack.exception.*;
import com.dwarfeng.familyhelper.assets.stack.handler.ItemOperateHandler;
import com.dwarfeng.familyhelper.assets.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ItemOperateHandlerImpl implements ItemOperateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemOperateHandlerImpl.class);

    private final UserMaintainService userMaintainService;
    private final AssetCatalogMaintainService assetCatalogMaintainService;
    private final ItemMaintainService itemMaintainService;
    private final ItemLabelMaintainService itemLabelMaintainService;
    private final PoacMaintainService poacMaintainService;

    public ItemOperateHandlerImpl(
            UserMaintainService userMaintainService,
            AssetCatalogMaintainService assetCatalogMaintainService,
            ItemMaintainService itemMaintainService,
            ItemLabelMaintainService itemLabelMaintainService,
            PoacMaintainService poacMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.assetCatalogMaintainService = assetCatalogMaintainService;
        this.itemMaintainService = itemMaintainService;
        this.itemLabelMaintainService = itemLabelMaintainService;
        this.poacMaintainService = poacMaintainService;
    }

    @Override
    public LongIdKey createItem(StringIdKey userKey, ItemCreateInfo itemCreateInfo) throws HandlerException {
        try {
            LongIdKey assetCatalogKey = itemCreateInfo.getAssetCatalogKey();
            LongIdKey parentKey = itemCreateInfo.getParentKey();
            List<StringIdKey> labelKeys = itemCreateInfo.getLabelKeys();

            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认资产目录存在。
            makeSureAssetCatalogExists(assetCatalogKey);

            // 3. 确认父项目存在。
            if (Objects.nonNull(parentKey)) {
                makeSureItemExists(parentKey);
            }

            // 4. 确认关联的项目标签全部存在。
            makeSureLabelExists(labelKeys);

            // 5. 确认用户有权限操作指定的资产目录。
            makeSureUserPermittedForAssetCatalog(userKey, assetCatalogKey);

            // 6. 根据 itemCreateInfo 以及创建的规则组合 项目 实体。
            Date currentDate = new Date();
            Date scrappedDate = null;
            if (Objects.equals(itemCreateInfo.getLifeCycle(), Constants.LIFE_CYCLE_SCRAPPED)) {
                scrappedDate = currentDate;
            }
            Item item = new Item(
                    null, parentKey, assetCatalogKey, itemCreateInfo.getName(), itemCreateInfo.getType(), currentDate,
                    null, scrappedDate, itemCreateInfo.getLifeCycle(), itemCreateInfo.getRemark()
            );
            LongIdKey itemKey = itemMaintainService.insert(item);

            // 7. 关联 项目标签 实体。
            itemMaintainService.batchAddLabelRelations(itemKey, labelKeys);

            // 8. 资产目录对应项目数量增加。
            AssetCatalog assetCatalog = assetCatalogMaintainService.get(assetCatalogKey);
            assetCatalog.setItemCount(assetCatalog.getItemCount() + 1);
            assetCatalogMaintainService.update(assetCatalog);

            // 8. 返回 项目 实体的主键。
            return itemKey;
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void updateItem(StringIdKey userKey, ItemUpdateInfo itemUpdateInfo) throws HandlerException {
        try {
            LongIdKey assetCatalogKey = itemUpdateInfo.getAssetCatalogKey();
            LongIdKey itemKey = itemUpdateInfo.getItemKey();
            LongIdKey parentKey = itemUpdateInfo.getParentKey();
            List<StringIdKey> labelKeys = itemUpdateInfo.getLabelKeys();

            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认资产目录存在。
            makeSureAssetCatalogExists(assetCatalogKey);

            // 3. 确认项目存在。
            makeSureItemExists(itemKey);

            // 4. 确认父项目存在。
            if (Objects.nonNull(parentKey)) {
                makeSureItemExists(parentKey);
            }

            // 5. 确认关联的项目标签全部存在。
            makeSureLabelExists(labelKeys);

            // 6. 确认用户有权限操作指定的资产目录。
            makeSureUserPermittedForAssetCatalog(userKey, assetCatalogKey);

            // 7. 根据 itemUpdateInfo 以及更新的规则设置 项目 实体。
            Item item = itemMaintainService.get(itemKey);
            item.setParentKey(parentKey);
            item.setName(itemUpdateInfo.getName());
            item.setItemType(itemUpdateInfo.getType());
            item.setLifeCycle(itemUpdateInfo.getLifeCycle());
            item.setRemark(itemUpdateInfo.getRemark());
            Date currentDate = new Date();
            Date scrappedDate = null;
            if (Objects.equals(itemUpdateInfo.getLifeCycle(), Constants.LIFE_CYCLE_SCRAPPED)) {
                scrappedDate = currentDate;
            }
            item.setModifiedDate(currentDate);
            item.setScrappedDate(scrappedDate);

            // 8. 更新 项目 实体。
            itemMaintainService.update(item);

            // 9. 解除 item 当前的所有标签，并重新关联为 itemUpdateInfo 中指定的标签。
            List<StringIdKey> labelKeysToDelete = itemLabelMaintainService.lookup(
                    ItemLabelMaintainService.CHILD_FOR_ITEM, new Object[]{itemKey}
            ).getData().stream().map(ItemLabel::getKey).collect(Collectors.toList());
            itemMaintainService.batchDeleteLabelRelations(itemKey, labelKeysToDelete);
            itemMaintainService.batchAddLabelRelations(itemKey, labelKeys);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void removeItem(StringIdKey userKey, LongIdKey itemKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            makeSureUserExists(userKey);

            // 2. 确认项目存在。
            makeSureItemExists(itemKey);

            // 3. 确认用户有权限操作指定的银行卡。
            makeSureUserPermittedForItem(userKey, itemKey);

            // 4. 资产目录对应项目数量减少。
            Item item = itemMaintainService.get(itemKey);
            AssetCatalog assetCatalog = assetCatalogMaintainService.get(item.getAssetCatalogKey());
            assetCatalog.setItemCount(assetCatalog.getItemCount() + 1);
            assetCatalogMaintainService.update(assetCatalog);

            // 5. 存在删除指定的项目。
            itemMaintainService.deleteIfExists(itemKey);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
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

    private void makeSureAssetCatalogExists(LongIdKey assetCatalogKey) throws HandlerException {
        try {
            if (Objects.isNull(assetCatalogKey) || !assetCatalogMaintainService.exists(assetCatalogKey)) {
                throw new AssetCatalogNotExistsException(assetCatalogKey);
            }
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

    private void makeSureLabelExists(List<StringIdKey> labelKeys) throws HandlerException {
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
}
