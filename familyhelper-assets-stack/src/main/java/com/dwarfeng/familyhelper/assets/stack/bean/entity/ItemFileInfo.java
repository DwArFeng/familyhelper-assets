package com.dwarfeng.familyhelper.assets.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 项目文件信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemFileInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = -3250002253998326255L;

    private LongIdKey key;
    private LongIdKey itemKey;
    private String originName;
    private long length;
    private Date createDate;
    private Date modifiedDate;
    private String remark;
    private int index;

    public ItemFileInfo() {
    }

    public ItemFileInfo(
            LongIdKey key, LongIdKey itemKey, String originName, long length, Date createDate, Date modifiedDate,
            String remark, int index
    ) {
        this.key = key;
        this.itemKey = itemKey;
        this.originName = originName;
        this.length = length;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.remark = remark;
        this.index = index;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getItemKey() {
        return itemKey;
    }

    public void setItemKey(LongIdKey itemKey) {
        this.itemKey = itemKey;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "ItemFileInfo{" +
                "key=" + key +
                ", itemKey=" + itemKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", remark='" + remark + '\'' +
                ", index=" + index +
                '}';
    }
}
