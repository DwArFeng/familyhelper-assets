package com.dwarfeng.familyhelper.assets.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 项目文件不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemFileNotExistsException extends HandlerException {

    private static final long serialVersionUID = 7768051936269379068L;

    private final LongIdKey itemFileKey;

    public ItemFileNotExistsException(LongIdKey itemFileKey) {
        this.itemFileKey = itemFileKey;
    }

    public ItemFileNotExistsException(Throwable cause, LongIdKey itemFileKey) {
        super(cause);
        this.itemFileKey = itemFileKey;
    }

    @Override
    public String getMessage() {
        return "项目文件 " + itemFileKey + " 不存在";
    }
}
