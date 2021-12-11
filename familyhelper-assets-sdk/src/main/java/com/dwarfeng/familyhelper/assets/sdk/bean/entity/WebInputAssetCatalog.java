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

/**
 * WebInput 资产目录。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputAssetCatalog implements Bean {

    private static final long serialVersionUID = -2866703785181045401L;

    public static AssetCatalog toStackBean(WebInputAssetCatalog webInputAssetCatalog) {
        return new AssetCatalog(
                WebInputLongIdKey.toStackBean(webInputAssetCatalog.getKey()),
                webInputAssetCatalog.getName(),
                webInputAssetCatalog.getRemark()
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

    @Override
    public String toString() {
        return "WebInputAssetCatalog{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
