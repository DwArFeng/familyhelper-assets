package com.dwarfeng.familyhelper.assets.stack.dao;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemLabel;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.subgrade.stack.exception.DaoException;

import java.util.List;

/**
 * 标签项目数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemLabelDao extends BatchBaseDao<StringIdKey, ItemLabel>, EntireLookupDao<ItemLabel>,
        PresetLookupDao<ItemLabel> {

    boolean existsItemRelation(StringIdKey labelIdKey, LongIdKey itemIdKey) throws DaoException;

    void addItemRelation(StringIdKey labelIdKey, LongIdKey itemIdKey) throws DaoException;

    void deleteItemRelation(StringIdKey labelIdKey, LongIdKey itemIdKey) throws DaoException;

    boolean existsAllItemRelations(StringIdKey labelIdKey, List<LongIdKey> itemIdKeys) throws DaoException;

    boolean existsNonItemRelations(StringIdKey labelIdKey, List<LongIdKey> itemIdKeys) throws DaoException;

    void batchAddItemRelations(StringIdKey labelIdKey, List<LongIdKey> itemIdKeys) throws DaoException;

    void batchDeleteItemRelations(StringIdKey labelIdKey, List<LongIdKey> itemIdKeys) throws DaoException;
}
