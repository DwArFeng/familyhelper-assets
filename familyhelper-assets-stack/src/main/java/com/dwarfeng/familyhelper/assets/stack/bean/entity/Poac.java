package com.dwarfeng.familyhelper.assets.stack.bean.entity;

import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 资产目录权限。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Poac implements Entity<PoacKey> {

    private static final long serialVersionUID = -7960262894409709506L;

    private PoacKey key;
    private int permissionLevel;
    private String remark;

    public Poac() {
    }

    public Poac(PoacKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;

    }

    @Override
    public PoacKey getKey() {
        return key;
    }

    @Override
    public void setKey(PoacKey key) {
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
        return "Poac{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
