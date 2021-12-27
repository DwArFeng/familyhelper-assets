package com.dwarfeng.familyhelper.assets.sdk.util;

/**
 * 常量类。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class Constants {

    public static final int LIFE_CYCLE_PREPARING = 0;
    public static final int LIFE_CYCLE_USING = 1;
    public static final int LIFE_CYCLE_DISABLED = 2;
    public static final int LIFE_CYCLE_SCRAPPED = 3;

    public static final int PERMISSION_LEVEL_OWNER = 0;
    public static final int PERMISSION_LEVEL_GUEST = 1;
    public static final int PERMISSION_LEVEL_MODIFIER = 2;

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}
