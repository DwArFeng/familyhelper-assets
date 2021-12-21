package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.sdk.util.Constraints;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

/**
 * WebInput 资产目录。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputAssetCatalog implements Bean {

    private static final long serialVersionUID = -5660476280088103005L;

    public static AssetCatalog toStackBean(WebInputAssetCatalog webInputAssetCatalog) {
        return new AssetCatalog(
                WebInputLongIdKey.toStackBean(webInputAssetCatalog.getKey()), webInputAssetCatalog.getName(),
                webInputAssetCatalog.getRemark(), webInputAssetCatalog.getItemCount(),
                webInputAssetCatalog.getCreatedDate()
        );
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "remark")
    private String remark;

    @JSONField(name = "item_count")
    @PositiveOrZero
    private int itemCount;

    @JSONField(name = "created_date")
    private Date createdDate;

    public WebInputAssetCatalog() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
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

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "WebInputAssetCatalog{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", itemCount=" + itemCount +
                ", createdDate=" + createdDate +
                '}';
    }
}
