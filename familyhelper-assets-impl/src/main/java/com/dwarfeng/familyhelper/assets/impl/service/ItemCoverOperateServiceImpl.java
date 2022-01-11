package com.dwarfeng.familyhelper.assets.impl.service;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCover;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCoverOrderUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCoverUploadInfo;
import com.dwarfeng.familyhelper.assets.stack.handler.ItemCoverOperateHandler;
import com.dwarfeng.familyhelper.assets.stack.service.ItemCoverOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class ItemCoverOperateServiceImpl implements ItemCoverOperateService {

    private final ItemCoverOperateHandler itemCoverOperateHandler;

    private final ServiceExceptionMapper sem;

    public ItemCoverOperateServiceImpl(ItemCoverOperateHandler itemCoverOperateHandler, ServiceExceptionMapper sem) {
        this.itemCoverOperateHandler = itemCoverOperateHandler;
        this.sem = sem;
    }

    @Override
    public ItemCover downloadItemCover(StringIdKey userKey, LongIdKey itemCoverKey) throws ServiceException {
        try {
            return itemCoverOperateHandler.downloadItemCover(userKey, itemCoverKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("下载项目封面时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void uploadItemCover(StringIdKey userKey, ItemCoverUploadInfo itemCoverUploadInfo) throws ServiceException {
        try {
            itemCoverOperateHandler.uploadItemCover(userKey, itemCoverUploadInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("上传项目封面时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removeItemCover(StringIdKey userKey, LongIdKey itemCoverKey) throws ServiceException {
        try {
            itemCoverOperateHandler.removeItemCover(userKey, itemCoverKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除项目封面时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void updateItemCoverOrder(StringIdKey userKey, ItemCoverOrderUpdateInfo itemCoverOrderUpdateInfo)
            throws ServiceException {
        try {
            itemCoverOperateHandler.updateItemCoverOrder(userKey, itemCoverOrderUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新项目封面的顺序时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
