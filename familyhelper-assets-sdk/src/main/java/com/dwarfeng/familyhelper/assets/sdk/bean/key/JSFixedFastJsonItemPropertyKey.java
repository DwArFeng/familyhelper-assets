package com.dwarfeng.familyhelper.assets.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 资产目录权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonItemPropertyKey implements Key {

    private static final long serialVersionUID = -5411059051076711165L;

    public static JSFixedFastJsonItemPropertyKey of(ItemPropertyKey key) {
        if (Objects.isNull(key)) {
            return null;
        } else {
            return new JSFixedFastJsonItemPropertyKey(key.getItemId(), key.getPropertyId());
        }
    }

    @JSONField(name = "item_key", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long itemId;

    @JSONField(name = "property_id", ordinal = 2)
    private String propertyId;

    public JSFixedFastJsonItemPropertyKey() {
    }

    public JSFixedFastJsonItemPropertyKey(Long itemId, String propertyId) {
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

        JSFixedFastJsonItemPropertyKey that = (JSFixedFastJsonItemPropertyKey) o;

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
        return "JSFixedFastJsonItemPropertyKey{" +
                "itemId=" + itemId +
                ", propertyId='" + propertyId + '\'' +
                '}';
    }
}
