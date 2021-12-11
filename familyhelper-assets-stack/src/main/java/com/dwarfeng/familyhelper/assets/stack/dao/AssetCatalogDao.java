package com.dwarfeng.familyhelper.assets.stack.dao;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 资产目录数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface AssetCatalogDao extends BatchBaseDao<LongIdKey, AssetCatalog>, EntireLookupDao<AssetCatalog>,
        PresetLookupDao<AssetCatalog> {
}
