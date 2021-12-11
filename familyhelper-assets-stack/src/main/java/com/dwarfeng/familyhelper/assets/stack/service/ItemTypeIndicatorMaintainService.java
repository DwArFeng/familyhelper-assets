package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemTypeIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;

/**
 * 项目类型指示器维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemTypeIndicatorMaintainService extends BatchCrudService<StringIdKey, ItemTypeIndicator>,
        EntireLookupService<ItemTypeIndicator> {
}
