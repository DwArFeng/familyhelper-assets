package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 资产目录。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonAssetCatalog implements Bean {

    private static final long serialVersionUID = 3340366377304888391L;

    public static FastJsonAssetCatalog of(AssetCatalog assetCatalog) {
        if (Objects.isNull(assetCatalog)) {
            return null;
        } else {
            return new FastJsonAssetCatalog(
                    FastJsonLongIdKey.of(assetCatalog.getKey()), assetCatalog.getName(), assetCatalog.getRemark(),
                    assetCatalog.getItemCount(), assetCatalog.getCreatedDate()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "name", ordinal = 2)
    private String name;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    @JSONField(name = "item_count", ordinal = 4)
    private int itemCount;

    @JSONField(name = "created_date", ordinal = 5)
    private Date createdDate;

    public FastJsonAssetCatalog() {
    }

    public FastJsonAssetCatalog(FastJsonLongIdKey key, String name, String remark, int itemCount, Date createdDate) {
        this.key = key;
        this.name = name;
        this.remark = remark;
        this.itemCount = itemCount;
        this.createdDate = createdDate;
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

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "FastJsonAssetCatalog{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", itemCount=" + itemCount +
                ", createdDate=" + createdDate +
                '}';
    }
}
