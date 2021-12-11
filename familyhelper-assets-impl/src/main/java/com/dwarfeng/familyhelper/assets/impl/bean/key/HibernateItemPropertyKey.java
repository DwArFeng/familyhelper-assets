package com.dwarfeng.familyhelper.assets.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.Bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * Hibernate 资产目录权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class HibernateItemPropertyKey implements Bean, Serializable {

    private static final long serialVersionUID = -1920844117154954559L;

    private Long itemId;
    private String propertyId;

    public HibernateItemPropertyKey() {
    }

    public HibernateItemPropertyKey(Long itemId, String propertyId) {
        this.itemId = itemId;
        this.propertyId = propertyId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HibernateItemPropertyKey that = (HibernateItemPropertyKey) o;

        if (!Objects.equals(itemId, that.itemId)) return false;
        return Objects.equals(propertyId, that.propertyId);
    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (propertyId != null ? propertyId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateItemPropertyKey{" +
                "itemId=" + itemId +
                ", propertyId='" + propertyId + '\'' +
                '}';
    }
}
