package com.dwarfeng.familyhelper.assets.impl.bean.entity;

import com.dwarfeng.familyhelper.assets.impl.bean.key.HibernateItemPropertyKey;
import com.dwarfeng.familyhelper.assets.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateItemPropertyKey.class)
@Table(name = "tbl_item_property")
public class HibernateItemProperty implements Bean {

    private static final long serialVersionUID = -3211874890300002757L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Id
    @Column(name = "property_id", nullable = false)
    private String propertyId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "label", length = Constraints.LENGTH_LABEL)
    private String label;

    @Column(name = "value", columnDefinition = "TEXT")
    private String value;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    public HibernateItemProperty() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateItemPropertyKey getKey() {
        return new HibernateItemPropertyKey(itemId, propertyId);
    }

    public void setKey(HibernateItemPropertyKey key) {
        if (Objects.isNull(key)) {
            this.itemId = null;
            this.propertyId = null;
        } else {
            this.itemId = key.getItemId();
            this.propertyId = key.getPropertyId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
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
        return getClass().getSimpleName() + "(" +
                "itemId = " + itemId + ", " +
                "propertyId = " + propertyId + ", " +
                "label = " + label + ", " +
                "value = " + value + ", " +
                "remark = " + remark + ")";
    }
}
