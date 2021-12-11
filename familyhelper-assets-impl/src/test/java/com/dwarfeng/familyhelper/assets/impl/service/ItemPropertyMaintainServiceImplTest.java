package com.dwarfeng.familyhelper.assets.impl.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemProperty;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.familyhelper.assets.stack.service.ItemMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.ItemPropertyMaintainService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class ItemPropertyMaintainServiceImplTest {

    @Autowired
    private ItemPropertyMaintainService itemPropertyMaintainService;
    @Autowired
    private ItemMaintainService itemMaintainService;

    private List<ItemProperty> itemProperties;
    private Item item;

    @Before
    public void setUp() {
        itemProperties = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ItemProperty itemProperty = new ItemProperty(
                    new ItemPropertyKey(0L, "test.item_property." + i), "label", "value", "remark"
            );
            itemProperties.add(itemProperty);
            item = new Item(
                    null, null, null, "name", "item_type", new Date(), new Date(), new Date(), 0, "remark"
            );
        }
    }

    @After
    public void tearDown() {
        itemProperties.clear();
        item = null;
    }

    @Test
    public void test() throws Exception {
        try {
            item.setKey(itemMaintainService.insertOrUpdate(item));
            for (ItemProperty itemProperty : itemProperties) {
                itemProperty.getKey().setItemId(item.getKey().getLongId());
                itemPropertyMaintainService.insertOrUpdate(itemProperty);

                ItemProperty testItemProperty = itemPropertyMaintainService.get(itemProperty.getKey());
                assertEquals(BeanUtils.describe(itemProperty), BeanUtils.describe(testItemProperty));
                itemPropertyMaintainService.update(itemProperty);
                testItemProperty = itemPropertyMaintainService.get(itemProperty.getKey());
                assertEquals(BeanUtils.describe(itemProperty), BeanUtils.describe(testItemProperty));
            }
        } finally {
            for (ItemProperty itemProperty : itemProperties) {
                itemPropertyMaintainService.deleteIfExists(itemProperty.getKey());
            }
        }
    }
}
