package com.dwarfeng.familyhelper.assets.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 资产目录权限信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class PermissionUpsertInfo implements Dto {

    private static final long serialVersionUID = -9089779484430750071L;

    private LongIdKey assetCatalogKey;
    private StringIdKey userKey;
    private int permissionLevel;

    public PermissionUpsertInfo() {
    }

    public PermissionUpsertInfo(LongIdKey AssetCatalogKey, StringIdKey userKey, int permissionLevel) {
        this.assetCatalogKey = AssetCatalogKey;
        this.userKey = userKey;
        this.permissionLevel = permissionLevel;
    }

    public LongIdKey getAssetCatalogKey() {
        return assetCatalogKey;
    }

    public void setAssetCatalogKey(LongIdKey AssetCatalogKey) {
        this.assetCatalogKey = AssetCatalogKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        return "PermissionUpsertInfo{" +
                "assetCatalogKey=" + assetCatalogKey +
                ", userKey=" + userKey +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
