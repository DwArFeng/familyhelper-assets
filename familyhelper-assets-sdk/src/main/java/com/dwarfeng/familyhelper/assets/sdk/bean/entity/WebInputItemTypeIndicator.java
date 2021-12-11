package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.sdk.util.Constraints;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemTypeIndicator;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * WebInput 项目类型指示器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputItemTypeIndicator implements Bean {

    private static final long serialVersionUID = -8435180491184743757L;

    public static ItemTypeIndicator toStackBean(WebInputItemTypeIndicator webInputItemTypeIndicator) {
        return new ItemTypeIndicator(
                WebInputStringIdKey.toStackBean(webInputItemTypeIndicator.getKey()),
                webInputItemTypeIndicator.getLabel(),
                webInputItemTypeIndicator.getRemark()
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

    public WebInputItemTypeIndicator() {
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
        return "WebInputItemTypeIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
