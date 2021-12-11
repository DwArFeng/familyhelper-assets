package com.dwarfeng.familyhelper.assets.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 资产目录权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonPoacKey implements Key {

    private static final long serialVersionUID = 841507015720043664L;

    public static FastJsonPoacKey of(PoacKey key) {
        if (Objects.isNull(key)) {
            return null;
        } else {
            return new FastJsonPoacKey(key.getLongId(), key.getStringId());
        }
    }

    @JSONField(name = "long_id", ordinal = 1)
    private Long longId;

    @JSONField(name = "string_id", ordinal = 2)
    private String stringId;

    public FastJsonPoacKey() {
    }

    public FastJsonPoacKey(Long longId, String stringId) {
        this.longId = longId;
        this.stringId = stringId;
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
        return "FastJsonPoacKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
