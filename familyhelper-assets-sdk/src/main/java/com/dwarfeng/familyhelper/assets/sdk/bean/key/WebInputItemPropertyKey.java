package com.dwarfeng.familyhelper.assets.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 资产目录权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputItemPropertyKey implements Key {

    private static final long serialVersionUID = -5809656035018312497L;

    public static ItemPropertyKey toStackBean(WebInputItemPropertyKey webInputItemPropertyKey) {
        if (Objects.isNull(webInputItemPropertyKey)) {
            return null;
        } else {
            return new ItemPropertyKey(webInputItemPropertyKey.getItemId(), webInputItemPropertyKey.getPropertyId());
        }
    }

    @JSONField(name = "item_key")
    @NotNull
    private Long itemId;

    @JSONField(name = "property_id")
    @NotNull
    @NotEmpty
    private String propertyId;

    public WebInputItemPropertyKey() {
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

        WebInputItemPropertyKey that = (WebInputItemPropertyKey) o;

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
        return "WebInputItemPropertyKey{" +
                "itemId=" + itemId +
                ", propertyId='" + propertyId + '\'' +
                '}';
    }
}
