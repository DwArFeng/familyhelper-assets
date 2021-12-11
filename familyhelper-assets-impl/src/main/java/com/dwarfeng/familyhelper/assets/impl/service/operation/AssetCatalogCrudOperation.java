package com.dwarfeng.familyhelper.assets.impl.service.operation;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Poac;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.familyhelper.assets.stack.cache.AssetCatalogCache;
import com.dwarfeng.familyhelper.assets.stack.cache.ItemCache;
import com.dwarfeng.familyhelper.assets.stack.cache.PoacCache;
import com.dwarfeng.familyhelper.assets.stack.dao.AssetCatalogDao;
import com.dwarfeng.familyhelper.assets.stack.dao.ItemDao;
import com.dwarfeng.familyhelper.assets.stack.dao.PoacDao;
import com.dwarfeng.familyhelper.assets.stack.service.ItemMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.PoacMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssetCatalogCrudOperation implements BatchCrudOperation<LongIdKey, AssetCatalog> {

    private final AssetCatalogDao assetCatalogDao;
    private final ItemDao itemDao;
    private final PoacDao poacDao;

    private final AssetCatalogCache assetCatalogCache;
    private final ItemCache itemCache;
    private final PoacCache poacCache;

    @Value("${cache.timeout.entity.asset_catalog}")
    private long assetCatalogTimeout;

    public AssetCatalogCrudOperation(
            AssetCatalogDao assetCatalogDao, ItemDao itemDao, PoacDao poacDao,
            AssetCatalogCache assetCatalogCache, ItemCache itemCache, PoacCache poacCache
    ) {
        this.assetCatalogDao = assetCatalogDao;
        this.itemDao = itemDao;
        this.poacDao = poacDao;
        this.assetCatalogCache = assetCatalogCache;
        this.itemCache = itemCache;
        this.poacCache = poacCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return assetCatalogCache.exists(key) || assetCatalogDao.exists(key);
    }

    @Override
    public AssetCatalog get(LongIdKey key) throws Exception {
        if (assetCatalogCache.exists(key)) {
            return assetCatalogCache.get(key);
        } else {
            if (!assetCatalogDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            AssetCatalog assetCatalog = assetCatalogDao.get(key);
            assetCatalogCache.push(assetCatalog, assetCatalogTimeout);
            return assetCatalog;
        }
    }

    @Override
    public LongIdKey insert(AssetCatalog assetCatalog) throws Exception {
        assetCatalogCache.push(assetCatalog, assetCatalogTimeout);
        return assetCatalogDao.insert(assetCatalog);
    }

    @Override
    public void update(AssetCatalog assetCatalog) throws Exception {
        assetCatalogCache.push(assetCatalog, assetCatalogTimeout);
        assetCatalogDao.update(assetCatalog);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 找到与账本相关的银行卡。
        List<LongIdKey> itemKeys = itemDao.lookup(
                ItemMaintainService.CHILD_FOR_ASSET_CATALOG, new Object[]{key}
        ).stream().map(Item::getKey).collect(Collectors.toList());
        // 删除与账本相关的银行卡。
        itemCache.batchDelete(itemKeys);
        itemDao.batchDelete(itemKeys);

        // 删除与账本相关的账本权限。
        List<PoacKey> poacKeys = poacDao.lookup(PoacMaintainService.CHILD_FOR_ASSET_CATALOG, new Object[]{key})
                .stream().map(Poac::getKey).collect(Collectors.toList());
        poacCache.batchDelete(poacKeys);
        poacDao.batchDelete(poacKeys);

        // 删除账本实体自身。
        assetCatalogCache.delete(key);
        assetCatalogDao.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return assetCatalogCache.allExists(keys) || assetCatalogDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return assetCatalogCache.nonExists(keys) && assetCatalogDao.nonExists(keys);
    }

    @Override
    public List<AssetCatalog> batchGet(List<LongIdKey> keys) throws Exception {
        if (assetCatalogCache.allExists(keys)) {
            return assetCatalogCache.batchGet(keys);
        } else {
            if (!assetCatalogDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<AssetCatalog> assetCatalogs = assetCatalogDao.batchGet(keys);
            assetCatalogCache.batchPush(assetCatalogs, assetCatalogTimeout);
            return assetCatalogs;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<AssetCatalog> assetCatalogs) throws Exception {
        assetCatalogCache.batchPush(assetCatalogs, assetCatalogTimeout);
        return assetCatalogDao.batchInsert(assetCatalogs);
    }

    @Override
    public void batchUpdate(List<AssetCatalog> assetCatalogs) throws Exception {
        assetCatalogCache.batchPush(assetCatalogs, assetCatalogTimeout);
        assetCatalogDao.batchUpdate(assetCatalogs);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
