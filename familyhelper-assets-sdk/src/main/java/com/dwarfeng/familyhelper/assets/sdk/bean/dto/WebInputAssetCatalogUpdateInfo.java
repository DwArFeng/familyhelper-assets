package com.dwarfeng.familyhelper.assets.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.AssetCatalogUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 资产目录更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputAssetCatalogUpdateInfo implements Dto {

    private static final long serialVersionUID = -5485111282719661036L;

    public static AssetCatalogUpdateInfo toStackBean(WebInputAssetCatalogUpdateInfo webInputAssetCatalogUpdateInfo) {
        if (Objects.isNull(webInputAssetCatalogUpdateInfo)) {
            return null;
        } else {
            return new AssetCatalogUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputAssetCatalogUpdateInfo.getAssetCatalogKey()),
                    webInputAssetCatalogUpdateInfo.getName(),
                    webInputAssetCatalogUpdateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "asset_catalog_key")
    @Valid
    @NotNull
    private WebInputLongIdKey assetCatalogKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "remark")
    private String remark;

    public WebInputAssetCatalogUpdateInfo() {
    }

    public WebInputLongIdKey getAssetCatalogKey() {
        return assetCatalogKey;
    }

    public void setAssetCatalogKey(WebInputLongIdKey assetCatalogKey) {
        this.assetCatalogKey = assetCatalogKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputAssetCatalogUpdateInfo{" +
                "assetCatalogKey=" + assetCatalogKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
