package com.dwarfeng.familyhelper.assets.impl.service.operation;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemLabel;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemProperty;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.familyhelper.assets.stack.cache.ItemCache;
import com.dwarfeng.familyhelper.assets.stack.cache.ItemLabelCache;
import com.dwarfeng.familyhelper.assets.stack.cache.ItemPropertyCache;
import com.dwarfeng.familyhelper.assets.stack.dao.ItemDao;
import com.dwarfeng.familyhelper.assets.stack.dao.ItemLabelDao;
import com.dwarfeng.familyhelper.assets.stack.dao.ItemPropertyDao;
import com.dwarfeng.familyhelper.assets.stack.service.ItemLabelMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.ItemMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.ItemPropertyMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    @Value("${cache.timeout.entity.item}")
    private long itemTimeout;

    public ItemCrudOperation(
            ItemDao itemDao, ItemCache itemCache,
            ItemLabelDao itemLabelDao, ItemLabelCache itemLabelCache,
            ItemPropertyDao itemPropertyDao, ItemPropertyCache itemPropertyCache
    ) {
        this.itemDao = itemDao;
        this.itemCache = itemCache;
        this.itemLabelDao = itemLabelDao;
        this.itemLabelCache = itemLabelCache;
        this.itemPropertyDao = itemPropertyDao;
        this.itemPropertyCache = itemPropertyCache;
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
        // 查找并清除所有相关的父节点关联。
        List<Item> childItems = itemDao.lookup(ItemMaintainService.CHILD_FOR_PARENT, new Object[]{key});
        childItems.forEach(i -> i.setParentKey(null));
        itemCache.batchDelete(childItems.stream().map(Item::getKey).collect(Collectors.toList()));
        itemDao.batchUpdate(childItems);

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

        // 删除 Item 自身。
        itemCache.delete(key);
        itemDao.delete(key);
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
