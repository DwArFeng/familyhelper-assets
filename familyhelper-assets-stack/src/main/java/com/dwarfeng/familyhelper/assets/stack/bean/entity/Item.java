package com.dwarfeng.familyhelper.assets.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 项目。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Item implements Entity<LongIdKey> {

    private static final long serialVersionUID = 5983510953007954996L;

    private LongIdKey key;
    private LongIdKey parentKey;
    private LongIdKey assetCatalogKey;
    private String name;
    private String itemType;
    private Date createdDate;
    private Date modifiedDate;
    private Date scrappedDate;
    private Integer lifeCycle;
    private String remark;

    public Item() {
    }

    public Item(
            LongIdKey key, LongIdKey parentKey, LongIdKey assetCatalogKey, String name, String itemType, Date createdDate,
            Date modifiedDate, Date scrappedDate, Integer lifeCycle, String remark
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

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(LongIdKey parentKey) {
        this.parentKey = parentKey;
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
        return "Item{" +
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
