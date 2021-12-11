package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 资产目录。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonAssetCatalog implements Bean {

    private static final long serialVersionUID = 4158556347525526061L;

    public static JSFixedFastJsonAssetCatalog of(AssetCatalog assetCatalog) {
        if (Objects.isNull(assetCatalog)) {
            return null;
        } else {
            return new JSFixedFastJsonAssetCatalog(
                    JSFixedFastJsonLongIdKey.of(assetCatalog.getKey()),
                    assetCatalog.getName(),
                    assetCatalog.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "name", ordinal = 2)
    private String name;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonAssetCatalog() {
    }

    public JSFixedFastJsonAssetCatalog(JSFixedFastJsonLongIdKey key, String name, String remark) {
        this.key = key;
        this.name = name;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
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
        return "JSFixedFastJsonAssetCatalog{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
