package com.dwarfeng.familyhelper.assets.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 项目文件状态非法异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class IllegalItemFileStateException extends HandlerException {

    private static final long serialVersionUID = 3628285295008935709L;

    private final LongIdKey itemFileKey;

    public IllegalItemFileStateException(LongIdKey itemFileKey) {
        this.itemFileKey = itemFileKey;
    }

    public IllegalItemFileStateException(Throwable cause, LongIdKey itemFileKey) {
        super(cause);
        this.itemFileKey = itemFileKey;
    }

    @Override
    public String getMessage() {
        return "项目文件 " + itemFileKey + " 状态异常: 它是否没绑定项目?";
    }
}
