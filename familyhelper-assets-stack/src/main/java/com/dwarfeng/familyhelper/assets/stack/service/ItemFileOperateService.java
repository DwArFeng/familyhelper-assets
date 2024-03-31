package com.dwarfeng.familyhelper.assets.stack.service;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.*;
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
     * 下载项目文件流。
     *
     * <p>
     * 如果返回的 <code>ItemFileStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>ItemFileStream</code> 中的输入流，即其中的 <code>ItemFileStream.content</code>。
     *
     * @param userKey     执行用户主键。
     * @param itemFileKey 项目文件主键。
     * @return 项目文件流。
     * @throws ServiceException 服务异常。
     * @since 1.1.8
     */
    ItemFileStream downloadItemFileStream(StringIdKey userKey, LongIdKey itemFileKey) throws ServiceException;

    /**
     * 上传项目文件。
     *
     * @param userKey    执行用户主键。
     * @param uploadInfo 上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadItemFile(StringIdKey userKey, ItemFileUploadInfo uploadInfo) throws ServiceException;

    /**
     * 上传项目文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>ItemFileStreamUploadInfo</code> 中的输入流，
     * 即其中的 <code>ItemFileStreamUploadInfo.content</code>。
     *
     * @param userKey    执行用户主键。
     * @param uploadInfo 上传信息。
     * @throws ServiceException 服务异常。
     * @since 1.1.8
     */
    void uploadItemFileStream(StringIdKey userKey, ItemFileStreamUploadInfo uploadInfo) throws ServiceException;

    /**
     * 更新项目文件。
     *
     * @param userKey    执行用户主键。
     * @param updateInfo 更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateItemFile(StringIdKey userKey, ItemFileUpdateInfo updateInfo) throws ServiceException;

    /**
     * 更新项目文件流。
     *
     * <p>
     * 调用者有责任关闭 <code>ItemFileStreamUpdateInfo</code> 中的输入流，
     * 即其中的 <code>ItemFileStreamUpdateInfo.content</code>。
     *
     * @param userKey    执行用户主键。
     * @param updateInfo 更新信息。
     * @throws ServiceException 服务异常。
     * @since 1.1.8
     */
    void updateItemFileStream(StringIdKey userKey, ItemFileStreamUpdateInfo updateInfo) throws ServiceException;

    /**
     * 删除项目文件。
     *
     * @param userKey     执行用户主键。
     * @param itemFileKey 项目文件主键。
     * @throws ServiceException 服务异常。
     */
    void removeItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws ServiceException;
}
