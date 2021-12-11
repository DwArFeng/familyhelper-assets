package com.dwarfeng.familyhelper.assets.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.User;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 用户。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputUser implements Bean {

    private static final long serialVersionUID = -2680047427972185285L;

    public static User toStackBean(WebInputUser webInputUser) {
        if (Objects.isNull(webInputUser)) {
            return null;
        } else {
            return new User(
                    WebInputStringIdKey.toStackBean(webInputUser.getKey()),
                    webInputUser.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputStringIdKey key;

    @JSONField(name = "remark")
    private String remark;

    public WebInputUser() {
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
        this.key = key;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputUser{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                '}';
    }
}
