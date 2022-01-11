package com.dwarfeng.familyhelper.assets.impl.dao.preset;

import com.dwarfeng.familyhelper.assets.stack.service.ItemFileInfoMaintainService;
import com.dwarfeng.subgrade.sdk.hibernate.criteria.PresetCriteriaMaker;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Component
public class ItemFileInfoPresetCriteriaMaker implements PresetCriteriaMaker {

    @Override
    public void makeCriteria(DetachedCriteria detachedCriteria, String s, Object[] objects) {
        switch (s) {
            case ItemFileInfoMaintainService.CHILD_FOR_ITEM:
                childForItem(detachedCriteria, objects);
                break;
            case ItemFileInfoMaintainService.CHILD_FOR_ITEM_ORIGIN_NAME_ASC:
                childForItemOriginNameAsc(detachedCriteria, objects);
                break;
            case ItemFileInfoMaintainService.CHILD_FOR_ITEM_ORIGIN_NAME_DESC:
                childForItemOriginNameDesc(detachedCriteria, objects);
                break;
            case ItemFileInfoMaintainService.CHILD_FOR_ITEM_CREATED_DATE_ASC:
                childForItemCreatedDateAsc(detachedCriteria, objects);
                break;
            case ItemFileInfoMaintainService.CHILD_FOR_ITEM_CREATED_DATE_DESC:
                childForItemCreatedDateDesc(detachedCriteria, objects);
                break;
            case ItemFileInfoMaintainService.CHILD_FOR_ITEM_MODIFIED_DATE_ASC:
                childForItemModifiedDateAsc(detachedCriteria, objects);
                break;
            case ItemFileInfoMaintainService.CHILD_FOR_ITEM_MODIFIED_DATE_DESC:
                childForItemModifiedDateDesc(detachedCriteria, objects);
                break;
            case ItemFileInfoMaintainService.CHILD_FOR_ITEM_INSPECTED_DATE_ASC:
                childForItemInspectedDateAsc(detachedCriteria, objects);
                break;
            case ItemFileInfoMaintainService.CHILD_FOR_ITEM_INSPECTED_DATE_DESC:
                childForItemInspectedDateDesc(detachedCriteria, objects);
                break;
            default:
                throw new IllegalArgumentException("无法识别的预设: " + s);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void childForItem(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("itemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("itemLongId", longIdKey.getLongId())
                );
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForItemOriginNameAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("itemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("itemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.asc("originName"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForItemOriginNameDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("itemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("itemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("originName"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForItemCreatedDateAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("itemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("itemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.asc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForItemCreatedDateDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("itemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("itemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("createdDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForItemModifiedDateAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("itemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("itemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.asc("modifiedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForItemModifiedDateDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("itemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("itemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("modifiedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForItemInspectedDateAsc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("itemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("itemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.asc("inspectedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }

    private void childForItemInspectedDateDesc(DetachedCriteria detachedCriteria, Object[] objects) {
        try {
            if (Objects.isNull(objects[0])) {
                detachedCriteria.add(Restrictions.isNull("itemLongId"));
            } else {
                LongIdKey longIdKey = (LongIdKey) objects[0];
                detachedCriteria.add(
                        Restrictions.eqOrIsNull("itemLongId", longIdKey.getLongId())
                );
            }
            detachedCriteria.addOrder(Order.desc("inspectedDate"));
        } catch (Exception e) {
            throw new IllegalArgumentException("非法的参数:" + Arrays.toString(objects));
        }
    }
}
