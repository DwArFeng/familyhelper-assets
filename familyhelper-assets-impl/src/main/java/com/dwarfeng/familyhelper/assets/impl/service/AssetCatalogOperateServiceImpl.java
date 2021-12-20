package com.dwarfeng.familyhelper.assets.impl.service;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.AssetCatalogCreateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.AssetCatalogUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.PermissionRemoveInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.PermissionUpsertInfo;
import com.dwarfeng.familyhelper.assets.stack.handler.AssetCatalogOperateHandler;
import com.dwarfeng.familyhelper.assets.stack.service.AssetCatalogOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class AssetCatalogOperateServiceImpl implements AssetCatalogOperateService {

    private final AssetCatalogOperateHandler assetCatalogOperateHandler;

    private final ServiceExceptionMapper sem;

    public AssetCatalogOperateServiceImpl(
            AssetCatalogOperateHandler assetCatalogOperateHandler, ServiceExceptionMapper sem
    ) {
        this.assetCatalogOperateHandler = assetCatalogOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createAssetCatalog(StringIdKey userKey, AssetCatalogCreateInfo assetCatalogCreateInfo)
            throws ServiceException {
        try {
            return assetCatalogOperateHandler.createAssetCatalog(userKey, assetCatalogCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("创建资产目录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void updateAssetCatalog(StringIdKey userKey, AssetCatalogUpdateInfo assetCatalogUpdateInfo)
            throws ServiceException {
        try {
            assetCatalogOperateHandler.updateAssetCatalog(userKey, assetCatalogUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("更新资产目录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removeAssetCatalog(StringIdKey userKey, LongIdKey assetCatalogKey) throws ServiceException {
        try {
            assetCatalogOperateHandler.removeAssetCatalog(userKey, assetCatalogKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("删除资产目录时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void upsertPermission(StringIdKey ownerUserKey, PermissionUpsertInfo permissionUpsertInfo)
            throws ServiceException {
        try {
            assetCatalogOperateHandler.upsertPermission(ownerUserKey, permissionUpsertInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("添加或更新资产目录的权限时发生异常", LogLevel.WARN, sem, e);
        }
    }

    @Override
    public void removePermission(StringIdKey ownerUserKey, PermissionRemoveInfo permissionRemoveInfo)
            throws ServiceException {
        try {
            assetCatalogOperateHandler.removePermission(ownerUserKey, permissionRemoveInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("移除资产目录的权限时发生异常", LogLevel.WARN, sem, e);
        }
    }
}
