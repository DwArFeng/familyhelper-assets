package com.dwarfeng.familyhelper.assets.node.configuration;

import com.dwarfeng.familyhelper.assets.impl.bean.entity.*;
import com.dwarfeng.familyhelper.assets.impl.bean.key.HibernateItemPropertyKey;
import com.dwarfeng.familyhelper.assets.impl.bean.key.HibernatePoacKey;
import com.dwarfeng.familyhelper.assets.impl.dao.preset.*;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.*;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchRelationDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate template;
    private final Mapper mapper;

    private final AssetCatalogPresetCriteriaMaker assetCatalogPresetCriteriaMaker;
    private final ItemPresetCriteriaMaker itemPresetCriteriaMaker;
    private final ItemLabelPresetCriteriaMaker itemLabelPresetCriteriaMaker;
    private final ItemPropertyPresetCriteriaMaker itemPropertyPresetCriteriaMaker;
    private final PoacPresetCriteriaMaker poacPresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template, Mapper mapper,
            AssetCatalogPresetCriteriaMaker assetCatalogPresetCriteriaMaker,
            ItemPresetCriteriaMaker itemPresetCriteriaMaker,
            ItemLabelPresetCriteriaMaker itemLabelPresetCriteriaMaker,
            ItemPropertyPresetCriteriaMaker itemPropertyPresetCriteriaMaker,
            PoacPresetCriteriaMaker poacPresetCriteriaMaker
    ) {
        this.template = template;
        this.mapper = mapper;
        this.assetCatalogPresetCriteriaMaker = assetCatalogPresetCriteriaMaker;
        this.itemPresetCriteriaMaker = itemPresetCriteriaMaker;
        this.itemLabelPresetCriteriaMaker = itemLabelPresetCriteriaMaker;
        this.itemPropertyPresetCriteriaMaker = itemPropertyPresetCriteriaMaker;
        this.poacPresetCriteriaMaker = poacPresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AssetCatalog, HibernateAssetCatalog>
    assetCatalogHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(AssetCatalog.class, HibernateAssetCatalog.class, mapper),
                HibernateAssetCatalog.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AssetCatalog, HibernateAssetCatalog> assetCatalogHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(AssetCatalog.class, HibernateAssetCatalog.class, mapper),
                HibernateAssetCatalog.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AssetCatalog, HibernateAssetCatalog> assetCatalogHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(AssetCatalog.class, HibernateAssetCatalog.class, mapper),
                HibernateAssetCatalog.class,
                assetCatalogPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Item, HibernateItem> itemHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(Item.class, HibernateItem.class, mapper),
                HibernateItem.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Item, HibernateItem> itemHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(Item.class, HibernateItem.class, mapper),
                HibernateItem.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Item, HibernateItem> itemHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(Item.class, HibernateItem.class, mapper),
                HibernateItem.class,
                itemPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchRelationDao<LongIdKey, Item, StringIdKey, ItemLabel, HibernateLongIdKey, HibernateItem,
            HibernateStringIdKey, HibernateItemLabel> itemBatchRelationDao() {
        return new HibernateBatchRelationDao<>(
                template,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(Item.class, HibernateItem.class, mapper),
                new DozerBeanTransformer<>(ItemLabel.class, HibernateItemLabel.class, mapper),
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
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(ItemLabel.class, HibernateItemLabel.class, mapper),
                HibernateItemLabel.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ItemLabel, HibernateItemLabel> itemLabelHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(ItemLabel.class, HibernateItemLabel.class, mapper),
                HibernateItemLabel.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ItemLabel, HibernateItemLabel> itemLabelHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(ItemLabel.class, HibernateItemLabel.class, mapper),
                HibernateItemLabel.class,
                itemLabelPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchRelationDao<StringIdKey, ItemLabel, LongIdKey, Item, HibernateStringIdKey,
            HibernateItemLabel, HibernateLongIdKey, HibernateItem> itemLabelBatchRelationDao() {
        return new HibernateBatchRelationDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(ItemLabel.class, HibernateItemLabel.class, mapper),
                new DozerBeanTransformer<>(Item.class, HibernateItem.class, mapper),
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
                new DozerBeanTransformer<>(ItemPropertyKey.class, HibernateItemPropertyKey.class, mapper),
                new DozerBeanTransformer<>(ItemProperty.class, HibernateItemProperty.class, mapper),
                HibernateItemProperty.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ItemProperty, HibernateItemProperty> itemPropertyHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(ItemProperty.class, HibernateItemProperty.class, mapper),
                HibernateItemProperty.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ItemProperty, HibernateItemProperty> itemPropertyHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(ItemProperty.class, HibernateItemProperty.class, mapper),
                HibernateItemProperty.class,
                itemPropertyPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, ItemTypeIndicator,
            HibernateItemTypeIndicator> itemTypeIndicatorHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(ItemTypeIndicator.class, HibernateItemTypeIndicator.class, mapper),
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
                new DozerBeanTransformer<>(ItemTypeIndicator.class, HibernateItemTypeIndicator.class, mapper),
                HibernateItemTypeIndicator.class
        );
    }

    @Bean
    public HibernateBatchBaseDao<PoacKey, HibernatePoacKey, Poac, HibernatePoac> poacHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(PoacKey.class, HibernatePoacKey.class, mapper),
                new DozerBeanTransformer<>(Poac.class, HibernatePoac.class, mapper),
                HibernatePoac.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Poac, HibernatePoac> poacHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new DozerBeanTransformer<>(Poac.class, HibernatePoac.class, mapper),
                HibernatePoac.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Poac, HibernatePoac> poacHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new DozerBeanTransformer<>(Poac.class, HibernatePoac.class, mapper),
                HibernatePoac.class,
                poacPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> userHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(User.class, HibernateUser.class, mapper),
                HibernateUser.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }
}
