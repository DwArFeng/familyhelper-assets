package com.dwarfeng.familyhelper.assets.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 项目不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemNotExistsException extends HandlerException {

    private static final long serialVersionUID = -8688476338203772210L;

    private final LongIdKey itemKey;

    public ItemNotExistsException(LongIdKey itemKey) {
        this.itemKey = itemKey;
    }

    public ItemNotExistsException(Throwable cause, LongIdKey itemKey) {
        super(cause);
        this.itemKey = itemKey;
    }

    @Override
    public String getMessage() {
        return "项目 " + itemKey + " 不存在";
    }
}
