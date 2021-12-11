package com.dwarfeng.familyhelper.assets.stack.dao;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemProperty;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 项目属性数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemPropertyDao extends BatchBaseDao<ItemPropertyKey, ItemProperty>, EntireLookupDao<ItemProperty>,
        PresetLookupDao<ItemProperty> {
}
