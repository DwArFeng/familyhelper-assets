package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
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
public class FastJsonItem implements Bean {

    private static final long serialVersionUID = -7205885628114662044L;

    public static FastJsonItem of(Item item) {
        if (Objects.isNull(item)) {
            return null;
        } else {
            return new FastJsonItem(
                    FastJsonLongIdKey.of(item.getKey()), FastJsonLongIdKey.of(item.getParentKey()),
                    FastJsonLongIdKey.of(item.getAssetCatalogKey()),
                    item.getName(), item.getItemType(), item.getCreatedDate(), item.getModifiedDate(),
                    item.getScrappedDate(), item.getLifeCycle(), item.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "parent_key", ordinal = 2)
    private FastJsonLongIdKey parentKey;

    @JSONField(name = "asset_catalog_key", ordinal = 3)
    private FastJsonLongIdKey assetCatalogKey;

    @JSONField(name = "name", ordinal = 4)
    private String name;

    @JSONField(name = "item_type", ordinal = 5)
    private String itemType;

    @JSONField(name = "created_date", ordinal = 6)
    private Date createdDate;

    @JSONField(name = "modified_date", ordinal = 7)
    private Date modifiedDate;

    @JSONField(name = "scrapped_date", ordinal = 8)
    private Date scrappedDate;

    @JSONField(name = "life_cycle", ordinal = 9)
    private Integer lifeCycle;

    @JSONField(name = "remark", ordinal = 10)
    private String remark;

    public FastJsonItem() {
    }

    public FastJsonItem(
            FastJsonLongIdKey key, FastJsonLongIdKey parentKey, FastJsonLongIdKey assetCatalogKey, String name,
            String itemType, Date createdDate, Date modifiedDate, Date scrappedDate, Integer lifeCycle, String remark
    ) {
        this.key = key;
        this.parentKey = parentKey;
        this.assetCatalogKey = assetCatalogKey;
        this.name = name;
        this.itemType = itemType;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.scrappedDate = scrappedDate;
        this.lifeCycle = lifeCycle;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(FastJsonLongIdKey parentKey) {
        this.parentKey = parentKey;
    }

    public FastJsonLongIdKey getAssetCatalogKey() {
        return assetCatalogKey;
    }

    public void setAssetCatalogKey(FastJsonLongIdKey assetCatalogKey) {
        this.assetCatalogKey = assetCatalogKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getScrappedDate() {
        return scrappedDate;
    }

    public void setScrappedDate(Date scrappedDate) {
        this.scrappedDate = scrappedDate;
    }

    public Integer getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(Integer lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonItem{" +
                "key=" + key +
                ", parentKey=" + parentKey +
                ", assetCatalogKey=" + assetCatalogKey +
                ", name='" + name + '\'' +
                ", itemType='" + itemType + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", scrappedDate=" + scrappedDate +
                ", lifeCycle=" + lifeCycle +
                ", remark='" + remark + '\'' +
                '}';
    }
}
