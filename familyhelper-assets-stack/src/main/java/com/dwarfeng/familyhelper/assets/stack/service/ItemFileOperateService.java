package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemFile;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemFileUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemFileUploadInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 项目文件操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemFileOperateService extends Service {

    /**
     * 下载项目文件。
     *
     * @param userKey     执行用户主键。
     * @param itemFileKey 项目文件主键。
     * @return 项目文件。
     * @throws ServiceException 服务异常。
     */
    ItemFile downloadItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws ServiceException;

    /**
     * 上传项目文件。
     *
     * @param userKey            执行用户主键。
     * @param itemFileUploadInfo 项目文件上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadItemFile(StringIdKey userKey, ItemFileUploadInfo itemFileUploadInfo) throws ServiceException;

    /**
     * 更新项目文件。
     *
     * @param userKey            执行用户主键。
     * @param itemFileUpdateInfo 项目文件更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateItemFile(StringIdKey userKey, ItemFileUpdateInfo itemFileUpdateInfo) throws ServiceException;

    /**
     * 删除项目文件。
     *
     * @param userKey     执行用户主键。
     * @param itemFileKey 项目文件主键。
     * @throws ServiceException 服务异常。
     */
    void removeItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws ServiceException;
}
