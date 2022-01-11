package com.dwarfeng.familyhelper.assets.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 项目封面不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemCoverNotExistsException extends HandlerException {

    private static final long serialVersionUID = 1844198440734003633L;
    
    private final LongIdKey itemCoverKey;

    public ItemCoverNotExistsException(LongIdKey itemCoverKey) {
        this.itemCoverKey = itemCoverKey;
    }

    public ItemCoverNotExistsException(Throwable cause, LongIdKey itemCoverKey) {
        super(cause);
        this.itemCoverKey = itemCoverKey;
    }

    @Override
    public String getMessage() {
        return "项目封面 " + itemCoverKey + " 不存在";
    }
}
