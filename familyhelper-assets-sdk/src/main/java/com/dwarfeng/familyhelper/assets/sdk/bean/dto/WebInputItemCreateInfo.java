package com.dwarfeng.familyhelper.assets.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCreateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * WebInput 项目创建信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputItemCreateInfo implements Dto {

    private static final long serialVersionUID = 1362374940648098523L;

    public static ItemCreateInfo toStackBean(WebInputItemCreateInfo webInputItemCreateInfo) {
        if (Objects.isNull(webInputItemCreateInfo)) {
            return null;
        } else {
            return new ItemCreateInfo(
                    WebInputLongIdKey.toStackBean(webInputItemCreateInfo.getAssetCatalogKey()),
                    WebInputLongIdKey.toStackBean(webInputItemCreateInfo.getParentKey()),
                    webInputItemCreateInfo.getLabelKeys().stream().map(WebInputStringIdKey::toStackBean)
                            .collect(Collectors.toList()),
                    webInputItemCreateInfo.getName(),
                    webInputItemCreateInfo.getType(),
                    webInputItemCreateInfo.getLifeCycle(),
                    webInputItemCreateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "asset_catalog_key")
    @Valid
    @NotNull
    private WebInputLongIdKey assetCatalogKey;

    @JSONField(name = "parent_key")
    private WebInputLongIdKey parentKey;

    @JSONField(name = "label_keys")
    @Valid
    @NotNull
    private List<WebInputStringIdKey> labelKeys;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "type")
    private String type;

    @JSONField(name = "life_cycle")
    private Integer lifeCycle;

    @JSONField(name = "remark")
    private String remark;

    public WebInputItemCreateInfo() {
    }

    public WebInputLongIdKey getAssetCatalogKey() {
        return assetCatalogKey;
    }

    public void setAssetCatalogKey(WebInputLongIdKey assetCatalogKey) {
        this.assetCatalogKey = assetCatalogKey;
    }

    public WebInputLongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(WebInputLongIdKey parentKey) {
        this.parentKey = parentKey;
    }

    public List<WebInputStringIdKey> getLabelKeys() {
        return labelKeys;
    }

    public void setLabelKeys(List<WebInputStringIdKey> labelKeys) {
        this.labelKeys = labelKeys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(Integer lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputItemCreateInfo{" +
                "assetCatalogKey=" + assetCatalogKey +
                ", parentKey=" + parentKey +
                ", labelKeys=" + labelKeys +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", lifeCycle=" + lifeCycle +
                ", remark='" + remark + '\'' +
                '}';
    }
}
