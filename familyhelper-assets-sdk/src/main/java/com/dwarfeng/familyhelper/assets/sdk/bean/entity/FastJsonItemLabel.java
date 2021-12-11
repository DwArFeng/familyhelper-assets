package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemLabel;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 项目标签。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonItemLabel implements Bean {

    private static final long serialVersionUID = -653886922646935786L;

    public static FastJsonItemLabel of(ItemLabel ItemLabel) {
        if (Objects.isNull(ItemLabel)) {
            return null;
        } else {
            return new FastJsonItemLabel(
                    FastJsonStringIdKey.of(ItemLabel.getKey()),
                    ItemLabel.getLabel(),
                    ItemLabel.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonItemLabel() {
    }

    public FastJsonItemLabel(FastJsonStringIdKey key, String label, String remark) {
        this.key = key;
        this.label = label;
        this.remark = remark;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonItemLabel{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
