package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemLabel;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

import java.util.List;

/**
 * 项目标签维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemLabelMaintainService extends BatchCrudService<StringIdKey, ItemLabel>,
        EntireLookupService<ItemLabel>, PresetLookupService<ItemLabel> {

    String CHILD_FOR_ITEM = "child_for_item";

    boolean existsItemRelation(StringIdKey labelIdKey, LongIdKey itemIdKey) throws ServiceException;

    void addItemRelation(StringIdKey labelIdKey, LongIdKey itemIdKey) throws ServiceException;

    void deleteItemRelation(StringIdKey labelIdKey, LongIdKey itemIdKey) throws ServiceException;

    boolean existsAllItemRelations(StringIdKey labelIdKey, List<LongIdKey> itemIdKeys) throws ServiceException;

    boolean existsNonItemRelations(StringIdKey labelIdKey, List<LongIdKey> itemIdKeys) throws ServiceException;

    void batchAddItemRelations(StringIdKey labelIdKey, List<LongIdKey> itemIdKeys) throws ServiceException;

    void batchDeleteItemRelations(StringIdKey labelIdKey, List<LongIdKey> itemIdKeys) throws ServiceException;
}
