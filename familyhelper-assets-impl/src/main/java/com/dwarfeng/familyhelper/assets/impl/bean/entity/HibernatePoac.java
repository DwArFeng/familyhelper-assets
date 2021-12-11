package com.dwarfeng.familyhelper.assets.impl.bean.entity;

import com.dwarfeng.familyhelper.assets.impl.bean.key.HibernatePoacKey;
import com.dwarfeng.familyhelper.assets.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernatePoacKey.class)
@Table(name = "tbl_poac")
public class HibernatePoac implements Bean {

    private static final long serialVersionUID = 864192530818965554L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "long_id", nullable = false)
    private Long longId;

    @Id
    @Column(name = "string_id", length = Constraints.LENGTH_USER, nullable = false)
    private String stringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "permission_level")
    private int permissionLevel;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateAssetCatalog.class)
    @JoinColumns({ //
            @JoinColumn(name = "long_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateAssetCatalog assetCatalog;

    @ManyToOne(targetEntity = HibernateUser.class)
    @JoinColumns({ //
            @JoinColumn(name = "string_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateUser user;

    public HibernatePoac() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernatePoacKey getKey() {
        return new HibernatePoacKey(longId, stringId);
    }

    public void setKey(HibernatePoacKey key) {
        if (Objects.isNull(key)) {
            this.longId = null;
            this.stringId = null;
        } else {
            this.longId = key.getLongId();
            this.stringId = key.getStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
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

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateAssetCatalog getAssetCatalog() {
        return assetCatalog;
    }

    public void setAssetCatalog(HibernateAssetCatalog assetCatalog) {
        this.assetCatalog = assetCatalog;
    }

    public HibernateUser getUser() {
        return user;
    }

    public void setUser(HibernateUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "stringId = " + stringId + ", " +
                "permissionLevel = " + permissionLevel + ", " +
                "remark = " + remark + ", " +
                "assetCatalog = " + assetCatalog + ", " +
                "user = " + user + ")";
    }
}
