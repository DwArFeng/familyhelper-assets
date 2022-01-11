package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemFileInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 项目文件信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonItemFileInfo implements Bean {

    private static final long serialVersionUID = -3281079525421745295L;

    public static JSFixedFastJsonItemFileInfo of(ItemFileInfo itemFileInfo) {
        if (Objects.isNull(itemFileInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonItemFileInfo(
                    JSFixedFastJsonLongIdKey.of(itemFileInfo.getKey()),
                    JSFixedFastJsonLongIdKey.of(itemFileInfo.getItemKey()),
                    itemFileInfo.getOriginName(), itemFileInfo.getLength(), itemFileInfo.getCreatedDate(),
                    itemFileInfo.getModifiedDate(), itemFileInfo.getInspectedDate(), itemFileInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "item_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey itemKey;

    @JSONField(name = "origin_name", ordinal = 3)
    private String originName;

    @JSONField(name = "length", ordinal = 4)
    private long length;

    @JSONField(name = "created_date", ordinal = 5)
    private Date createdDate;

    @JSONField(name = "modified_date", ordinal = 6)
    private Date modifiedDate;

    @JSONField(name = "inspected_date", ordinal = 7)
    private Date inspectedDate;

    @JSONField(name = "remark", ordinal = 8)
    private String remark;

    public JSFixedFastJsonItemFileInfo() {
    }

    public JSFixedFastJsonItemFileInfo(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey itemKey, String originName, long length,
            Date createdDate, Date modifiedDate, Date inspectedDate, String remark
    ) {
        this.key = key;
        this.itemKey = itemKey;
        this.originName = originName;
        this.length = length;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.inspectedDate = inspectedDate;
        this.remark = remark;
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

    public Date getInspectedDate() {
        return inspectedDate;
    }

    public void setInspectedDate(Date inspectedDate) {
        this.inspectedDate = inspectedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonItemFileInfo{" +
                "key=" + key +
                ", itemKey=" + itemKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", inspectedDate=" + inspectedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
