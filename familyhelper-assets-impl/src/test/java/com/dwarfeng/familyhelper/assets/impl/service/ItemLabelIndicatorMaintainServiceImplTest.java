package com.dwarfeng.familyhelper.assets.impl.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemLabel;
import com.dwarfeng.familyhelper.assets.stack.service.ItemLabelMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.ItemMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class ItemLabelIndicatorMaintainServiceImplTest {

    @Autowired
    private ItemLabelMaintainService itemLabelMaintainService;
    @Autowired
    private ItemMaintainService itemMaintainService;

    private List<ItemLabel> itemLabels;
    private List<Item> items;

    @Before
    public void setUp() {
        itemLabels = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ItemLabel itemLabel = new ItemLabel(new StringIdKey("test.item_label." + i), "label", "remark");
            itemLabels.add(itemLabel);
        }
        items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Item item = new Item(
                    null, null, null, "name", "item_type", new Date(), new Date(), new Date(), 0, "remark"
            );
            items.add(item);
        }
    }

    @After
    public void tearDown() {
        itemLabels.clear();
        items.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (ItemLabel itemLabel : itemLabels) {
                itemLabel.setKey(itemLabelMaintainService.insert(itemLabel));

                ItemLabel testItemLabel = itemLabelMaintainService.get(itemLabel.getKey());
                assertEquals(BeanUtils.describe(itemLabel), BeanUtils.describe(testItemLabel));
                itemLabelMaintainService.update(itemLabel);
                testItemLabel = itemLabelMaintainService.get(itemLabel.getKey());
                assertEquals(BeanUtils.describe(itemLabel), BeanUtils.describe(testItemLabel));
            }
        } finally {
            for (ItemLabel itemLabel : itemLabels) {
                itemLabelMaintainService.deleteIfExists(itemLabel.getKey());
            }
        }
    }

    @Test
    public void testForItemRelation() throws Exception {
        try {
            for (ItemLabel itemLabel : itemLabels) {
                itemLabel.setKey(itemLabelMaintainService.insert(itemLabel));
            }
            for (Item item : items) {
                itemMaintainService.insertOrUpdate(item);
            }
            List<LongIdKey> itemKeys = items.stream().map(Item::getKey).collect(Collectors.toList());
            for (ItemLabel itemLabel : itemLabels) {
                itemLabelMaintainService.batchAddItemRelations(itemLabel.getKey(), itemKeys);
            }

            // 判断关系是否存在。
            for (ItemLabel itemLabel : itemLabels) {
                for (LongIdKey itemKey : itemKeys) {
                    assertTrue(itemLabelMaintainService.existsItemRelation(itemLabel.getKey(), itemKey));
                }
                assertTrue(itemLabelMaintainService.existsAllItemRelations(itemLabel.getKey(), itemKeys));
                assertFalse(itemLabelMaintainService.existsNonItemRelations(itemLabel.getKey(), itemKeys));
            }

            // 移除一个关系。
            LongIdKey firstItemKey = itemKeys.get(0);
            for (ItemLabel itemLabel : itemLabels) {
                itemLabelMaintainService.deleteItemRelation(itemLabel.getKey(), firstItemKey);
            }

            // 判断关系。
            for (ItemLabel itemLabel : itemLabels) {
                for (LongIdKey itemKey : itemKeys) {
                    if (Objects.equals(itemKey, firstItemKey)) {
                        assertFalse(itemLabelMaintainService.existsItemRelation(itemLabel.getKey(), itemKey));
                    } else {
                        assertTrue(itemLabelMaintainService.existsItemRelation(itemLabel.getKey(), itemKey));
                    }
                }
                assertFalse(itemLabelMaintainService.existsAllItemRelations(itemLabel.getKey(), itemKeys));
                assertFalse(itemLabelMaintainService.existsNonItemRelations(itemLabel.getKey(), itemKeys));
            }

            // 全删除。
            for (ItemLabel itemLabel : itemLabels) {
                itemLabelMaintainService.batchDeleteItemRelations(itemLabel.getKey(), itemKeys);
            }

            // 判断关系。
            for (ItemLabel itemLabel : itemLabels) {
                for (LongIdKey itemKey : itemKeys) {
                    assertFalse(itemLabelMaintainService.existsItemRelation(itemLabel.getKey(), itemKey));
                }
                assertFalse(itemLabelMaintainService.existsAllItemRelations(itemLabel.getKey(), itemKeys));
                assertTrue(itemLabelMaintainService.existsNonItemRelations(itemLabel.getKey(), itemKeys));
            }
        } finally {
            for (ItemLabel itemLabel : itemLabels) {
                itemLabelMaintainService.deleteIfExists(itemLabel.getKey());
            }
            for (Item item : items) {
                itemMaintainService.deleteIfExists(item.getKey());
            }
        }
    }
}
