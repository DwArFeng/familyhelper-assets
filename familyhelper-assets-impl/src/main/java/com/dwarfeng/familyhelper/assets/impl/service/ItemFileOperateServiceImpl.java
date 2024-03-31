package com.dwarfeng.familyhelper.assets.impl.service;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.*;
import com.dwarfeng.familyhelper.assets.stack.handler.ItemFileOperateHandler;
import com.dwarfeng.familyhelper.assets.stack.service.ItemFileOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class ItemFileOperateServiceImpl implements ItemFileOperateService {

    private final ItemFileOperateHandler itemFileOperateHandler;

    private final ServiceExceptionMapper sem;

    public ItemFileOperateServiceImpl(ItemFileOperateHandler itemFileOperateHandler, ServiceExceptionMapper sem) {
        this.itemFileOperateHandler = itemFileOperateHandler;
        this.sem = sem;
    }

    @Override
    public ItemFile downloadItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws ServiceException {
        try {
            return itemFileOperateHandler.downloadItemFile(userKey, itemFileKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载项目文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public ItemFileStream downloadItemFileStream(StringIdKey userKey, LongIdKey itemFileKey) throws ServiceException {
        try {
            return itemFileOperateHandler.downloadItemFileStream(userKey, itemFileKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载项目文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadItemFile(StringIdKey userKey, ItemFileUploadInfo uploadInfo) throws ServiceException {
        try {
            itemFileOperateHandler.uploadItemFile(userKey, uploadInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传项目文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadItemFileStream(StringIdKey userKey, ItemFileStreamUploadInfo uploadInfo)
            throws ServiceException {
        try {
            itemFileOperateHandler.uploadItemFileStream(userKey, uploadInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传项目文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateItemFile(StringIdKey userKey, ItemFileUpdateInfo updateInfo) throws ServiceException {
        try {
            itemFileOperateHandler.updateItemFile(userKey, updateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新项目文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateItemFileStream(StringIdKey userKey, ItemFileStreamUpdateInfo updateInfo)
            throws ServiceException {
        try {
            itemFileOperateHandler.updateItemFileStream(userKey, updateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新项目文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws ServiceException {
        try {
            itemFileOperateHandler.removeItemFile(userKey, itemFileKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除项目文件时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
