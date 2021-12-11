package com.dwarfeng.familyhelper.assets.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 项目属性主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemPropertyKey implements Key {

    private static final long serialVersionUID = 6334373792543183807L;

    private Long itemId;
    private String propertyId;

    public ItemPropertyKey() {
    }

    public ItemPropertyKey(Long itemId, String propertyId) {
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

        ItemPropertyKey that = (ItemPropertyKey) o;

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
        return "ItemPropertyKey{" +
                "itemId=" + itemId +
                ", propertyId='" + propertyId + '\'' +
                '}';
    }
}
