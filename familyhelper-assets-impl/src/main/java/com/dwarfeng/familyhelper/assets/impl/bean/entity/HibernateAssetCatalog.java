package com.dwarfeng.familyhelper.assets.impl.bean.entity;

import com.dwarfeng.familyhelper.assets.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_asset_catalog", indexes = {
        @Index(name = "idx_name", columnList = "name")
})
public class HibernateAssetCatalog implements Bean {

    private static final long serialVersionUID = 8322016824030427941L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "card_type", length = Constraints.LENGTH_TYPE)
    private String cardType;

    @Column(name = "recorded_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastRecordedDate;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateItem.class, mappedBy = "assetCatalog")
    private Set<HibernateItem> items = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernatePoac.class, mappedBy = "assetCatalog")
    private Set<HibernatePoac> poacs = new HashSet<>();

    public HibernateAssetCatalog() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Date getLastRecordedDate() {
        return lastRecordedDate;
    }

    public void setLastRecordedDate(Date lastRecordedDate) {
        this.lastRecordedDate = lastRecordedDate;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<HibernateItem> getItems() {
        return items;
    }

    public void setItems(Set<HibernateItem> items) {
        this.items = items;
    }

    public Set<HibernatePoac> getPoacs() {
        return poacs;
    }

    public void setPoacs(Set<HibernatePoac> poacs) {
        this.poacs = poacs;
    }


}
