package com.dwarfeng.familyhelper.assets.impl.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemCoverInfo;
import com.dwarfeng.familyhelper.assets.stack.service.ItemCoverInfoMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.ItemMaintainService;
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
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class ItemCoverInfoMaintainServiceImplTest {

    @Autowired
    private ItemCoverInfoMaintainService itemCoverInfoMaintainService;
    @Autowired
    private ItemMaintainService itemMaintainService;

    private List<ItemCoverInfo> itemCoverInfos;
    private Item item;

    @Before
    public void setUp() {
        itemCoverInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ItemCoverInfo itemCoverInfo = new ItemCoverInfo(
                    null, null, "originName", 12450, new Date(), new Date(), "remark", 1
            );
            itemCoverInfos.add(itemCoverInfo);
        }
        item = new Item(
                null, null, null, "name", "item_type", new Date(), new Date(), new Date(), 0, "remark"
        );
    }

    @After
    public void tearDown() {
        itemCoverInfos.clear();
        item = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            item.setKey(itemMaintainService.insert(item));
            for (ItemCoverInfo itemCoverInfo : itemCoverInfos) {
                itemCoverInfo.setItemKey(item.getKey());
                itemCoverInfo.setKey(itemCoverInfoMaintainService.insert(itemCoverInfo));

                ItemCoverInfo testItemCoverInfo = itemCoverInfoMaintainService.get(itemCoverInfo.getKey());
                assertEquals(BeanUtils.describe(itemCoverInfo), BeanUtils.describe(testItemCoverInfo));
                itemCoverInfoMaintainService.update(itemCoverInfo);
                testItemCoverInfo = itemCoverInfoMaintainService.get(itemCoverInfo.getKey());
                assertEquals(BeanUtils.describe(itemCoverInfo), BeanUtils.describe(testItemCoverInfo));
            }
        } finally {
            for (ItemCoverInfo itemCoverInfo : itemCoverInfos) {
                itemCoverInfoMaintainService.deleteIfExists(itemCoverInfo.getKey());
            }
            itemMaintainService.deleteIfExists(item.getKey());
        }
    }

    @Test
    public void testForItemCascade() throws Exception {
        try {
            item.setKey(itemMaintainService.insert(item));
            for (ItemCoverInfo itemCoverInfo : itemCoverInfos) {
                itemCoverInfo.setItemKey(item.getKey());
                itemCoverInfo.setKey(itemCoverInfoMaintainService.insert(itemCoverInfo));
            }

            itemMaintainService.deleteIfExists(item.getKey());

            assertTrue(itemCoverInfoMaintainService.nonExists(
                    itemCoverInfos.stream().map(ItemCoverInfo::getKey).collect(Collectors.toList()))
            );
        } finally {
            for (ItemCoverInfo itemCoverInfo : itemCoverInfos) {
                itemCoverInfoMaintainService.deleteIfExists(itemCoverInfo.getKey());
            }
            itemMaintainService.deleteIfExists(item.getKey());
        }
    }
}
