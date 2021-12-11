package com.dwarfeng.familyhelper.assets.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 资产目录权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class PoacKey implements Key {

    private static final long serialVersionUID = 8724483787801533842L;

    private Long longId;
    private String stringId;

    public PoacKey() {
    }

    public PoacKey(Long longId, String stringId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PoacKey poacKey = (PoacKey) o;

        if (!Objects.equals(longId, poacKey.longId)) return false;
        return Objects.equals(stringId, poacKey.stringId);
    }

    @Override
    public int hashCode() {
        int result = longId != null ? longId.hashCode() : 0;
        result = 31 * result + (stringId != null ? stringId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PoacKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
