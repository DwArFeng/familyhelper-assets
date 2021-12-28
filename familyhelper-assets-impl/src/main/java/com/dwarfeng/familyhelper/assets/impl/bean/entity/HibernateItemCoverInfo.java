package com.dwarfeng.familyhelper.assets.impl.bean.entity;

import com.dwarfeng.familyhelper.assets.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_item_cover_info", indexes = {
        @Index(name = "idx_column_index", columnList = "item_id, column_index")
})
public class HibernateItemCoverInfo implements Bean {

    private static final long serialVersionUID = 5022607864182661796L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "item_id")
    private Long itemLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "origin_name")
    private String originName;

    @Column(name = "column_length")
    private long length;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    @Column(name = "column_index", nullable = false)
    private int index;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateItem.class)
    @JoinColumns({ //
            @JoinColumn(name = "item_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateItem item;

    public HibernateItemCoverInfo() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getItemKey() {
        return Optional.ofNullable(itemLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setItemKey(HibernateLongIdKey idKey) {
        this.itemLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public Long getItemLongId() {
        return itemLongId;
    }

    public void setItemLongId(Long itemLongId) {
        this.itemLongId = itemLongId;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public HibernateItem getItem() {
        return item;
    }

    public void setItem(HibernateItem item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "itemLongId = " + itemLongId + ", " +
                "originName = " + originName + ", " +
                "length = " + length + ", " +
                "createDate = " + createDate + ", " +
                "modifiedDate = " + modifiedDate + ", " +
                "remark = " + remark + ", " +
                "index = " + index + ", " +
                "item = " + item + ")";
    }
}
