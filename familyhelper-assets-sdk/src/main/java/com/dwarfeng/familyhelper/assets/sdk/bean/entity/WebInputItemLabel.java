package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.sdk.util.Constraints;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemLabel;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * WebInput 项目标签。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputItemLabel implements Bean {

    private static final long serialVersionUID = 3893121287039458542L;

    public static ItemLabel toStackBean(WebInputItemLabel webInputItemLabel) {
        return new ItemLabel(
                WebInputStringIdKey.toStackBean(webInputItemLabel.getKey()),
                webInputItemLabel.getLabel(),
                webInputItemLabel.getRemark()
        );
    }

    @JSONField(name = "key")
    @Valid
    private WebInputStringIdKey key;

    @JSONField(name = "label")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_LABEL)
    private String label;

    @JSONField(name = "remark")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputItemLabel() {
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
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
        return "WebInputItemLabel{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
