package com.dwarfeng.familyhelper.assets.impl.cache;

import com.dwarfeng.familyhelper.assets.sdk.bean.entity.FastJsonItemProperty;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemProperty;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.familyhelper.assets.stack.cache.ItemPropertyCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ItemPropertyCacheImpl implements ItemPropertyCache {

    private final RedisBatchBaseCache<ItemPropertyKey, ItemProperty, FastJsonItemProperty> itemPropertyBatchBaseDelegate;

    public ItemPropertyCacheImpl(
            RedisBatchBaseCache<ItemPropertyKey, ItemProperty, FastJsonItemProperty> itemPropertyBatchBaseDelegate
    ) {
        this.itemPropertyBatchBaseDelegate = itemPropertyBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(ItemPropertyKey key) throws CacheException {
        return itemPropertyBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public ItemProperty get(ItemPropertyKey key) throws CacheException {
        return itemPropertyBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(ItemProperty value, long timeout) throws CacheException {
        itemPropertyBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(ItemPropertyKey key) throws CacheException {
        itemPropertyBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        itemPropertyBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<ItemPropertyKey> keys) throws CacheException {
        return itemPropertyBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<ItemPropertyKey> keys) throws CacheException {
        return itemPropertyBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<ItemProperty> batchGet(@SkipRecord List<ItemPropertyKey> keys) throws CacheException {
        return itemPropertyBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<ItemProperty> entities, long timeout) throws CacheException {
        itemPropertyBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<ItemPropertyKey> keys) throws CacheException {
        itemPropertyBatchBaseDelegate.batchDelete(keys);
    }
}
