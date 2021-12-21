package com.dwarfeng.familyhelper.assets.impl.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Item;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.ItemLabel;
import com.dwarfeng.familyhelper.assets.stack.service.AssetCatalogMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.ItemLabelMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.ItemMaintainService;
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
public class ItemMaintainServiceImplTest {

    @Autowired
    private ItemMaintainService itemMaintainService;
    @Autowired
    private AssetCatalogMaintainService assetCatalogMaintainService;
    @Autowired
    private ItemLabelMaintainService itemLabelMaintainService;

    private List<Item> items;
    private Item parent;
    private AssetCatalog assetCatalog;
    private List<ItemLabel> itemLabels;

    @Before
    public void setUp() {
        items = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Item item = new Item(
                    null, null, null, "name", "item_type", new Date(), new Date(), new Date(), 0, "remark"
            );
            items.add(item);
        }
        parent = new Item(
                null, null, null, "name", "item_type", new Date(), new Date(), new Date(), 0, "remark"
        );
        assetCatalog = new AssetCatalog(null, "name", "remark", 12450, new Date());
        itemLabels = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ItemLabel itemLabel = new ItemLabel(
                    new StringIdKey("test.item_label." + i), "label", "remark"
            );
            itemLabels.add(itemLabel);
        }
    }

    @After
    public void tearDown() {
        items.clear();
        parent = null;
        assetCatalog = null;
        itemLabels.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (Item item : items) {
                item.setKey(itemMaintainService.insert(item));

                Item testItem = itemMaintainService.get(item.getKey());
                assertEquals(BeanUtils.describe(item), BeanUtils.describe(testItem));
                itemMaintainService.update(item);
                testItem = itemMaintainService.get(item.getKey());
                assertEquals(BeanUtils.describe(item), BeanUtils.describe(testItem));
            }
        } finally {
            for (Item item : items) {
                itemMaintainService.deleteIfExists(item.getKey());
            }
        }
    }

    @Test
    public void testForParentCascade() throws Exception {
        try {
            parent.setKey(itemMaintainService.insertOrUpdate(parent));
            for (Item item : items) {
                item.setParentKey(parent.getKey());
                item.setKey(itemMaintainService.insert(item));
            }

            assertEquals(items.size(), itemMaintainService.lookup(
                    ItemMaintainService.CHILD_FOR_PARENT, new Object[]{parent.getKey()}
            ).getCount());

            itemMaintainService.deleteIfExists(parent.getKey());

            assertEquals(0, itemMaintainService.lookup(
                    ItemMaintainService.CHILD_FOR_PARENT, new Object[]{parent.getKey()}
            ).getCount());
        } finally {
            itemMaintainService.deleteIfExists(parent.getKey());
            for (Item item : items) {
                itemMaintainService.deleteIfExists(item.getKey());
            }
        }
    }

    @Test
    public void testForAssetCatalogCascade() throws Exception {
        try {
            assetCatalog.setKey(assetCatalogMaintainService.insertOrUpdate(assetCatalog));
            for (Item item : items) {
                item.setAssetCatalogKey(assetCatalog.getKey());
                item.setKey(itemMaintainService.insert(item));
            }

            assertEquals(items.size(), itemMaintainService.lookup(
                    ItemMaintainService.CHILD_FOR_ASSET_CATALOG, new Object[]{assetCatalog.getKey()}
            ).getCount());

            assetCatalogMaintainService.deleteIfExists(assetCatalog.getKey());

            assertEquals(0, itemMaintainService.lookup(
                    ItemMaintainService.CHILD_FOR_ASSET_CATALOG, new Object[]{assetCatalog.getKey()}
            ).getCount());
        } finally {
            assetCatalogMaintainService.deleteIfExists(assetCatalog.getKey());
            for (Item item : items) {
                itemMaintainService.deleteIfExists(item.getKey());
            }
        }
    }

    @Test
    public void testForLabelRelation() throws Exception {
        try {
            for (Item item : items) {
                item.setKey(itemMaintainService.insert(item));
            }
            for (ItemLabel itemLabel : itemLabels) {
                itemLabelMaintainService.insertOrUpdate(itemLabel);
            }
            List<StringIdKey> itemLabelKeys = itemLabels.stream().map(ItemLabel::getKey).collect(Collectors.toList());
            for (Item item : items) {
                itemMaintainService.batchAddLabelRelations(item.getKey(), itemLabelKeys);
            }

            // 判断关系是否存在。
            for (Item item : items) {
                for (StringIdKey itemLabelKey : itemLabelKeys) {
                    assertTrue(itemMaintainService.existsLabelRelation(item.getKey(), itemLabelKey));
                }
                assertTrue(itemMaintainService.existsAllLabelRelations(item.getKey(), itemLabelKeys));
                assertFalse(itemMaintainService.existsNonLabelRelations(item.getKey(), itemLabelKeys));
            }

            // 移除一个关系。
            StringIdKey firstItemLabelKey = itemLabelKeys.get(0);
            for (Item item : items) {
                itemMaintainService.deleteLabelRelation(item.getKey(), firstItemLabelKey);
            }

            // 判断关系。
            for (Item item : items) {
                for (StringIdKey itemLabelKey : itemLabelKeys) {
                    if (Objects.equals(itemLabelKey, firstItemLabelKey)) {
                        assertFalse(itemMaintainService.existsLabelRelation(item.getKey(), itemLabelKey));
                    } else {
                        assertTrue(itemMaintainService.existsLabelRelation(item.getKey(), itemLabelKey));
                    }
                }
                assertFalse(itemMaintainService.existsAllLabelRelations(item.getKey(), itemLabelKeys));
                assertFalse(itemMaintainService.existsNonLabelRelations(item.getKey(), itemLabelKeys));
            }

            // 全删除。
            for (Item item : items) {
                itemMaintainService.batchDeleteLabelRelations(item.getKey(), itemLabelKeys);
            }

            // 判断关系。
            for (Item item : items) {
                for (StringIdKey itemLabelKey : itemLabelKeys) {
                    assertFalse(itemMaintainService.existsLabelRelation(item.getKey(), itemLabelKey));
                }
                assertFalse(itemMaintainService.existsAllLabelRelations(item.getKey(), itemLabelKeys));
                assertTrue(itemMaintainService.existsNonLabelRelations(item.getKey(), itemLabelKeys));
            }
        } finally {
            for (Item item : items) {
                itemMaintainService.deleteIfExists(item.getKey());
            }
            for (ItemLabel itemLabel : itemLabels) {
                itemLabelMaintainService.deleteIfExists(itemLabel.getKey());
            }
        }
    }
}
