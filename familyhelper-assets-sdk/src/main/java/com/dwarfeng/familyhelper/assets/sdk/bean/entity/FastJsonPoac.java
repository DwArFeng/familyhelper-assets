package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.sdk.bean.key.FastJsonPoacKey;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Poac;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 账本权限。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonPoac implements Bean {

    private static final long serialVersionUID = -5587018993454540929L;

    public static FastJsonPoac of(Poac poac) {
        if (Objects.isNull(poac)) {
            return null;
        } else {
            return new FastJsonPoac(FastJsonPoacKey.of(poac.getKey()), poac.getPermissionLevel(), poac.getRemark());
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonPoacKey key;

    @JSONField(name = "permission_level", ordinal = 2)
    private int permissionLevel;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonPoac() {
    }

    public FastJsonPoac(FastJsonPoacKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;
    }

    public FastJsonPoacKey getKey() {
        return key;
    }

    public void setKey(FastJsonPoacKey key) {
        this.key = key;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonPoac{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
