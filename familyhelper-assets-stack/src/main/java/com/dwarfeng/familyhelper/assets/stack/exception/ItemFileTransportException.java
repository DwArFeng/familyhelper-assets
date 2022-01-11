package com.dwarfeng.familyhelper.assets.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 项目文件传输异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemFileTransportException extends HandlerException {

    private static final long serialVersionUID = -3524952813195977935L;

    public ItemFileTransportException() {
    }

    public ItemFileTransportException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "项目文件传输异常";
    }
}
