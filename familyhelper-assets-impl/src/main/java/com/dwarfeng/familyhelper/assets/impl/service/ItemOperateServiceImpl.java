package com.dwarfeng.familyhelper.assets.impl.service;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCreateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.handler.ItemOperateHandler;
import com.dwarfeng.familyhelper.assets.stack.service.ItemOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class ItemOperateServiceImpl implements ItemOperateService {

    private final ItemOperateHandler itemOperateHandler;

    private final ServiceExceptionMapper sem;

    public ItemOperateServiceImpl(ItemOperateHandler itemOperateHandler, ServiceExceptionMapper sem) {
        this.itemOperateHandler = itemOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createItem(StringIdKey userKey, ItemCreateInfo itemCreateInfo)
            throws ServiceException {
        try {
            return itemOperateHandler.createItem(userKey, itemCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("创建项目时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateItem(StringIdKey userKey, ItemUpdateInfo itemUpdateInfo)
            throws ServiceException {
        try {
            itemOperateHandler.updateItem(userKey, itemUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新项目时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeItem(StringIdKey userKey, LongIdKey itemKey) throws ServiceException {
        try {
            itemOperateHandler.removeItem(userKey, itemKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除项目时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
