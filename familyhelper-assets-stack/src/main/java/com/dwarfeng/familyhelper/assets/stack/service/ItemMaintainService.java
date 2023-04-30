package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

import java.util.List;

/**
 * 零部件维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemMaintainService extends BatchCrudService<LongIdKey, Item>, EntireLookupService<Item>,
        PresetLookupService<Item> {

    String CHILD_FOR_PARENT = "child_for_parent";
    String CHILD_FOR_ASSET_CATALOG = "child_for_asset_catalog";
    String CHILD_FOR_LABEL = "child_for_label";
    String CHILD_FOR_ASSET_CATALOG_ROOT = "child_for_asset_catalog_root";
    String NAME_LIKE = "name_like";
    String CHILD_FOR_ASSET_CATALOG_NAME_LIKE = "child_for_asset_catalog_name_like";

    boolean existsLabelRelation(LongIdKey itemIdKey, StringIdKey labelIdKey) throws ServiceException;

    void addLabelRelation(LongIdKey itemIdKey, StringIdKey labelIdKey) throws ServiceException;

    void deleteLabelRelation(LongIdKey itemIdKey, StringIdKey labelIdKey) throws ServiceException;

    boolean existsAllLabelRelations(LongIdKey itemIdKey, List<StringIdKey> labelIdKeys) throws ServiceException;

    boolean existsNonLabelRelations(LongIdKey itemIdKey, List<StringIdKey> labelIdKeys) throws ServiceException;

    void batchAddLabelRelations(LongIdKey itemIdKey, List<StringIdKey> labelIdKeys) throws ServiceException;

    void batchDeleteLabelRelations(LongIdKey itemIdKey, List<StringIdKey> labelIdKeys) throws ServiceException;
}
