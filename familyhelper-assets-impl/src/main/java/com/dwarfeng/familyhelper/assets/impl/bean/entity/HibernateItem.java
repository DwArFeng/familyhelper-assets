package com.dwarfeng.familyhelper.assets.impl.bean.entity;

import com.dwarfeng.familyhelper.assets.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_item")
public class HibernateItem implements Bean {

    private static final long serialVersionUID = 5466353365649120963L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "parent_long_id")
    private Long parentLongId;

    @Column(name = "asset_catalog_id")
    private Long assetCatalogLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "name", length = Constraints.LENGTH_NAME, nullable = false)
    private String name;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "scrapped_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date scrappedDate;

    @Column(name = "life_cycle")
    private Integer lifeCycle;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateItem.class)
    @JoinColumns({ //
            @JoinColumn(name = "parent_long_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateItem parent;

    @ManyToOne(targetEntity = HibernateAssetCatalog.class)
    @JoinColumns({ //
            @JoinColumn(name = "asset_catalog_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateAssetCatalog assetCatalog;

    // -----------------------------------------------------------多对多-----------------------------------------------------------
    @ManyToMany(targetEntity = HibernateItemLabel.class)
    @JoinTable(name = "tbl_item_has_label", joinColumns = { //
            @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false)//
    }, inverseJoinColumns = { //
            @JoinColumn(name = "label_id", referencedColumnName = "id", insertable = false, updatable = false),//
    })
    private Set<HibernateItemLabel> labels = new HashSet<>();

    public HibernateItem() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getParentKey() {
        return Optional.ofNullable(parentLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setParentKey(HibernateLongIdKey idKey) {
        this.parentLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getAssetCatalogKey() {
        return Optional.ofNullable(assetCatalogLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setAssetCatalogKey(HibernateLongIdKey idKey) {
        this.assetCatalogLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getParentLongId() {
        return parentLongId;
    }

    public void setParentLongId(Long parentLongId) {
        this.parentLongId = parentLongId;
    }

    public Long getAssetCatalogLongId() {
        return assetCatalogLongId;
    }

    public void setAssetCatalogLongId(Long assetCatalogLongId) {
        this.assetCatalogLongId = assetCatalogLongId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
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

    public HibernateItem getParent() {
        return parent;
    }

    public void setParent(HibernateItem parent) {
        this.parent = parent;
    }

    public HibernateAssetCatalog getAssetCatalog() {
        return assetCatalog;
    }

    public void setAssetCatalog(HibernateAssetCatalog assetCatalog) {
        this.assetCatalog = assetCatalog;
    }

    public Set<HibernateItemLabel> getLabels() {
        return labels;
    }

    public void setLabels(Set<HibernateItemLabel> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "parentLongId = " + parentLongId + ", " +
                "assetCatalogLongId = " + assetCatalogLongId + ", " +
                "name = " + name + ", " +
                "itemType = " + itemType + ", " +
                "createdDate = " + createdDate + ", " +
                "modifiedDate = " + modifiedDate + ", " +
                "scrappedDate = " + scrappedDate + ", " +
                "lifeCycle = " + lifeCycle + ", " +
                "remark = " + remark + ", " +
                "parent = " + parent + ", " +
                "assetCatalog = " + assetCatalog + ")";
    }
}
