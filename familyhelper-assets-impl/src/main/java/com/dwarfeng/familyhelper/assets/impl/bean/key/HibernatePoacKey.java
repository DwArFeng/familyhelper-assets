package com.dwarfeng.familyhelper.assets.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.Bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * Hibernate 资产目录权限主键。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class HibernatePoacKey implements Bean, Serializable {

    private static final long serialVersionUID = -6288527652406691801L;

    private Long longId;
    private String stringId;

    public HibernatePoacKey() {
    }

    public HibernatePoacKey(Long longId, String stringId) {
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

        HibernatePoacKey that = (HibernatePoacKey) o;

        if (!Objects.equals(longId, that.longId)) return false;
        return Objects.equals(stringId, that.stringId);
    }

    @Override
    public int hashCode() {
        int result = longId != null ? longId.hashCode() : 0;
        result = 31 * result + (stringId != null ? stringId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HibernatePoacKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
