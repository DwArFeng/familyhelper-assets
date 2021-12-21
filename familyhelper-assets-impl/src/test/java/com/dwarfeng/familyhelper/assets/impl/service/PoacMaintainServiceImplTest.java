package com.dwarfeng.familyhelper.assets.impl.service;

import com.dwarfeng.familyhelper.assets.stack.bean.entity.AssetCatalog;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.Poac;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.User;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.familyhelper.assets.stack.service.AssetCatalogMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.PoacMaintainService;
import com.dwarfeng.familyhelper.assets.stack.service.UserMaintainService;
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

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class PoacMaintainServiceImplTest {

    private static final long ASSET_CATALOG_ID = 12450;
    private static final String USER_ID = "test_user";

    @Autowired
    private AssetCatalogMaintainService assetCatalogMaintainService;
    @Autowired
    private UserMaintainService userMaintainService;
    @Autowired
    private PoacMaintainService poacMaintainService;

    private AssetCatalog assetCatalog;
    private User user;
    private Poac poac;

    @Before
    public void setUp() {
        assetCatalog = new AssetCatalog(new LongIdKey(ASSET_CATALOG_ID), "name", "remark", 12450, new Date());
        user = new User(new StringIdKey(USER_ID), "remark");
        poac = new Poac(new PoacKey(ASSET_CATALOG_ID, USER_ID), 233, "remark");
    }

    @After
    public void tearDown() {
        assetCatalog = null;
        user = null;
        poac = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            assetCatalogMaintainService.insertOrUpdate(assetCatalog);
            userMaintainService.insertOrUpdate(user);
            poacMaintainService.insert(poac);
            poacMaintainService.update(poac);

            Poac testPoac = poacMaintainService.get(poac.getKey());
            assertEquals(BeanUtils.describe(poac), BeanUtils.describe(testPoac));
            testPoac = poacMaintainService.get(poac.getKey());
            assertEquals(BeanUtils.describe(poac), BeanUtils.describe(testPoac));
        } finally {
            assetCatalogMaintainService.deleteIfExists(assetCatalog.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            poacMaintainService.deleteIfExists(poac.getKey());
        }
    }

    @Test
    public void testForAssetCatalogCascade() throws Exception {
        try {
            assetCatalogMaintainService.insertOrUpdate(assetCatalog);
            userMaintainService.insertOrUpdate(user);
            poacMaintainService.insert(poac);

            assetCatalogMaintainService.deleteIfExists(assetCatalog.getKey());
            assertFalse(poacMaintainService.exists(poac.getKey()));
        } finally {
            assetCatalogMaintainService.deleteIfExists(assetCatalog.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            poacMaintainService.deleteIfExists(poac.getKey());
        }
    }

    @Test
    public void testForUserCascade() throws Exception {
        try {
            assetCatalogMaintainService.insertOrUpdate(assetCatalog);
            userMaintainService.insertOrUpdate(user);
            poacMaintainService.insert(poac);

            userMaintainService.deleteIfExists(user.getKey());
            assertFalse(poacMaintainService.exists(poac.getKey()));
        } finally {
            assetCatalogMaintainService.deleteIfExists(assetCatalog.getKey());
            userMaintainService.deleteIfExists(user.getKey());
            poacMaintainService.deleteIfExists(poac.getKey());
        }
    }
}
