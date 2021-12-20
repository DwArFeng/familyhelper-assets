package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.AssetCatalogCreateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.AssetCatalogUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.PermissionRemoveInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.PermissionUpsertInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 资产目录操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface AssetCatalogOperateService extends Service {

    /**
     * 创建资产目录。
     *
     * @param userKey                资产目录的所有者的主键。
     * @param assetCatalogCreateInfo 资产目录的创建信息。
     * @return 生成的资产目录的主键。
     * @throws ServiceException 服务异常。
     */
    LongIdKey createAssetCatalog(StringIdKey userKey, AssetCatalogCreateInfo assetCatalogCreateInfo)
            throws ServiceException;

    /**
     * 更新资产目录。
     *
     * @param userKey                资产目录的所有者的主键。
     * @param assetCatalogUpdateInfo 资产目录的更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateAssetCatalog(StringIdKey userKey, AssetCatalogUpdateInfo assetCatalogUpdateInfo)
            throws ServiceException;

    /**
     * 删除资产目录。
     *
     * @param userKey         资产目录的所有者的主键。
     * @param assetCatalogKey 资产目录的主键。
     * @throws ServiceException 服务异常。
     */
    void removeAssetCatalog(StringIdKey userKey, LongIdKey assetCatalogKey) throws ServiceException;

    /**
     * 添加或更新资产目录的权限。
     *
     * @param ownerUserKey         操作者的主键。
     * @param permissionUpsertInfo 权限添加信息。
     * @throws ServiceException 服务异常。
     */
    void upsertPermission(StringIdKey ownerUserKey, PermissionUpsertInfo permissionUpsertInfo) throws ServiceException;

    /**
     * 移除资产目录的权限。
     *
     * @param ownerUserKey         操作者的主键。
     * @param permissionRemoveInfo 权限移除信息。
     * @throws ServiceException 服务异常。
     */
    void removePermission(StringIdKey ownerUserKey, PermissionRemoveInfo permissionRemoveInfo) throws ServiceException;
}
