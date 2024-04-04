package com.dwarfeng.familyhelper.assets.impl.service.operation;

import com.dwarfeng.familyhelper.assets.impl.util.FtpConstants;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemFileInfo;
import com.dwarfeng.familyhelper.assets.stack.cache.ItemFileInfoCache;
import com.dwarfeng.familyhelper.assets.stack.dao.ItemFileInfoDao;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemFileInfoCrudOperation implements BatchCrudOperation<LongIdKey, ItemFileInfo> {

    private final ItemFileInfoDao itemFileInfoDao;
    private final ItemFileInfoCache itemFileInfoCache;

    private final FtpHandler ftpHandler;

    @Value("${cache.timeout.entity.item_file_info}")
    private long itemFileInfoTimeout;

    public ItemFileInfoCrudOperation(
            ItemFileInfoDao itemFileInfoDao, ItemFileInfoCache itemFileInfoCache,
            FtpHandler ftpHandler
    ) {
        this.itemFileInfoDao = itemFileInfoDao;
        this.itemFileInfoCache = itemFileInfoCache;
        this.ftpHandler = ftpHandler;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return itemFileInfoCache.exists(key) || itemFileInfoDao.exists(key);
    }

    @Override
    public ItemFileInfo get(LongIdKey key) throws Exception {
        if (itemFileInfoCache.exists(key)) {
            return itemFileInfoCache.get(key);
        } else {
            if (!itemFileInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            ItemFileInfo itemFileInfo = itemFileInfoDao.get(key);
            itemFileInfoCache.push(itemFileInfo, itemFileInfoTimeout);
            return itemFileInfo;
        }
    }

    @Override
    public LongIdKey insert(ItemFileInfo itemFileInfo) throws Exception {
        itemFileInfoCache.push(itemFileInfo, itemFileInfoTimeout);
        return itemFileInfoDao.insert(itemFileInfo);
    }

    @Override
    public void update(ItemFileInfo itemFileInfo) throws Exception {
        itemFileInfoCache.push(itemFileInfo, itemFileInfoTimeout);
        itemFileInfoDao.update(itemFileInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 如果存在票据文件，则删除票据文件。
        if (ftpHandler.existsFile(FtpConstants.PATH_ITEM_FILE, getFileName(key))) {
            ftpHandler.deleteFile(FtpConstants.PATH_ITEM_FILE, getFileName(key));
        }

        // 删除票据文件信息实体自身。
        itemFileInfoCache.delete(key);
        itemFileInfoDao.delete(key);
    }

    private String getFileName(LongIdKey itemFileKey) {
        return Long.toString(itemFileKey.getLongId());
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return itemFileInfoCache.allExists(keys) || itemFileInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return itemFileInfoCache.nonExists(keys) && itemFileInfoDao.nonExists(keys);
    }

    @Override
    public List<ItemFileInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (itemFileInfoCache.allExists(keys)) {
            return itemFileInfoCache.batchGet(keys);
        } else {
            if (!itemFileInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<ItemFileInfo> itemFileInfos = itemFileInfoDao.batchGet(keys);
            itemFileInfoCache.batchPush(itemFileInfos, itemFileInfoTimeout);
            return itemFileInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<ItemFileInfo> itemFileInfos) throws Exception {
        itemFileInfoCache.batchPush(itemFileInfos, itemFileInfoTimeout);
        return itemFileInfoDao.batchInsert(itemFileInfos);
    }

    @Override
    public void batchUpdate(List<ItemFileInfo> itemFileInfos) throws Exception {
        itemFileInfoCache.batchPush(itemFileInfos, itemFileInfoTimeout);
        itemFileInfoDao.batchUpdate(itemFileInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
