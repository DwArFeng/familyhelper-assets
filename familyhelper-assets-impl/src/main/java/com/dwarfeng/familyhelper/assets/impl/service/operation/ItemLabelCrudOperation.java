package com.dwarfeng.familyhelper.assets.impl.service.operation;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemLabel;
import com.dwarfeng.familyhelper.assets.stack.cache.ItemLabelCache;
import com.dwarfeng.familyhelper.assets.stack.dao.ItemDao;
import com.dwarfeng.familyhelper.assets.stack.dao.ItemLabelDao;
import com.dwarfeng.familyhelper.assets.stack.service.ItemMaintainService;
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
public class ItemLabelCrudOperation implements BatchCrudOperation<StringIdKey, ItemLabel> {

    private final ItemLabelDao itemLabelDao;
    private final ItemLabelCache itemLabelCache;

    private final ItemDao itemDao;

    @Value("${cache.timeout.entity.item_label}")
    private long itemLabelTimeout;

    public ItemLabelCrudOperation(
            ItemLabelDao itemLabelDao, ItemLabelCache itemLabelCache, ItemDao itemDao
    ) {
        this.itemLabelDao = itemLabelDao;
        this.itemLabelCache = itemLabelCache;
        this.itemDao = itemDao;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return itemLabelCache.exists(key) || itemLabelDao.exists(key);
    }

    @Override
    public ItemLabel get(StringIdKey key) throws Exception {
        if (itemLabelCache.exists(key)) {
            return itemLabelCache.get(key);
        } else {
            if (!itemLabelDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            ItemLabel itemLabel = itemLabelDao.get(key);
            itemLabelCache.push(itemLabel, itemLabelTimeout);
            return itemLabel;
        }
    }

    @Override
    public StringIdKey insert(ItemLabel itemLabel) throws Exception {
        itemLabelCache.push(itemLabel, itemLabelTimeout);
        return itemLabelDao.insert(itemLabel);
    }

    @Override
    public void update(ItemLabel itemLabel) throws Exception {
        itemLabelCache.push(itemLabel, itemLabelTimeout);
        itemLabelDao.update(itemLabel);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        //查找并清除所有相关的失效模式关联。
        List<LongIdKey> itemKeys = itemDao.lookup(ItemMaintainService.CHILD_FOR_LABEL, new Object[]{key})
                .stream().map(Item::getKey).collect(Collectors.toList());
        itemLabelDao.batchDeleteItemRelations(key, itemKeys);

        //删除 ItemLabel 自身。
        itemLabelCache.delete(key);
        itemLabelDao.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return itemLabelCache.allExists(keys) || itemLabelDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return itemLabelCache.nonExists(keys) && itemLabelDao.nonExists(keys);
    }

    @Override
    public List<ItemLabel> batchGet(List<StringIdKey> keys) throws Exception {
        if (itemLabelCache.allExists(keys)) {
            return itemLabelCache.batchGet(keys);
        } else {
            if (!itemLabelDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<ItemLabel> itemLabels = itemLabelDao.batchGet(keys);
            itemLabelCache.batchPush(itemLabels, itemLabelTimeout);
            return itemLabels;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<ItemLabel> itemLabels) throws Exception {
        itemLabelCache.batchPush(itemLabels, itemLabelTimeout);
        return itemLabelDao.batchInsert(itemLabels);
    }

    @Override
    public void batchUpdate(List<ItemLabel> itemLabels) throws Exception {
        itemLabelCache.batchPush(itemLabels, itemLabelTimeout);
        itemLabelDao.batchUpdate(itemLabels);
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
