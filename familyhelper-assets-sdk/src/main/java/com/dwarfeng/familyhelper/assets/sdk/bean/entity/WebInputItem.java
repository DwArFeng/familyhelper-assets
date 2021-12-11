package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.sdk.util.Constraints;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

/**
 * WebInput 项目。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputItem implements Bean {

    private static final long serialVersionUID = -7745854803218860294L;

    public static Item toStackBean(WebInputItem webInputItem) {
        return new Item(
                WebInputLongIdKey.toStackBean(webInputItem.getKey()),
                WebInputLongIdKey.toStackBean(webInputItem.getParentKey()),
                WebInputLongIdKey.toStackBean(webInputItem.getAssetCatalogKey()),
                webInputItem.getName(), webInputItem.getType(), webInputItem.getCreatedDate(),
                webInputItem.getModifiedDate(), webInputItem.getScrappedDate(), webInputItem.getLifeCycle(),
                webInputItem.getRemark()
        );
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "parent_key")
    @Valid
    private WebInputLongIdKey parentKey;

    @JSONField(name = "asset_catalog_key")
    @Valid
    private WebInputLongIdKey assetCatalogKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "type")
    private String type;

    @JSONField(name = "created_date")
    @Null
    private Date createdDate;

    @JSONField(name = "modified_date")
    @Null
    private Date modifiedDate;

    @JSONField(name = "scrapped_date")
    @Null
    private Date scrappedDate;

    @JSONField(name = "life_cycle")
    @Null
    private Integer lifeCycle;

    @JSONField(name = "remark")
    private String remark;

    public WebInputItem() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
    }

    public WebInputLongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(WebInputLongIdKey parentKey) {
        this.parentKey = parentKey;
    }

    public WebInputLongIdKey getAssetCatalogKey() {
        return assetCatalogKey;
    }

    public void setAssetCatalogKey(WebInputLongIdKey assetCatalogKey) {
        this.assetCatalogKey = assetCatalogKey;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getScrappedDate() {
        return scrappedDate;
    }

    public void setScrappedDate(Date scrappedDate) {
        this.scrappedDate = scrappedDate;
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
        return "WebInputItem{" +
                "key=" + key +
                ", parentKey=" + parentKey +
                ", assetCatalogKey=" + assetCatalogKey +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", scrappedDate=" + scrappedDate +
                ", lifeCycle=" + lifeCycle +
                ", remark='" + remark + '\'' +
                '}';
    }
}
