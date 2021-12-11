package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.Poac;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 资产目录维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface PoacMaintainService extends BatchCrudService<PoacKey, Poac>,
        EntireLookupService<Poac>, PresetLookupService<Poac> {

    String CHILD_FOR_ASSET_CATALOG = "child_for_asset_catalog";
    String CHILD_FOR_USER = "child_for_user";
    String CHILD_FOR_ASSET_CATALOG_PERMISSION_LEVEL_EQUALS = "child_for_asset_catalog_permission_level_equals";
    String CHILD_FOR_USER_PERMISSION_LEVEL_EQUALS = "child_for_user_permission_level_equals";
}
