package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCreateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 项目操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemOperateService extends Service {

    /**
     * 创建项目。
     *
     * @param userKey        项目的所有者的主键。
     * @param itemCreateInfo 项目的创建信息。
     * @return 生成的项目的主键。
     * @throws ServiceException 服务异常。
     */
    LongIdKey createItem(StringIdKey userKey, ItemCreateInfo itemCreateInfo) throws ServiceException;

    /**
     * 更新项目。
     *
     * @param userKey        项目的所有者的主键。
     * @param itemUpdateInfo 项目的更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateItem(StringIdKey userKey, ItemUpdateInfo itemUpdateInfo) throws ServiceException;

    /**
     * 删除项目。
     *
     * @param userKey 项目的所有者的主键。
     * @param itemKey 项目的主键。
     * @throws ServiceException 服务异常。
     */
    void removeItem(StringIdKey userKey, LongIdKey itemKey) throws ServiceException;
}
