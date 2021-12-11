package com.dwarfeng.familyhelper.assets.impl.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemTypeIndicator;
import com.dwarfeng.familyhelper.assets.stack.service.ItemTypeIndicatorMaintainService;
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
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class ItemTypeIndicatorMaintainServiceImplTest {

    @Autowired
    private ItemTypeIndicatorMaintainService itemTypeIndicatorMaintainService;

    private List<ItemTypeIndicator> itemTypeIndicators;

    @Before
    public void setUp() {
        itemTypeIndicators = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ItemTypeIndicator itemTypeIndicator = new ItemTypeIndicator(
                    new StringIdKey("fund_change_type_indicator_test" + i),
                    "label",
                    "remark"
            );
            itemTypeIndicators.add(itemTypeIndicator);
        }
    }

    @After
    public void tearDown() {
        itemTypeIndicators.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (ItemTypeIndicator itemTypeIndicator : itemTypeIndicators) {
                itemTypeIndicator.setKey(itemTypeIndicatorMaintainService.insert(itemTypeIndicator));

                ItemTypeIndicator testItemTypeIndicator = itemTypeIndicatorMaintainService.get(
                        itemTypeIndicator.getKey());
                assertEquals(BeanUtils.describe(itemTypeIndicator), BeanUtils.describe(testItemTypeIndicator));
                itemTypeIndicatorMaintainService.update(itemTypeIndicator);
                testItemTypeIndicator = itemTypeIndicatorMaintainService.get(itemTypeIndicator.getKey());
                assertEquals(BeanUtils.describe(itemTypeIndicator), BeanUtils.describe(testItemTypeIndicator));
            }
        } finally {
            for (ItemTypeIndicator itemTypeIndicator : itemTypeIndicators) {
                itemTypeIndicatorMaintainService.deleteIfExists(itemTypeIndicator.getKey());
            }
        }
    }
}
