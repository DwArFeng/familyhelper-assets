package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemProperty;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 项目属性维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemPropertyMaintainService extends BatchCrudService<ItemPropertyKey, ItemProperty>,
        EntireLookupService<ItemProperty>, PresetLookupService<ItemProperty> {

    String ITEM_ID_EQUALS = "item_id_equals";
}
