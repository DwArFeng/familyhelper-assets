package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 资产目录。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonAssetCatalog implements Bean {

    private static final long serialVersionUID = 8368842348847155518L;

    public static FastJsonAssetCatalog of(AssetCatalog assetCatalog) {
        if (Objects.isNull(assetCatalog)) {
            return null;
        } else {
            return new FastJsonAssetCatalog(
                    FastJsonLongIdKey.of(assetCatalog.getKey()),
                    assetCatalog.getName(),
                    assetCatalog.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "name", ordinal = 2)
    private String name;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonAssetCatalog() {
    }

    public FastJsonAssetCatalog(FastJsonLongIdKey key, String name, String remark) {
        this.key = key;
        this.name = name;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
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
        return "FastJsonAssetCatalog{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
