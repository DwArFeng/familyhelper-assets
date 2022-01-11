package com.dwarfeng.familyhelper.assets.impl.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemFileInfo;
import com.dwarfeng.familyhelper.assets.stack.service.ItemFileInfoMaintainService;
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
public class ItemFileInfoMaintainServiceImplTest {

    @Autowired
    private ItemFileInfoMaintainService itemFileInfoMaintainService;
    @Autowired
    private ItemMaintainService itemMaintainService;

    private List<ItemFileInfo> itemFileInfos;
    private Item item;

    @Before
    public void setUp() {
        itemFileInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ItemFileInfo itemFileInfo = new ItemFileInfo(
                    null, null, "originName", 12450, new Date(), new Date(), new Date(), "remark"
            );
            itemFileInfos.add(itemFileInfo);
        }
        item = new Item(
                null, null, null, "name", "item_type", new Date(), new Date(), new Date(), 0, "remark"
        );
    }

    @After
    public void tearDown() {
        itemFileInfos.clear();
        item = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            item.setKey(itemMaintainService.insert(item));
            for (ItemFileInfo itemFileInfo : itemFileInfos) {
                itemFileInfo.setItemKey(item.getKey());
                itemFileInfo.setKey(itemFileInfoMaintainService.insert(itemFileInfo));

                ItemFileInfo testItemFileInfo = itemFileInfoMaintainService.get(itemFileInfo.getKey());
                assertEquals(BeanUtils.describe(itemFileInfo), BeanUtils.describe(testItemFileInfo));
                itemFileInfoMaintainService.update(itemFileInfo);
                testItemFileInfo = itemFileInfoMaintainService.get(itemFileInfo.getKey());
                assertEquals(BeanUtils.describe(itemFileInfo), BeanUtils.describe(testItemFileInfo));
            }
        } finally {
            for (ItemFileInfo itemFileInfo : itemFileInfos) {
                itemFileInfoMaintainService.deleteIfExists(itemFileInfo.getKey());
            }
            itemMaintainService.deleteIfExists(item.getKey());
        }
    }

    @Test
    public void testForItemCascade() throws Exception {
        try {
            item.setKey(itemMaintainService.insert(item));
            for (ItemFileInfo itemFileInfo : itemFileInfos) {
                itemFileInfo.setItemKey(item.getKey());
                itemFileInfo.setKey(itemFileInfoMaintainService.insert(itemFileInfo));
            }

            itemMaintainService.deleteIfExists(item.getKey());

            assertTrue(itemFileInfoMaintainService.nonExists(
                    itemFileInfos.stream().map(ItemFileInfo::getKey).collect(Collectors.toList()))
            );
        } finally {
            for (ItemFileInfo itemFileInfo : itemFileInfos) {
                itemFileInfoMaintainService.deleteIfExists(itemFileInfo.getKey());
            }
            itemMaintainService.deleteIfExists(item.getKey());
        }
    }
}
