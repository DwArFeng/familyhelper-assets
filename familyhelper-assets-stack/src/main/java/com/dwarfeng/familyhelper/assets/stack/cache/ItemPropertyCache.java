package com.dwarfeng.familyhelper.assets.stack.cache;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemProperty;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 项目属性缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemPropertyCache extends BatchBaseCache<ItemPropertyKey, ItemProperty> {
}
