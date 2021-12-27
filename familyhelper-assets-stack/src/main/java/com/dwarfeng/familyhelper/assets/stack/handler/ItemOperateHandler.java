package com.dwarfeng.familyhelper.assets.stack.handler;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCreateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 项目操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemOperateHandler extends Handler {

    /**
     * 创建项目。
     *
     * @param userKey        项目的所有者的主键。
     * @param itemCreateInfo 项目的创建信息。
     * @return 生成的项目的主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey createItem(StringIdKey userKey, ItemCreateInfo itemCreateInfo) throws HandlerException;

    /**
     * 更新项目。
     *
     * @param userKey        项目的所有者的主键。
     * @param itemUpdateInfo 项目的更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateItem(StringIdKey userKey, ItemUpdateInfo itemUpdateInfo) throws HandlerException;

    /**
     * 删除项目。
     *
     * @param userKey 项目的所有者的主键。
     * @param itemKey 项目的主键。
     * @throws HandlerException 处理器异常。
     */
    void removeItem(StringIdKey userKey, LongIdKey itemKey) throws HandlerException;
}
