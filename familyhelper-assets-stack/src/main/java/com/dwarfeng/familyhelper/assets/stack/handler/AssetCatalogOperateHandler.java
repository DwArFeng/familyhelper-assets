package com.dwarfeng.familyhelper.assets.stack.handler;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.AssetCatalogCreateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.AssetCatalogUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.PermissionRemoveInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.PermissionUpsertInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 资产目录操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface AssetCatalogOperateHandler extends Handler {

    /**
     * 创建资产目录。
     *
     * @param userKey                资产目录的所有者的主键。
     * @param assetCatalogCreateInfo 资产目录的创建信息。
     * @return 生成的资产目录的主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey createAssetCatalog(StringIdKey userKey, AssetCatalogCreateInfo assetCatalogCreateInfo)
            throws HandlerException;

    /**
     * 更新资产目录。
     *
     * @param userKey                资产目录的所有者的主键。
     * @param assetCatalogUpdateInfo 资产目录的更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateAssetCatalog(StringIdKey userKey, AssetCatalogUpdateInfo assetCatalogUpdateInfo) throws HandlerException;

    /**
     * 删除资产目录。
     *
     * @param userKey         资产目录的所有者的主键。
     * @param assetCatalogKey 资产目录的主键。
     * @throws HandlerException 处理器异常。
     */
    void removeAssetCatalog(StringIdKey userKey, LongIdKey assetCatalogKey) throws HandlerException;

    /**
     * 添加或更新资产目录的权限。
     *
     * @param ownerUserKey         操作者的主键。
     * @param permissionUpsertInfo 权限添加信息。
     * @throws HandlerException 处理器异常。
     */
    void upsertPermission(StringIdKey ownerUserKey, PermissionUpsertInfo permissionUpsertInfo) throws HandlerException;

    /**
     * 移除资产目录的权限。
     *
     * @param ownerUserKey         操作者的主键。
     * @param permissionRemoveInfo 权限移除信息。
     * @throws HandlerException 处理器异常。
     */
    void removePermission(StringIdKey ownerUserKey, PermissionRemoveInfo permissionRemoveInfo) throws HandlerException;
}
