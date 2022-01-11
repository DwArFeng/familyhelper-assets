package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 项目文件信息维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemFileInfoMaintainService extends BatchCrudService<LongIdKey, ItemFileInfo>,
        EntireLookupService<ItemFileInfo>, PresetLookupService<ItemFileInfo> {

    String CHILD_FOR_ITEM = "child_for_item";
    String CHILD_FOR_ITEM_ORIGIN_NAME_ASC = "child_for_item_origin_name_asc";
    String CHILD_FOR_ITEM_ORIGIN_NAME_DESC = "child_for_item_origin_name_desc";
    String CHILD_FOR_ITEM_CREATED_DATE_ASC = "child_for_item_created_date_asc";
    String CHILD_FOR_ITEM_CREATED_DATE_DESC = "child_for_item_created_date_desc";
    String CHILD_FOR_ITEM_MODIFIED_DATE_ASC = "child_for_item_modified_date_asc";
    String CHILD_FOR_ITEM_MODIFIED_DATE_DESC = "child_for_item_modified_date_desc";
    String CHILD_FOR_ITEM_INSPECTED_DATE_ASC = "child_for_item_inspected_date_asc";
    String CHILD_FOR_ITEM_INSPECTED_DATE_DESC = "child_for_item_inspected_date_desc";
}
