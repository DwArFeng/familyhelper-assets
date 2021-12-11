package com.dwarfeng.familyhelper.assets.stack.cache;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 资产目录缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface AssetCatalogCache extends BatchBaseCache<LongIdKey, AssetCatalog> {
}
