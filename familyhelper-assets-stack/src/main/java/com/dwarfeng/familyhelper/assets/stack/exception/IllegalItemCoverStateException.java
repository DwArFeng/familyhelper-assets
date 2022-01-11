package com.dwarfeng.familyhelper.assets.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 项目封面状态非法异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class IllegalItemCoverStateException extends HandlerException {

    private static final long serialVersionUID = -7486276856396191801L;

    private final LongIdKey itemCoverKey;

    public IllegalItemCoverStateException(LongIdKey itemCoverKey) {
        this.itemCoverKey = itemCoverKey;
    }

    public IllegalItemCoverStateException(Throwable cause, LongIdKey itemCoverKey) {
        super(cause);
        this.itemCoverKey = itemCoverKey;
    }

    @Override
    public String getMessage() {
        return "项目封面 " + itemCoverKey + " 状态异常: 它是否与其它项目封面不一致，或是否没绑定项目?";
    }
}
