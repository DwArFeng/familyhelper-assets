package com.dwarfeng.familyhelper.assets.impl.dao.preset;

import com.dwarfeng.familyhelper.assets.stack.service.ItemLabelMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class ItemLabelPresetCriteriaMaker implements PresetCriteriaMaker {

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case ItemLabelMaintainService.CHILD_FOR_ITEM:
                childForItem(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void childForItem(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isEmpty("items"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.createAlias("items", "i");
                detachedCriteria.add(Restrictions.eqOrIsNull("i.longId", longIdKey.getLongId()));
            }
            detachedCriteria.addOrder(Order.asc("stringId"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
