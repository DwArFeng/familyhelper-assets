package com.dwarfeng.familyhelper.assets.stack.cache;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemLabel;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 项目标签缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemLabelCache extends BatchBaseCache<StringIdKey, ItemLabel> {
}
