package com.dwarfeng.familyhelper.assets.stack.handler;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 项目文件操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemFileOperateHandler extends Handler {

    /**
     * 下载项目文件。
     *
     * @param userKey     执行用户主键。
     * @param itemFileKey 项目文件主键。
     * @return 项目文件。
     * @throws HandlerException 处理器异常。
     */
    ItemFile downloadItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws HandlerException;

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
     * @throws HandlerException 处理器异常。
     * @since 1.1.8
     */
    ItemFileStream downloadItemFileStream(StringIdKey userKey, LongIdKey itemFileKey) throws HandlerException;

    /**
     * 上传项目文件。
     *
     * @param userKey    执行用户主键。
     * @param uploadInfo 项目文件上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadItemFile(StringIdKey userKey, ItemFileUploadInfo uploadInfo) throws HandlerException;

    /**
     * 上传项目文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>ItemFileStreamUploadInfo</code> 中的输入流，
     * 即其中的 <code>ItemFileStreamUploadInfo.content</code>。
     *
     * @param userKey    执行用户主键。
     * @param uploadInfo 项目文件流上传信息。
     * @throws HandlerException 处理器异常。
     * @since 1.1.8
     */
    void uploadItemFileStream(StringIdKey userKey, ItemFileStreamUploadInfo uploadInfo) throws HandlerException;

    /**
     * 更新项目文件。
     *
     * @param userKey    执行用户主键。
     * @param updateInfo 项目文件更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateItemFile(StringIdKey userKey, ItemFileUpdateInfo updateInfo) throws HandlerException;

    /**
     * 更新项目文件流。
     *
     * <p>
     * 调用者有责任关闭 <code>ItemFileStreamUpdateInfo</code> 中的输入流，
     * 即其中的 <code>ItemFileStreamUpdateInfo.content</code>。
     *
     * @param userKey    执行用户主键。
     * @param updateInfo 项目文件流更新信息。
     * @throws HandlerException 处理器异常。
     * @since 1.1.8
     */
    void updateItemFileStream(StringIdKey userKey, ItemFileStreamUpdateInfo updateInfo) throws HandlerException;

    /**
     * 删除项目文件。
     *
     * @param userKey     执行用户主键。
     * @param itemFileKey 项目文件主键。
     * @throws HandlerException 处理器异常。
     */
    void removeItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws HandlerException;
}
