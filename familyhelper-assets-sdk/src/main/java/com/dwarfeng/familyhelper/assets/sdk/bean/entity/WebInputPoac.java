package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.sdk.bean.key.WebInputPoacKey;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Poac;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 资产目录权限。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputPoac implements Bean {

    private static final long serialVersionUID = -3400700798762839591L;

    public static Poac toStackBean(WebInputPoac webInputPoac) {
        if (Objects.isNull(webInputPoac)) {
            return null;
        } else {
            return new Poac(
                    WebInputPoacKey.toStackBean(webInputPoac.getKey()),
                    webInputPoac.getPermissionLevel(),
                    webInputPoac.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    @Valid
    private WebInputPoacKey key;

    @JSONField(name = "permission_level", ordinal = 2)
    private int permissionLevel;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public WebInputPoac() {
    }

    public WebInputPoacKey getKey() {
        return key;
    }

    public void setKey(WebInputPoacKey key) {
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
        return "WebInputPoac{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
