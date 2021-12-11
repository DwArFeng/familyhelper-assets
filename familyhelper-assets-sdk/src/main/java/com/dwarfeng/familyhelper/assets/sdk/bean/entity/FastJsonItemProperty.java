package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.sdk.bean.key.FastJsonItemPropertyKey;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemProperty;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 账本权限。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonItemProperty implements Bean {

    private static final long serialVersionUID = -808428160034566314L;

    public static FastJsonItemProperty of(ItemProperty itemProperty) {
        if (Objects.isNull(itemProperty)) {
            return null;
        } else {
            return new FastJsonItemProperty(
                    FastJsonItemPropertyKey.of(itemProperty.getKey()),
                    itemProperty.getLabel(), itemProperty.getValue(), itemProperty.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonItemPropertyKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "value", ordinal = 3)
    private String value;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public FastJsonItemProperty() {
    }

    public FastJsonItemProperty(FastJsonItemPropertyKey key, String label, String value, String remark) {
        this.key = key;
        this.label = label;
        this.value = value;
        this.remark = remark;
    }

    public FastJsonItemPropertyKey getKey() {
        return key;
    }

    public void setKey(FastJsonItemPropertyKey key) {
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
        return "FastJsonItemProperty{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
