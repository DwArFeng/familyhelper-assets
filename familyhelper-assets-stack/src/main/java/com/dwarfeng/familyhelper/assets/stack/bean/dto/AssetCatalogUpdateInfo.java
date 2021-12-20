package com.dwarfeng.familyhelper.assets.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 资产目录更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class AssetCatalogUpdateInfo implements Dto {

    private static final long serialVersionUID = 1934468919726285361L;

    private LongIdKey assetCatalogKey;
    private String name;
    private String remark;

    public AssetCatalogUpdateInfo() {
    }

    public AssetCatalogUpdateInfo(LongIdKey assetCatalogKey, String name, String remark) {
        this.assetCatalogKey = assetCatalogKey;
        this.name = name;
        this.remark = remark;
    }

    public LongIdKey getAssetCatalogKey() {
        return assetCatalogKey;
    }

    public void setAssetCatalogKey(LongIdKey assetCatalogKey) {
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
        return "AssetCatalogUpdateInfo{" +
                "assetCatalogKey=" + assetCatalogKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
