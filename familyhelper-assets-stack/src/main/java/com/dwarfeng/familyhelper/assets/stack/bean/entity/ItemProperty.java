package com.dwarfeng.familyhelper.assets.stack.bean.entity;

import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 项目属性。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemProperty implements Entity<ItemPropertyKey> {

    private static final long serialVersionUID = -6442882034846365086L;

    private ItemPropertyKey key;
    private String label;
    private String value;
    private String remark;

    public ItemProperty() {
    }

    public ItemProperty(ItemPropertyKey key, String label, String value, String remark) {
        this.key = key;
        this.label = label;
        this.value = value;
        this.remark = remark;
    }

    @Override
    public ItemPropertyKey getKey() {
        return key;
    }

    @Override
    public void setKey(ItemPropertyKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ItemProperty{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
