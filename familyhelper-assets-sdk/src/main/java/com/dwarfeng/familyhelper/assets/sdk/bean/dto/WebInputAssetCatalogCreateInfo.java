package com.dwarfeng.familyhelper.assets.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.AssetCatalogCreateInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 资产目录创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputAssetCatalogCreateInfo implements Dto {

    private static final long serialVersionUID = -9109939225673783798L;

    public static AssetCatalogCreateInfo toStackBean(WebInputAssetCatalogCreateInfo webInputAssetCatalogCreateInfo) {
        if (Objects.isNull(webInputAssetCatalogCreateInfo)) {
            return null;
        } else {
            return new AssetCatalogCreateInfo(
                    webInputAssetCatalogCreateInfo.getName(),
                    webInputAssetCatalogCreateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "remark")
    private String remark;

    public WebInputAssetCatalogCreateInfo() {
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
        return "WebInputAssetCatalogCreateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
