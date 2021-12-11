package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.sdk.bean.key.WebInputItemPropertyKey;
import com.dwarfeng.familyhelper.assets.sdk.util.Constraints;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemProperty;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 账本权限。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputItemProperty implements Bean {

    private static final long serialVersionUID = -3023130809824886353L;

    public static ItemProperty toStackBean(WebInputItemProperty webInputItemProperty) {
        if (Objects.isNull(webInputItemProperty)) {
            return null;
        } else {
            return new ItemProperty(
                    WebInputItemPropertyKey.toStackBean(webInputItemProperty.getKey()),
                    webInputItemProperty.getLabel(), webInputItemProperty.getValue(), webInputItemProperty.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputItemPropertyKey key;

    @JSONField(name = "label")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_LABEL)
    private String label;

    @JSONField(name = "value")
    private String value;

    @JSONField(name = "remark")
    private String remark;

    public WebInputItemProperty() {
    }

    public WebInputItemPropertyKey getKey() {
        return key;
    }

    public void setKey(WebInputItemPropertyKey key) {
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
        return "WebInputItemProperty{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
