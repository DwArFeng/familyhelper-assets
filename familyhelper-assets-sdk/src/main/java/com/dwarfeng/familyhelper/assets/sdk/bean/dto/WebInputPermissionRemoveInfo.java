package com.dwarfeng.familyhelper.assets.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.PermissionRemoveInfo;
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
public class WebInputPermissionRemoveInfo implements Dto {

    private static final long serialVersionUID = -4397765005123008354L;

    public static PermissionRemoveInfo toStackBean(WebInputPermissionRemoveInfo webInputPermissionRemoveInfo) {
        if (Objects.isNull(webInputPermissionRemoveInfo)) {
            return null;
        } else {
            return new PermissionRemoveInfo(
                    WebInputLongIdKey.toStackBean(webInputPermissionRemoveInfo.getAssetCatalogKey()),
                    WebInputStringIdKey.toStackBean(webInputPermissionRemoveInfo.getUserKey())
            );
        }
    }

    @JSONField(name = "asset_catalog_key")
    @Valid
    private WebInputLongIdKey assetCatalogKey;

    @JSONField(name = "user_key")
    @Valid
    private WebInputStringIdKey userKey;

    public WebInputPermissionRemoveInfo() {
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

    @Override
    public String toString() {
        return "WebInputPermissionRemoveInfo{" +
                "assetCatalogKey=" + assetCatalogKey +
                ", userKey=" + userKey +
                '}';
    }
}
