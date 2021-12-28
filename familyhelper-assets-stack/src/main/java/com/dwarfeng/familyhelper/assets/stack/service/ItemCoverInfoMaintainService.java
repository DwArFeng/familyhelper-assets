package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemCoverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 项目封面信息维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemCoverInfoMaintainService extends BatchCrudService<LongIdKey, ItemCoverInfo>,
        EntireLookupService<ItemCoverInfo>, PresetLookupService<ItemCoverInfo> {

    String CHILD_FOR_ITEM = "child_for_item";
    String CHILD_FOR_ITEM_INDEX_ASC = "child_for_item_index_asc";
    String CHILD_FOR_ITEM_INDEX_DESC = "child_for_item_index_desc";
}
