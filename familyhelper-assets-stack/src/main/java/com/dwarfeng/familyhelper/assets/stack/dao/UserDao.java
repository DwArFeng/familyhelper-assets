package com.dwarfeng.familyhelper.assets.stack.dao;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.User;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;

/**
 * 用户数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface UserDao extends BatchBaseDao<StringIdKey, User> {
}
