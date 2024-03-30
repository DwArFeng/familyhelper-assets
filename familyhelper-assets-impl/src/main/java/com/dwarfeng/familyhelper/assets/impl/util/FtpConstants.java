package com.dwarfeng.familyhelper.assets.impl.util;

/**
 * FTP 常量。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class FtpConstants {

    /**
     * @since 1.0.8
     */
    public static final String[] PATH_ITEM_COVER = new String[]{"familyhelper-assets", "item-cover"};

    /**
     * @since 1.0.8
     */
    public static final String[] PATH_ITEM_FILE = new String[]{"familyhelper-assets", "item-file"};

    private FtpConstants() {
        throw new IllegalStateException("禁止实例化");
    }
}
