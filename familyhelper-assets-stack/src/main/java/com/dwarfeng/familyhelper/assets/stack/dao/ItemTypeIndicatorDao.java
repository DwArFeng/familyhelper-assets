package com.dwarfeng.familyhelper.assets.stack.dao;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemTypeIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;

/**
 * 项目类型指示器数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemTypeIndicatorDao extends BatchBaseDao<StringIdKey, ItemTypeIndicator>,
        EntireLookupDao<ItemTypeIndicator> {
}
