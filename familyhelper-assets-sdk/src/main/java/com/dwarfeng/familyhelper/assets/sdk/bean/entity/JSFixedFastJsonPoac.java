package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.sdk.bean.key.JSFixedFastJsonPoacKey;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Poac;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 账本权限。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonPoac implements Bean {

    private static final long serialVersionUID = -1397697380338130663L;

    public static JSFixedFastJsonPoac of(Poac poac) {
        if (Objects.isNull(poac)) {
            return null;
        } else {
            return new JSFixedFastJsonPoac(
                    JSFixedFastJsonPoacKey.of(poac.getKey()), poac.getPermissionLevel(), poac.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonPoacKey key;

    @JSONField(name = "permission_level", ordinal = 2)
    private int permissionLevel;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonPoac() {
    }

    public JSFixedFastJsonPoac(JSFixedFastJsonPoacKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;
    }

    public JSFixedFastJsonPoacKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonPoacKey key) {
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
        return "JSFixedFastJsonPoac{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
