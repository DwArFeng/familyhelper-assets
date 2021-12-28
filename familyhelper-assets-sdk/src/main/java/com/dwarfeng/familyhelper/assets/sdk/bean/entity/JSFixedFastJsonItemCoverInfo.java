package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemCoverInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 项目封面信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonItemCoverInfo implements Bean {

    private static final long serialVersionUID = 946452725972512631L;

    public static JSFixedFastJsonItemCoverInfo of(ItemCoverInfo itemCoverInfo) {
        if (Objects.isNull(itemCoverInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonItemCoverInfo(
                    JSFixedFastJsonLongIdKey.of(itemCoverInfo.getKey()),
                    JSFixedFastJsonLongIdKey.of(itemCoverInfo.getItemKey()),
                    itemCoverInfo.getOriginName(), itemCoverInfo.getLength(), itemCoverInfo.getCreateDate(),
                    itemCoverInfo.getModifiedDate(), itemCoverInfo.getRemark(), itemCoverInfo.getIndex()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "item_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey itemKey;

    @JSONField(name = "origin_name", ordinal = 3)
    private String originName;

    @JSONField(name = "length", ordinal = 4, serializeUsing = ToStringSerializer.class)
    private long length;

    @JSONField(name = "create_date", ordinal = 5)
    private Date createDate;

    @JSONField(name = "modified_date", ordinal = 6)
    private Date modifiedDate;

    @JSONField(name = "remark", ordinal = 7)
    private String remark;

    @JSONField(name = "index", ordinal = 8)
    private int index;

    public JSFixedFastJsonItemCoverInfo() {
    }

    public JSFixedFastJsonItemCoverInfo(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey itemKey, String originName, long length,
            Date createDate, Date modifiedDate, String remark, int index
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

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getItemKey() {
        return itemKey;
    }

    public void setItemKey(JSFixedFastJsonLongIdKey itemKey) {
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
        return "JSFixedFastJsonItemCoverInfo{" +
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
