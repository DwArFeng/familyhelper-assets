package com.dwarfeng.familyhelper.assets.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 项目状态非法异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class IllegalItemStateException extends HandlerException {

    private static final long serialVersionUID = -2632725773622683225L;

    private final LongIdKey itemKey;

    public IllegalItemStateException(LongIdKey itemKey) {
        this.itemKey = itemKey;
    }

    public IllegalItemStateException(Throwable cause, LongIdKey itemKey) {
        super(cause);
        this.itemKey = itemKey;
    }

    @Override
    public String getMessage() {
        return "项目 " + itemKey + " 状态异常: 它是否没绑定资产目录?";
    }
}
