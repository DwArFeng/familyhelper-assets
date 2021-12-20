package com.dwarfeng.familyhelper.assets.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 资产目录不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class AssetCatalogNotExistsException extends HandlerException {

    private static final long serialVersionUID = -2960166420874170695L;

    private final LongIdKey assetCatalogKey;

    public AssetCatalogNotExistsException(LongIdKey assetCatalogKey) {
        this.assetCatalogKey = assetCatalogKey;
    }

    public AssetCatalogNotExistsException(Throwable cause, LongIdKey assetCatalogKey) {
        super(cause);
        this.assetCatalogKey = assetCatalogKey;
    }

    @Override
    public String getMessage() {
        return "资产目录 " + assetCatalogKey + " 不存在";
    }
}
