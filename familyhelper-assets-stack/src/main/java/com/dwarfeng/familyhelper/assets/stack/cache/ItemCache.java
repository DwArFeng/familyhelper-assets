package com.dwarfeng.familyhelper.assets.stack.cache;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 零部件缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemCache extends BatchBaseCache<LongIdKey, Item> {
}
