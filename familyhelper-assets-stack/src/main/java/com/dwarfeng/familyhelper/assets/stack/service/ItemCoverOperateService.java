package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCover;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCoverUploadInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 项目封面操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemCoverOperateService extends Service {

    /**
     * 下载项目封面。
     *
     * @param userKey      执行用户主键。
     * @param itemCoverKey 项目封面主键。
     * @return 项目封面。
     * @throws ServiceException 服务异常。
     */
    ItemCover downloadItemCover(StringIdKey userKey, LongIdKey itemCoverKey) throws ServiceException;

    /**
     * 上传项目封面。
     *
     * @param userKey             执行用户主键。
     * @param itemCoverUploadInfo 项目封面上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadItemCover(StringIdKey userKey, ItemCoverUploadInfo itemCoverUploadInfo) throws ServiceException;

    /**
     * 删除项目封面。
     *
     * @param userKey      执行用户主键。
     * @param itemCoverKey 项目封面主键。
     * @throws ServiceException 服务异常。
     */
    void removeItemCover(StringIdKey userKey, LongIdKey itemCoverKey) throws ServiceException;
}
