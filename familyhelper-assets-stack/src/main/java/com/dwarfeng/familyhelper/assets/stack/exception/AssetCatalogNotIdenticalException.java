package com.dwarfeng.familyhelper.assets.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 资产目录不一致异常。
 *
 * @author DwArFeng
 * @since 1.0.4
 */
public class AssetCatalogNotIdenticalException extends HandlerException {

    private static final long serialVersionUID = -5948551237819394697L;

    private final LongIdKey parentAssetCatalogKey;
    private final LongIdKey childAssetCatalogKey;

    public AssetCatalogNotIdenticalException(LongIdKey parentAssetCatalogKey, LongIdKey childAssetCatalogKey) {
        this.parentAssetCatalogKey = parentAssetCatalogKey;
        this.childAssetCatalogKey = childAssetCatalogKey;
    }

    public AssetCatalogNotIdenticalException(
            Throwable cause, LongIdKey parentAssetCatalogKey, LongIdKey childAssetCatalogKey
    ) {
        super(cause);
        this.parentAssetCatalogKey = parentAssetCatalogKey;
        this.childAssetCatalogKey = childAssetCatalogKey;
    }

    @Override
    public String getMessage() {
        return "父项目的资产目录 " + parentAssetCatalogKey + " 与子项目的资产目录 " + childAssetCatalogKey + " 不一致";
    }
}
