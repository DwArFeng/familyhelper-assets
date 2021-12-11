package com.dwarfeng.familyhelper.assets.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 资产目录权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputPoacKey implements Key {

    private static final long serialVersionUID = -3277734583191438314L;

    public static PoacKey toStackBean(WebInputPoacKey webInputPoacKey) {
        if (Objects.isNull(webInputPoacKey)) {
            return null;
        } else {
            return new PoacKey(webInputPoacKey.getLongId(), webInputPoacKey.getStringId());
        }
    }

    @JSONField(name = "long_id")
    @NotNull
    private Long longId;

    @JSONField(name = "string_id")
    @NotNull
    @NotEmpty
    private String stringId;

    public WebInputPoacKey() {
    }

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    @Override
    public String toString() {
        return "WebInputPoacKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
