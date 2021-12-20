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
public class PermissionRemoveInfo implements Dto {

    private static final long serialVersionUID = 4901438160492930269L;

    private LongIdKey assetCatalogKey;
    private StringIdKey userKey;

    public PermissionRemoveInfo() {
    }

    public PermissionRemoveInfo(LongIdKey assetCatalogKey, StringIdKey userKey) {
        this.assetCatalogKey = assetCatalogKey;
        this.userKey = userKey;
    }

    public LongIdKey getAssetCatalogKey() {
        return assetCatalogKey;
    }

    public void setAssetCatalogKey(LongIdKey assetCatalogKey) {
        this.assetCatalogKey = assetCatalogKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return "PermissionRemoveInfo{" +
                "assetCatalogKey=" + assetCatalogKey +
                ", userKey=" + userKey +
                '}';
    }
}
