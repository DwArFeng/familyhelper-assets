package com.dwarfeng.familyhelper.assets.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.List;

/**
 * 项目更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemUpdateInfo implements Dto {

    private static final long serialVersionUID = -4093987943528315007L;

    private LongIdKey itemKey;
    private LongIdKey parentKey;
    private List<StringIdKey> labelKeys;
    private String name;
    private String type;
    private Integer lifeCycle;
    private String remark;

    public ItemUpdateInfo() {
    }

    public ItemUpdateInfo(
            LongIdKey itemKey, LongIdKey parentKey, List<StringIdKey> labelKeys,
            String name, String type, Integer lifeCycle, String remark
    ) {
        this.itemKey = itemKey;
        this.parentKey = parentKey;
        this.labelKeys = labelKeys;
        this.name = name;
        this.type = type;
        this.lifeCycle = lifeCycle;
        this.remark = remark;
    }

    public LongIdKey getItemKey() {
        return itemKey;
    }

    public void setItemKey(LongIdKey itemKey) {
        this.itemKey = itemKey;
    }

    public LongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(LongIdKey parentKey) {
        this.parentKey = parentKey;
    }

    public List<StringIdKey> getLabelKeys() {
        return labelKeys;
    }

    public void setLabelKeys(List<StringIdKey> labelKeys) {
        this.labelKeys = labelKeys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(Integer lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ItemUpdateInfo{" +
                "itemKey=" + itemKey +
                ", parentKey=" + parentKey +
                ", labelKeys=" + labelKeys +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", lifeCycle=" + lifeCycle +
                ", remark='" + remark + '\'' +
                '}';
    }
}
