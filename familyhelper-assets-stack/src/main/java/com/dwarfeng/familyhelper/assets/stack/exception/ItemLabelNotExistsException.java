package com.dwarfeng.familyhelper.assets.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 项目标签不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemLabelNotExistsException extends HandlerException {

    private static final long serialVersionUID = -4690808421702177210L;

    private final StringIdKey itemLabelKey;

    public ItemLabelNotExistsException(StringIdKey itemLabelKey) {
        this.itemLabelKey = itemLabelKey;
    }

    public ItemLabelNotExistsException(Throwable cause, StringIdKey itemLabelKey) {
        super(cause);
        this.itemLabelKey = itemLabelKey;
    }

    @Override
    public String getMessage() {
        return "项目标签 " + itemLabelKey + " 不存在";
    }
}
