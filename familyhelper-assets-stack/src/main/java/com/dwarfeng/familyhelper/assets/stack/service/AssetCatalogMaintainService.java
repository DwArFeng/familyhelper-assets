package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 资产目录维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface AssetCatalogMaintainService extends BatchCrudService<LongIdKey, AssetCatalog>,
        EntireLookupService<AssetCatalog>, PresetLookupService<AssetCatalog> {

    String NAME_LIKE = "name_like";
}
