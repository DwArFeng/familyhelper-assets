package com.dwarfeng.familyhelper.assets.impl.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.familyhelper.assets.stack.service.AssetCatalogMaintainService;
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
public class AssetCatalogMaintainServiceImplTest {

    @Autowired
    private AssetCatalogMaintainService assetCatalogMaintainService;

    private List<AssetCatalog> assetCatalogs;

    @Before
    public void setUp() {
        assetCatalogs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            AssetCatalog assetCatalog = new AssetCatalog(null, "name", "remark", 12450, new Date());
            assetCatalogs.add(assetCatalog);
        }
    }

    @After
    public void tearDown() {
        assetCatalogs.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (AssetCatalog assetCatalog : assetCatalogs) {
                assetCatalog.setKey(assetCatalogMaintainService.insert(assetCatalog));

                AssetCatalog testAssetCatalog = assetCatalogMaintainService.get(assetCatalog.getKey());
                assertEquals(BeanUtils.describe(assetCatalog), BeanUtils.describe(testAssetCatalog));
                assetCatalogMaintainService.update(assetCatalog);
                testAssetCatalog = assetCatalogMaintainService.get(assetCatalog.getKey());
                assertEquals(BeanUtils.describe(assetCatalog), BeanUtils.describe(testAssetCatalog));
            }
        } finally {
            for (AssetCatalog assetCatalog : assetCatalogs) {
                assetCatalogMaintainService.deleteIfExists(assetCatalog.getKey());
            }
        }
    }
}
