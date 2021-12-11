package com.dwarfeng.familyhelper.assets.sdk.bean.key.formatter;

import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.subgrade.sdk.common.Constants;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringKeyFormatter;

import java.util.Objects;

/**
 * ItemPropertyKey 的文本格式化转换器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemPropertyStringKeyFormatter implements StringKeyFormatter<ItemPropertyKey> {

    private String prefix;

    public ItemPropertyStringKeyFormatter(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String format(ItemPropertyKey key) {
        Objects.requireNonNull(key);
        return prefix + key.getItemId() + "_" + key.getPropertyId();
    }

    @Override
    public String generalFormat() {
        return prefix + Constants.REDIS_KEY_WILDCARD_CHARACTER;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String toString() {
        return "ItemPropertyStringKeyFormatter{" +
                "prefix='" + prefix + '\'' +
                '}';
    }
}
