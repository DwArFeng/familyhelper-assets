package com.dwarfeng.familyhelper.assets.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 12000;

    public static final ServiceException.Code ASSET_CATALOG_NOT_EXISTS =
            new ServiceException.Code(offset(0), "asset catalog not exists");
    public static final ServiceException.Code USER_NOT_EXISTS =
            new ServiceException.Code(offset(10), "user not exists");
    public static final ServiceException.Code USER_NOT_PERMITTED =
            new ServiceException.Code(offset(20), "user not permitted");
    public static final ServiceException.Code INVALID_PERMISSION_LEVEL =
            new ServiceException.Code(offset(30), "invalid permission level");
    public static final ServiceException.Code ITEM_NOT_EXISTS =
            new ServiceException.Code(offset(40), "item not exists");
    public static final ServiceException.Code ILLEGAL_ITEM_STATE =
            new ServiceException.Code(offset(50), "illegal item state");
    public static final ServiceException.Code ITEM_LABEL_NOT_EXISTS =
            new ServiceException.Code(offset(60), "item label not exists");
    public static final ServiceException.Code ITEM_COVER_NOT_EXISTS =
            new ServiceException.Code(offset(70), "item cover not exists");
    public static final ServiceException.Code ITEM_COVER_TRANSPORT_FAILED =
            new ServiceException.Code(offset(80), "item cover transport failed");
    public static final ServiceException.Code ILLEGAL_ITEM_COVER_STATE =
            new ServiceException.Code(offset(90), "illegal item cover state");
    public static final ServiceException.Code ITEM_FILE_NOT_EXISTS =
            new ServiceException.Code(offset(100), "item file not exists");
    public static final ServiceException.Code ITEM_FILE_TRANSPORT_FAILED =
            new ServiceException.Code(offset(110), "item file transport failed");
    public static final ServiceException.Code ILLEGAL_ITEM_FILE_STATE =
            new ServiceException.Code(offset(120), "illegal item file state");

    private static int offset(int i) {
        return EXCEPTION_CODE_OFFSET + i;
    }

    /**
     * 获取异常代号的偏移量。
     *
     * @return 异常代号的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代号的偏移量。
     *
     * @param exceptionCodeOffset 指定的异常代号的偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        // 设置 EXCEPTION_CODE_OFFSET 的值。
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;

        // 以新的 EXCEPTION_CODE_OFFSET 为基准，更新异常代码的值。
        ASSET_CATALOG_NOT_EXISTS.setCode(offset(0));
        USER_NOT_EXISTS.setCode(offset(10));
        USER_NOT_PERMITTED.setCode(offset(20));
        INVALID_PERMISSION_LEVEL.setCode(offset(30));
        ITEM_NOT_EXISTS.setCode(offset(40));
        ILLEGAL_ITEM_STATE.setCode(offset(50));
        ITEM_LABEL_NOT_EXISTS.setCode(offset(60));
        ITEM_COVER_NOT_EXISTS.setCode(offset(70));
        ITEM_COVER_TRANSPORT_FAILED.setCode(offset(80));
        ILLEGAL_ITEM_COVER_STATE.setCode(offset(90));
        ITEM_FILE_NOT_EXISTS.setCode(offset(100));
        ITEM_FILE_TRANSPORT_FAILED.setCode(offset(110));
        ILLEGAL_ITEM_FILE_STATE.setCode(offset(120));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
