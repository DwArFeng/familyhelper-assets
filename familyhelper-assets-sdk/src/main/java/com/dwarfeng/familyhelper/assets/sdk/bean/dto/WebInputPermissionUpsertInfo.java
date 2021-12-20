package com.dwarfeng.familyhelper.assets.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.PermissionUpsertInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 资产目录权限信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputPermissionUpsertInfo implements Dto {

    private static final long serialVersionUID = 8920492362220154158L;

    public static PermissionUpsertInfo toStackBean(WebInputPermissionUpsertInfo webInputPermissionUpsertInfo) {
        if (Objects.isNull(webInputPermissionUpsertInfo)) {
            return null;
        } else {
            return new PermissionUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInputPermissionUpsertInfo.getAssetCatalogKey()),
                    WebInputStringIdKey.toStackBean(webInputPermissionUpsertInfo.getUserKey()),
                    webInputPermissionUpsertInfo.getPermissionLevel()
            );
        }
    }

    @JSONField(name = "asset_catalog_key")
    @Valid
    private WebInputLongIdKey assetCatalogKey;

    @JSONField(name = "user_key")
    @Valid
    private WebInputStringIdKey userKey;

    @JSONField(name = "permission_level")
    private int permissionLevel;

    public WebInputPermissionUpsertInfo() {
    }

    public WebInputLongIdKey getAssetCatalogKey() {
        return assetCatalogKey;
    }

    public void setAssetCatalogKey(WebInputLongIdKey assetCatalogKey) {
        this.assetCatalogKey = assetCatalogKey;
    }

    public WebInputStringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(WebInputStringIdKey userKey) {
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
        return "WebInputPermissionUpsertInfo{" +
                "assetCatalogKey=" + assetCatalogKey +
                ", userKey=" + userKey +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
