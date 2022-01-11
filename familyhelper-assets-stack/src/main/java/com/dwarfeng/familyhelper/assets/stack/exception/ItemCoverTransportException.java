package com.dwarfeng.familyhelper.assets.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 项目封面传输异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemCoverTransportException extends HandlerException {

    private static final long serialVersionUID = -2579644298543430627L;

    public ItemCoverTransportException() {
    }

    public ItemCoverTransportException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "项目封面传输异常";
    }
}
