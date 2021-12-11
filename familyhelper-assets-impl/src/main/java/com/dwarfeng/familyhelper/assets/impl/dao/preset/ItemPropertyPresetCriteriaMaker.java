package com.dwarfeng.familyhelper.assets.impl.dao.preset;

import com.dwarfeng.familyhelper.assets.stack.service.ItemPropertyMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class ItemPropertyPresetCriteriaMaker implements PresetCriteriaMaker {

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case ItemPropertyMaintainService.ITEM_ID_EQUALS:
                itemIdEquals(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    private void itemIdEquals(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            Long longId = getLongId(objects);
            detachedCriteria.add(Restrictions.eq("itemId", longId));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private Long getLongId(Object[] objects) {
        if (objects.length < 1 || Objects.isNull(objects[0])) {
            return null;
        }
        Object target = objects[0];
        if (target instanceof Long) {
            return (Long) target;
        }
        if (target instanceof LongIdKey) {
            return ((LongIdKey) target).getLongId();
        }
        throw new IllegalArgumentException();
    }
}
