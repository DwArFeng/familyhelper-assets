package com.dwarfeng.familyhelper.assets.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.List;

/**
 * 项目封面顺序更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemCoverOrderUpdateInfo implements Dto {

    private static final long serialVersionUID = 8770681148401309110L;

    private List<LongIdKey> itemCoverKeys;

    public ItemCoverOrderUpdateInfo() {
    }

    public ItemCoverOrderUpdateInfo(List<LongIdKey> itemCoverKeys) {
        this.itemCoverKeys = itemCoverKeys;
    }

    public List<LongIdKey> getItemCoverKeys() {
        return itemCoverKeys;
    }

    public void setItemCoverKeys(List<LongIdKey> itemCoverKeys) {
        this.itemCoverKeys = itemCoverKeys;
    }

    @Override
    public String toString() {
        return "ItemCoverOrderUpdateInfo{" +
                "itemCoverKeys=" + itemCoverKeys +
                '}';
    }
}
