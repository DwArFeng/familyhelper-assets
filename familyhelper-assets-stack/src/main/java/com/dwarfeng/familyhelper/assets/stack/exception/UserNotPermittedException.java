package com.dwarfeng.familyhelper.assets.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 用户对账本没有权限异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class UserNotPermittedException extends HandlerException {

    private static final long serialVersionUID = -123435884346027911L;

    private final StringIdKey userKey;
    private final LongIdKey assetCatalogKey;

    public UserNotPermittedException(StringIdKey userKey, LongIdKey assetCatalogKey) {
        this.userKey = userKey;
        this.assetCatalogKey = assetCatalogKey;
    }

    public UserNotPermittedException(Throwable cause, StringIdKey userKey, LongIdKey assetCatalogKey) {
        super(cause);
        this.userKey = userKey;
        this.assetCatalogKey = assetCatalogKey;
    }

    @Override
    public String getMessage() {
        return "用户 " + userKey + " 没有操作资产目录 " + assetCatalogKey + " 的权限";
    }
}
