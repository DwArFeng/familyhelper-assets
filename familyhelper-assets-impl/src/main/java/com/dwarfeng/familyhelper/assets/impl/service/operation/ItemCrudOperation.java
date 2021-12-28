package com.dwarfeng.familyhelper.assets.impl.service.operation;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.*;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.familyhelper.assets.stack.cache.*;
import com.dwarfeng.familyhelper.assets.stack.dao.*;
import com.dwarfeng.familyhelper.assets.stack.service.*;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemCrudOperation implements BatchCrudOperation<LongIdKey, Item> {

    private final ItemDao itemDao;
    private final ItemCache itemCache;

    private final ItemLabelDao itemLabelDao;
    private final ItemLabelCache itemLabelCache;

    private final ItemPropertyDao itemPropertyDao;
    private final ItemPropertyCache itemPropertyCache;

    private final ItemCoverInfoDao itemCoverInfoDao;
    private final ItemCoverInfoCache itemCoverInfoCache;

    private final ItemFileInfoDao itemFileInfoDao;
    private final ItemFileInfoCache itemFileInfoCache;

    @Value("${cache.timeout.entity.item}")
    private long itemTimeout;

    public ItemCrudOperation(
            ItemDao itemDao, ItemCache itemCache,
            ItemLabelDao itemLabelDao, ItemLabelCache itemLabelCache,
            ItemPropertyDao itemPropertyDao, ItemPropertyCache itemPropertyCache,
            ItemCoverInfoDao itemCoverInfoDao, ItemCoverInfoCache itemCoverInfoCache,
            ItemFileInfoDao itemFileInfoDao, ItemFileInfoCache itemFileInfoCache
    ) {
        this.itemDao = itemDao;
        this.itemCache = itemCache;
        this.itemLabelDao = itemLabelDao;
        this.itemLabelCache = itemLabelCache;
        this.itemPropertyDao = itemPropertyDao;
        this.itemPropertyCache = itemPropertyCache;
        this.itemCoverInfoDao = itemCoverInfoDao;
        this.itemCoverInfoCache = itemCoverInfoCache;
        this.itemFileInfoDao = itemFileInfoDao;
        this.itemFileInfoCache = itemFileInfoCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return itemCache.exists(key) || itemDao.exists(key);
    }

    @Override
    public Item get(LongIdKey key) throws Exception {
        if (itemCache.exists(key)) {
            return itemCache.get(key);
        } else {
            if (!itemDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Item item = itemDao.get(key);
            itemCache.push(item, itemTimeout);
            return item;
        }
    }

    @Override
    public LongIdKey insert(Item item) throws Exception {
        itemCache.push(item, itemTimeout);
        return itemDao.insert(item);
    }

    @Override
    public void update(Item item) throws Exception {
        itemCache.push(item, itemTimeout);
        itemDao.update(item);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 递归寻找并删除 Item 自身的子孙节点。
        List<Item> descendantItems = new ArrayList<>();
        findDescendant(descendantItems, itemDao.get(key));
        descendantItems.forEach((item -> item.setParentKey(null)));
        itemDao.batchUpdate(descendantItems);
        List<LongIdKey> descendantItemKeys = descendantItems.stream().map(Item::getKey).collect(Collectors.toList());
        itemCache.batchDelete(descendantItemKeys);
        itemDao.batchDelete(descendantItemKeys);

        // 查找并清除所有相关的标签关联。
        List<StringIdKey> itemLabelKeys = itemLabelDao.lookup(
                ItemLabelMaintainService.CHILD_FOR_ITEM, new Object[]{key}
        ).stream().map(ItemLabel::getKey).collect(Collectors.toList());
        itemLabelCache.batchDelete(itemLabelKeys);
        itemDao.batchDeleteLabelRelations(key, itemLabelKeys);

        // 查找删除除所有相关的项目属性。
        List<ItemPropertyKey> itemPropertyKeys = itemPropertyDao.lookup(
                ItemPropertyMaintainService.ITEM_ID_EQUALS, new Object[]{key}
        ).stream().map(ItemProperty::getKey).collect(Collectors.toList());
        itemPropertyCache.batchDelete(itemPropertyKeys);
        itemPropertyDao.batchDelete(itemPropertyKeys);

        // 查找并删除所有相关的项目封面信息。
        List<LongIdKey> itemCoverInfoKeys = itemCoverInfoDao.lookup(
                ItemCoverInfoMaintainService.CHILD_FOR_ITEM, new Object[]{key}
        ).stream().map(ItemCoverInfo::getKey).collect(Collectors.toList());
        itemCoverInfoCache.batchDelete(itemCoverInfoKeys);
        itemCoverInfoDao.batchDelete(itemCoverInfoKeys);

        // 查找并删除所有相关的项目文件信息。
        List<LongIdKey> itemFileInfoKeys = itemFileInfoDao.lookup(
                ItemFileInfoMaintainService.CHILD_FOR_ITEM, new Object[]{key}
        ).stream().map(ItemFileInfo::getKey).collect(Collectors.toList());
        itemFileInfoCache.batchDelete(itemFileInfoKeys);
        itemFileInfoDao.batchDelete(itemFileInfoKeys);

        // 删除 Item 自身。
        itemCache.delete(key);
        itemDao.delete(key);
    }

    private void findDescendant(List<Item> descendantItemKeys, Item item) throws Exception {
        List<Item> childItems = itemDao.lookup(ItemMaintainService.CHILD_FOR_PARENT, new Object[]{item.getKey()});
        for (Item childItem : childItems) {
            descendantItemKeys.add(childItem);
            findDescendant(descendantItemKeys, childItem);
        }
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return itemCache.allExists(keys) || itemDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return itemCache.nonExists(keys) && itemDao.nonExists(keys);
    }

    @Override
    public List<Item> batchGet(List<LongIdKey> keys) throws Exception {
        if (itemCache.allExists(keys)) {
            return itemCache.batchGet(keys);
        } else {
            if (!itemDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Item> items = itemDao.batchGet(keys);
            itemCache.batchPush(items, itemTimeout);
            return items;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<Item> items) throws Exception {
        itemCache.batchPush(items, itemTimeout);
        return itemDao.batchInsert(items);
    }

    @Override
    public void batchUpdate(List<Item> items) throws Exception {
        itemCache.batchPush(items, itemTimeout);
        itemDao.batchUpdate(items);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
