package com.dwarfeng.familyhelper.assets.stack.handler;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemFile;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemFileUpdateInfo;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemFileUploadInfo;
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
     * 上传项目文件。
     *
     * @param userKey            执行用户主键。
     * @param itemFileUploadInfo 项目文件上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadItemFile(StringIdKey userKey, ItemFileUploadInfo itemFileUploadInfo) throws HandlerException;

    /**
     * 更新项目文件。
     *
     * @param userKey            执行用户主键。
     * @param itemFileUpdateInfo 项目文件更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateItemFile(StringIdKey userKey, ItemFileUpdateInfo itemFileUpdateInfo) throws HandlerException;

    /**
     * 删除项目文件。
     *
     * @param userKey     执行用户主键。
     * @param itemFileKey 项目文件主键。
     * @throws HandlerException 处理器异常。
     */
    void removeItemFile(StringIdKey userKey, LongIdKey itemFileKey) throws HandlerException;
}
