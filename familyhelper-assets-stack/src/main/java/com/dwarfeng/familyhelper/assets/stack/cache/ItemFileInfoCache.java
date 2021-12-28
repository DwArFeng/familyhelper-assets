package com.dwarfeng.familyhelper.assets.stack.cache;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 项目文件信息缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemFileInfoCache extends BatchBaseCache<LongIdKey, ItemFileInfo> {
}
