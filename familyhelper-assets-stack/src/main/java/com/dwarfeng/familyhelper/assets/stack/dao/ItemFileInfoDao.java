package com.dwarfeng.familyhelper.assets.stack.dao;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 项目文件信息数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemFileInfoDao extends BatchBaseDao<LongIdKey, ItemFileInfo>, EntireLookupDao<ItemFileInfo>,
        PresetLookupDao<ItemFileInfo> {
}
