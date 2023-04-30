package com.dwarfeng.familyhelper.assets.node.configuration;

import com.dwarfeng.familyhelper.assets.impl.bean.HibernateMapper;
import com.dwarfeng.familyhelper.assets.impl.bean.entity.*;
import com.dwarfeng.familyhelper.assets.impl.bean.key.HibernateItemPropertyKey;
import com.dwarfeng.familyhelper.assets.impl.bean.key.HibernatePoacKey;
import com.dwarfeng.familyhelper.assets.impl.dao.preset.*;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.*;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchRelationDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate template;

    private final AssetCatalogPresetCriteriaMaker assetCatalogPresetCriteriaMaker;
    private final ItemPresetCriteriaMaker itemPresetCriteriaMaker;
    private final ItemLabelPresetCriteriaMaker itemLabelPresetCriteriaMaker;
    private final ItemPropertyPresetCriteriaMaker itemPropertyPresetCriteriaMaker;
    private final PoacPresetCriteriaMaker poacPresetCriteriaMaker;
    private final ItemCoverInfoPresetCriteriaMaker itemCoverInfoPresetCriteriaMaker;
    private final ItemFileInfoPresetCriteriaMaker itemFileInfoPresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template,
            AssetCatalogPresetCriteriaMaker assetCatalogPresetCriteriaMaker,
            ItemPresetCriteriaMaker itemPresetCriteriaMaker,
            ItemLabelPresetCriteriaMaker itemLabelPresetCriteriaMaker,
            ItemPropertyPresetCriteriaMaker itemPropertyPresetCriteriaMaker,
            PoacPresetCriteriaMaker poacPresetCriteriaMaker,
            ItemCoverInfoPresetCriteriaMaker itemCoverInfoPresetCriteriaMaker,
            ItemFileInfoPresetCriteriaMaker itemFileInfoPresetCriteriaMaker
    ) {
        this.template = template;
        this.assetCatalogPresetCriteriaMaker = assetCatalogPresetCriteriaMaker;
        this.itemPresetCriteriaMaker = itemPresetCriteriaMaker;
        this.itemLabelPresetCriteriaMaker = itemLabelPresetCriteriaMaker;
        this.itemPropertyPresetCriteriaMaker = itemPropertyPresetCriteriaMaker;
        this.poacPresetCriteriaMaker = poacPresetCriteriaMaker;
        this.itemCoverInfoPresetCriteriaMaker = itemCoverInfoPresetCriteriaMaker;
        this.itemFileInfoPresetCriteriaMaker = itemFileInfoPresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AssetCatalog, HibernateAssetCatalog>
    assetCatalogHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(AssetCatalog.class, HibernateAssetCatalog.class, HibernateMapper.class),
                HibernateAssetCatalog.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AssetCatalog, HibernateAssetCatalog> assetCatalogHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(AssetCatalog.class, HibernateAssetCatalog.class, HibernateMapper.class),
                HibernateAssetCatalog.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AssetCatalog, HibernateAssetCatalog> assetCatalogHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(AssetCatalog.class, HibernateAssetCatalog.class, HibernateMapper.class),
                HibernateAssetCatalog.class,
                assetCatalogPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Item, HibernateItem> itemHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Item.class, HibernateItem.class, HibernateMapper.class),
                HibernateItem.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Item, HibernateItem> itemHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Item.class, HibernateItem.class, HibernateMapper.class),
                HibernateItem.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Item, HibernateItem> itemHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Item.class, HibernateItem.class, HibernateMapper.class),
                HibernateItem.class,
                itemPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchRelationDao<LongIdKey, Item, StringIdKey, ItemLabel, HibernateLongIdKey, HibernateItem,
            HibernateStringIdKey, HibernateItemLabel> itemBatchRelationDao() {
        return new HibernateBatchRelationDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Item.class, HibernateItem.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(ItemLabel.class, HibernateItemLabel.class, HibernateMapper.class),
                HibernateItem.class,
                HibernateItemLabel.class,
                "labels",
                "items",
                HibernateBatchRelationDao.JoinType.JOIN_BY_PARENT
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, ItemLabel, HibernateItemLabel>
    itemLabelHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(ItemLabel.class, HibernateItemLabel.class, HibernateMapper.class),
                HibernateItemLabel.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ItemLabel, HibernateItemLabel> itemLabelHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(ItemLabel.class, HibernateItemLabel.class, HibernateMapper.class),
                HibernateItemLabel.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ItemLabel, HibernateItemLabel> itemLabelHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(ItemLabel.class, HibernateItemLabel.class, HibernateMapper.class),
                HibernateItemLabel.class,
                itemLabelPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchRelationDao<StringIdKey, ItemLabel, LongIdKey, Item, HibernateStringIdKey,
            HibernateItemLabel, HibernateLongIdKey, HibernateItem> itemLabelBatchRelationDao() {
        return new HibernateBatchRelationDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(ItemLabel.class, HibernateItemLabel.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Item.class, HibernateItem.class, HibernateMapper.class),
                HibernateItemLabel.class,
                HibernateItem.class,
                "items",
                "labels",
                HibernateBatchRelationDao.JoinType.JOIN_BY_CHILD
        );
    }

    @Bean
    public HibernateBatchBaseDao<ItemPropertyKey, HibernateItemPropertyKey, ItemProperty, HibernateItemProperty>
    itemPropertyHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ItemPropertyKey.class, HibernateItemPropertyKey.class, HibernateMapper.class
                ),
                new MapStructBeanTransformer<>(ItemProperty.class, HibernateItemProperty.class, HibernateMapper.class),
                HibernateItemProperty.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ItemProperty, HibernateItemProperty> itemPropertyHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(ItemProperty.class, HibernateItemProperty.class, HibernateMapper.class),
                HibernateItemProperty.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ItemProperty, HibernateItemProperty> itemPropertyHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(ItemProperty.class, HibernateItemProperty.class, HibernateMapper.class),
                HibernateItemProperty.class,
                itemPropertyPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, ItemTypeIndicator,
            HibernateItemTypeIndicator> itemTypeIndicatorHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        ItemTypeIndicator.class, HibernateItemTypeIndicator.class, HibernateMapper.class
                ),
                HibernateItemTypeIndicator.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ItemTypeIndicator, HibernateItemTypeIndicator>
    itemTypeIndicatorHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ItemTypeIndicator.class, HibernateItemTypeIndicator.class, HibernateMapper.class
                ),
                HibernateItemTypeIndicator.class
        );
    }

    @Bean
    public HibernateBatchBaseDao<PoacKey, HibernatePoacKey, Poac, HibernatePoac> poacHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(PoacKey.class, HibernatePoacKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Poac.class, HibernatePoac.class, HibernateMapper.class),
                HibernatePoac.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Poac, HibernatePoac> poacHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Poac.class, HibernatePoac.class, HibernateMapper.class),
                HibernatePoac.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Poac, HibernatePoac> poacHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Poac.class, HibernatePoac.class, HibernateMapper.class),
                HibernatePoac.class,
                poacPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> userHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(User.class, HibernateUser.class, HibernateMapper.class),
                HibernateUser.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, ItemCoverInfo, HibernateItemCoverInfo>
    itemCoverInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        ItemCoverInfo.class, HibernateItemCoverInfo.class, HibernateMapper.class
                ),
                HibernateItemCoverInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ItemCoverInfo, HibernateItemCoverInfo> itemCoverInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ItemCoverInfo.class, HibernateItemCoverInfo.class, HibernateMapper.class
                ),
                HibernateItemCoverInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ItemCoverInfo, HibernateItemCoverInfo> itemCoverInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ItemCoverInfo.class, HibernateItemCoverInfo.class, HibernateMapper.class
                ),
                HibernateItemCoverInfo.class,
                itemCoverInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, ItemFileInfo, HibernateItemFileInfo>
    itemFileInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(ItemFileInfo.class, HibernateItemFileInfo.class, HibernateMapper.class),
                HibernateItemFileInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ItemFileInfo, HibernateItemFileInfo> itemFileInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(ItemFileInfo.class, HibernateItemFileInfo.class, HibernateMapper.class),
                HibernateItemFileInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ItemFileInfo, HibernateItemFileInfo> itemFileInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(ItemFileInfo.class, HibernateItemFileInfo.class, HibernateMapper.class),
                HibernateItemFileInfo.class,
                itemFileInfoPresetCriteriaMaker
        );
    }
}
