package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemCoverInfo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 项目封面信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonItemCoverInfo implements Bean {

    private static final long serialVersionUID = 6224672449748676140L;

    public static FastJsonItemCoverInfo of(ItemCoverInfo itemCoverInfo) {
        if (Objects.isNull(itemCoverInfo)) {
            return null;
        } else {
            return new FastJsonItemCoverInfo(
                    FastJsonLongIdKey.of(itemCoverInfo.getKey()),
                    FastJsonLongIdKey.of(itemCoverInfo.getItemKey()),
                    itemCoverInfo.getOriginName(), itemCoverInfo.getLength(), itemCoverInfo.getCreateDate(),
                    itemCoverInfo.getModifiedDate(), itemCoverInfo.getRemark(), itemCoverInfo.getIndex()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "item_key", ordinal = 2)
    private FastJsonLongIdKey itemKey;

    @JSONField(name = "origin_name", ordinal = 3)
    private String originName;

    @JSONField(name = "length", ordinal = 4)
    private long length;

    @JSONField(name = "create_date", ordinal = 5)
    private Date createDate;

    @JSONField(name = "modified_date", ordinal = 6)
    private Date modifiedDate;

    @JSONField(name = "remark", ordinal = 7)
    private String remark;

    @JSONField(name = "index", ordinal = 8)
    private int index;

    public FastJsonItemCoverInfo() {
    }

    public FastJsonItemCoverInfo(
            FastJsonLongIdKey key, FastJsonLongIdKey itemKey, String originName, long length, Date createDate,
            Date modifiedDate, String remark, int index
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

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getItemKey() {
        return itemKey;
    }

    public void setItemKey(FastJsonLongIdKey itemKey) {
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
        return "FastJsonItemCoverInfo{" +
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
