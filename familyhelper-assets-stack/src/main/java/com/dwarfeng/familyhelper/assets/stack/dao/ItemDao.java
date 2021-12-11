package com.dwarfeng.familyhelper.assets.stack.dao;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.subgrade.stack.exception.DaoException;

import java.util.List;

/**
 * 零部件数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemDao extends BatchBaseDao<LongIdKey, Item>, EntireLookupDao<Item>, PresetLookupDao<Item> {

    /**
     * 查询项目与标签的关联是否存在。
     *
     * @param itemIdKey  指定的项目主键。
     * @param labelIdKey 指定的标签主键。
     * @return 关联是否存在。
     * @throws DaoException 数据访问层异常。
     */
    boolean existsLabelRelation(LongIdKey itemIdKey, StringIdKey labelIdKey) throws DaoException;

    /**
     * 添加项目与标签的关联。
     *
     * @param itemIdKey  指定的项目主键。
     * @param labelIdKey 指定的标签主键。
     * @throws DaoException 数据访问层异常。
     */
    void addLabelRelation(LongIdKey itemIdKey, StringIdKey labelIdKey) throws DaoException;

    /**
     * 删除项目与标签的关联。
     *
     * @param itemIdKey  指定的项目主键。
     * @param labelIdKey 指定的标签主键。
     * @throws DaoException 数据访问层异常。
     */
    void deleteLabelRelation(LongIdKey itemIdKey, StringIdKey labelIdKey) throws DaoException;

    /**
     * 查询项目与标签的关联是否全部存在。
     *
     * @param itemIdKey   指定的项目主键。
     * @param labelIdKeys 指定的标签主键组成的列表。
     * @return 关联是否全部存在。
     * @throws DaoException 数据访问层异常。
     */
    boolean existsAllLabelRelations(LongIdKey itemIdKey, List<StringIdKey> labelIdKeys) throws DaoException;

    /**
     * 查询项目与标签的关联是否全部不存在。
     *
     * @param itemIdKey   指定的项目主键。
     * @param labelIdKeys 指定的标签主键组成的列表。
     * @return 关联是否全部不存在。
     * @throws DaoException 数据访问层异常。
     */
    boolean existsNonLabelRelations(LongIdKey itemIdKey, List<StringIdKey> labelIdKeys) throws DaoException;

    /**
     * 添加项目与标签的关联。
     *
     * @param itemIdKey   指定的项目主键。
     * @param labelIdKeys 指定的标签主键组成的列表。
     * @throws DaoException 数据访问层异常。
     */
    void batchAddLabelRelations(LongIdKey itemIdKey, List<StringIdKey> labelIdKeys) throws DaoException;

    /**
     * 删除项目与标签的关联。
     *
     * @param itemIdKey   指定的项目主键。
     * @param labelIdKeys 指定的标签主键组成的列表。
     * @throws DaoException 数据访问层异常。
     */
    void batchDeleteLabelRelations(LongIdKey itemIdKey, List<StringIdKey> labelIdKeys) throws DaoException;
}
