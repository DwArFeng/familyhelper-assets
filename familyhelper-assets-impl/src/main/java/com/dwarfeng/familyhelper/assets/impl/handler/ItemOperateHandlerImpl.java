package com.dwarfeng.familyhelper.assets.impl.handler;

import com.dwarfeng.familyhelper.assets.sdk.util.Constants;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCreateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemLabel;
import com.dwarfeng.familyhelper.assets.stack.handler.ItemOperateHandler;
import com.dwarfeng.familyhelper.assets.stack.service.AssetCatalogMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.ItemLabelMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.ItemMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ItemOperateHandlerImpl implements ItemOperateHandler {

    private final AssetCatalogMaintainService assetCatalogMaintainService;
    private final ItemMaintainService itemMaintainService;
    private final ItemLabelMaintainService itemLabelMaintainService;

    private final OperateHandlerValidator operateHandlerValidator;

    public ItemOperateHandlerImpl(
            AssetCatalogMaintainService assetCatalogMaintainService,
            ItemMaintainService itemMaintainService,
            ItemLabelMaintainService itemLabelMaintainService,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.assetCatalogMaintainService = assetCatalogMaintainService;
        this.itemMaintainService = itemMaintainService;
        this.itemLabelMaintainService = itemLabelMaintainService;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public LongIdKey createItem(StringIdKey userKey, ItemCreateInfo itemCreateInfo) throws HandlerException {
        try {
            LongIdKey assetCatalogKey = itemCreateInfo.getAssetCatalogKey();
            LongIdKey parentKey = itemCreateInfo.getParentKey();
            List<StringIdKey> labelKeys = itemCreateInfo.getLabelKeys();

            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认资产目录存在。
            operateHandlerValidator.makeSureAssetCatalogExists(assetCatalogKey);

            // 3. 确认父项目存在。
            if (Objects.nonNull(parentKey)) {
                operateHandlerValidator.makeSureItemExists(parentKey);
            }

            // 4. 确认关联的项目标签全部存在。
            operateHandlerValidator.makeSureLabelExists(labelKeys);

            // 5. 确认用户有权限操作指定的资产目录。
            operateHandlerValidator.makeSureUserModifyPermittedForAssetCatalog(userKey, assetCatalogKey);

            // 6. 确认项目与父项目的资产目录存在。
            operateHandlerValidator.makeSureAssetCatalogIdenticalForAssetCatalog(parentKey, assetCatalogKey);

            // 7. 根据 itemCreateInfo 以及创建的规则组合 项目 实体。
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

            // 8. 关联 项目标签 实体。
            itemMaintainService.batchAddLabelRelations(itemKey, labelKeys);

            // 9. 资产目录对应项目数量增加。
            AssetCatalog assetCatalog = assetCatalogMaintainService.get(assetCatalogKey);
            assetCatalog.setItemCount(assetCatalog.getItemCount() + 1);
            assetCatalogMaintainService.update(assetCatalog);

            // 10. 返回 项目 实体的主键。
            return itemKey;
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void updateItem(StringIdKey userKey, ItemUpdateInfo itemUpdateInfo) throws HandlerException {
        try {
            LongIdKey itemKey = itemUpdateInfo.getItemKey();
            LongIdKey parentKey = itemUpdateInfo.getParentKey();
            List<StringIdKey> labelKeys = itemUpdateInfo.getLabelKeys();

            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目存在。
            operateHandlerValidator.makeSureItemExists(itemKey);

            // 3. 确认父项目存在。
            if (Objects.nonNull(parentKey)) {
                operateHandlerValidator.makeSureItemExists(parentKey);
            }

            // 4. 确认关联的项目标签全部存在。
            operateHandlerValidator.makeSureLabelExists(labelKeys);

            // 5. 确认用户有权限操作指定的项目。
            operateHandlerValidator.makeSureUserModifyPermittedForItem(userKey, itemKey);

            // 6. 确认项目与父项目的资产目录存在。
            operateHandlerValidator.makeSureAssetCatalogIdenticalForAssetItem(parentKey, itemKey);

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
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeItem(StringIdKey userKey, LongIdKey itemKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认项目存在。
            operateHandlerValidator.makeSureItemExists(itemKey);

            // 3. 确认用户有权限操作指定的银行卡。
            operateHandlerValidator.makeSureUserModifyPermittedForItem(userKey, itemKey);

            // 4. 资产目录对应项目数量减少。
            Item item = itemMaintainService.get(itemKey);
            AssetCatalog assetCatalog = assetCatalogMaintainService.get(item.getAssetCatalogKey());
            assetCatalog.setItemCount(assetCatalog.getItemCount() - 1);
            assetCatalogMaintainService.update(assetCatalog);

            // 5. 存在删除指定的项目。
            itemMaintainService.deleteIfExists(itemKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
