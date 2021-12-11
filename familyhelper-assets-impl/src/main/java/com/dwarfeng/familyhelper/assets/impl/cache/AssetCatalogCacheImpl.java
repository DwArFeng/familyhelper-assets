package com.dwarfeng.familyhelper.assets.impl.cache;

import com.dwarfeng.familyhelper.assets.sdk.bean.entity.FastJsonAssetCatalog;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.familyhelper.assets.stack.cache.AssetCatalogCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AssetCatalogCacheImpl implements AssetCatalogCache {

    private final RedisBatchBaseCache<LongIdKey, AssetCatalog, FastJsonAssetCatalog> assetCatalogBatchBaseDelegate;

    public AssetCatalogCacheImpl(
            RedisBatchBaseCache<LongIdKey, AssetCatalog, FastJsonAssetCatalog> assetCatalogBatchBaseDelegate
    ) {
        this.assetCatalogBatchBaseDelegate = assetCatalogBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(LongIdKey key) throws CacheException {
        return assetCatalogBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public AssetCatalog get(LongIdKey key) throws CacheException {
        return assetCatalogBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(AssetCatalog value, long timeout) throws CacheException {
        assetCatalogBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(LongIdKey key) throws CacheException {
        assetCatalogBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        assetCatalogBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<LongIdKey> keys) throws CacheException {
        return assetCatalogBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<LongIdKey> keys) throws CacheException {
        return assetCatalogBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<AssetCatalog> batchGet(@SkipRecord List<LongIdKey> keys) throws CacheException {
        return assetCatalogBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<AssetCatalog> entities, long timeout) throws CacheException {
        assetCatalogBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<LongIdKey> keys) throws CacheException {
        assetCatalogBatchBaseDelegate.batchDelete(keys);
    }
}
