package com.dwarfeng.familyhelper.assets.stack.handler;

import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCover;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCoverUploadInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 项目封面操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface ItemCoverOperateHandler extends Handler {

    /**
     * 下载项目封面。
     *
     * @param userKey      执行用户主键。
     * @param itemCoverKey 项目封面主键。
     * @return 项目封面。
     * @throws HandlerException 处理器异常。
     */
    ItemCover downloadItemCover(StringIdKey userKey, LongIdKey itemCoverKey) throws HandlerException;

    /**
     * 上传项目封面。
     *
     * @param userKey             执行用户主键。
     * @param itemCoverUploadInfo 项目封面上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadItemCover(StringIdKey userKey, ItemCoverUploadInfo itemCoverUploadInfo) throws HandlerException;

    /**
     * 删除项目封面。
     *
     * @param userKey      执行用户主键。
     * @param itemCoverKey 项目封面主键。
     * @throws HandlerException 处理器异常。
     */
    void removeItemCover(StringIdKey userKey, LongIdKey itemCoverKey) throws HandlerException;
}
