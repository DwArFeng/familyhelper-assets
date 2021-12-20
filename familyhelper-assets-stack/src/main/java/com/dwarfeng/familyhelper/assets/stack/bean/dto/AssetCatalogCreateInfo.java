package com.dwarfeng.familyhelper.assets.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 资产目录创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class AssetCatalogCreateInfo implements Dto {

    private static final long serialVersionUID = 7015396689494275402L;

    private String name;
    private String remark;

    public AssetCatalogCreateInfo() {
    }

    public AssetCatalogCreateInfo(String name, String remark) {
        this.name = name;
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AssetCatalogCreateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
