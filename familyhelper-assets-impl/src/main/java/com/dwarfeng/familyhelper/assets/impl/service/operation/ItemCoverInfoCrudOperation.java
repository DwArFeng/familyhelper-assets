package com.dwarfeng.familyhelper.assets.impl.service.operation;

import com.dwarfeng.familyhelper.assets.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemCoverInfo;
import com.dwarfeng.familyhelper.assets.stack.cache.ItemCoverInfoCache;
import com.dwarfeng.familyhelper.assets.stack.dao.ItemCoverInfoDao;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemCoverInfoCrudOperation implements BatchCrudOperation<LongIdKey, ItemCoverInfo> {

    private final ItemCoverInfoDao itemCoverInfoDao;
    private final ItemCoverInfoCache itemCoverInfoCache;

    private final FtpHandler ftpHandler;

    @Value("${cache.timeout.entity.item_cover_info}")
    private long itemCoverInfoTimeout;

    public ItemCoverInfoCrudOperation(
            ItemCoverInfoDao itemCoverInfoDao, ItemCoverInfoCache itemCoverInfoCache,
            FtpHandler ftpHandler
    ) {
        this.itemCoverInfoDao = itemCoverInfoDao;
        this.itemCoverInfoCache = itemCoverInfoCache;
        this.ftpHandler = ftpHandler;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return itemCoverInfoCache.exists(key) || itemCoverInfoDao.exists(key);
    }

    @Override
    public ItemCoverInfo get(LongIdKey key) throws Exception {
        if (itemCoverInfoCache.exists(key)) {
            return itemCoverInfoCache.get(key);
        } else {
            if (!itemCoverInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            ItemCoverInfo itemCoverInfo = itemCoverInfoDao.get(key);
            itemCoverInfoCache.push(itemCoverInfo, itemCoverInfoTimeout);
            return itemCoverInfo;
        }
    }

    @Override
    public LongIdKey insert(ItemCoverInfo itemCoverInfo) throws Exception {
        itemCoverInfoCache.push(itemCoverInfo, itemCoverInfoTimeout);
        return itemCoverInfoDao.insert(itemCoverInfo);
    }

    @Override
    public void update(ItemCoverInfo itemCoverInfo) throws Exception {
        itemCoverInfoCache.push(itemCoverInfo, itemCoverInfoTimeout);
        itemCoverInfoDao.update(itemCoverInfo);
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void delete(LongIdKey key) throws Exception {
        // 如果存在票据文件，则删除票据文件。
        if (ftpHandler.existsFile(FtpConstants.PATH_ITEM_COVER, getCoverName(key))) {
            ftpHandler.deleteFile(FtpConstants.PATH_ITEM_COVER, getCoverName(key));
        }

        // 删除票据文件信息实体自身。
        itemCoverInfoCache.delete(key);
        itemCoverInfoDao.delete(key);
    }

    private String getCoverName(LongIdKey itemCoverKey) {
        return Long.toString(itemCoverKey.getLongId());
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return itemCoverInfoCache.allExists(keys) || itemCoverInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return itemCoverInfoCache.nonExists(keys) && itemCoverInfoDao.nonExists(keys);
    }

    @Override
    public List<ItemCoverInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (itemCoverInfoCache.allExists(keys)) {
            return itemCoverInfoCache.batchGet(keys);
        } else {
            if (!itemCoverInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<ItemCoverInfo> itemCoverInfos = itemCoverInfoDao.batchGet(keys);
            itemCoverInfoCache.batchPush(itemCoverInfos, itemCoverInfoTimeout);
            return itemCoverInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<ItemCoverInfo> itemCoverInfos) throws Exception {
        itemCoverInfoCache.batchPush(itemCoverInfos, itemCoverInfoTimeout);
        return itemCoverInfoDao.batchInsert(itemCoverInfos);
    }

    @Override
    public void batchUpdate(List<ItemCoverInfo> itemCoverInfos) throws Exception {
        itemCoverInfoCache.batchPush(itemCoverInfos, itemCoverInfoTimeout);
        itemCoverInfoDao.batchUpdate(itemCoverInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
