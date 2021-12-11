package com.dwarfeng.familyhelper.assets.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 资产目录。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class AssetCatalog implements Entity<LongIdKey> {

    private static final long serialVersionUID = -1608817712559286428L;

    private LongIdKey key;
    private String name;
    private String remark;

    public AssetCatalog() {
    }

    public AssetCatalog(LongIdKey key, String name, String remark) {
        this.key = key;
        this.name = name;
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
        return "AssetCatalog{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
