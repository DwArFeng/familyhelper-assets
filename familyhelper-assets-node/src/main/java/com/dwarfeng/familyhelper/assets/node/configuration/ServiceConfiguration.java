package com.dwarfeng.familyhelper.assets.node.configuration;

import com.dwarfeng.familyhelper.assets.impl.service.operation.AssetCatalogCrudOperation;
import com.dwarfeng.familyhelper.assets.impl.service.operation.ItemCrudOperation;
import com.dwarfeng.familyhelper.assets.impl.service.operation.ItemLabelCrudOperation;
import com.dwarfeng.familyhelper.assets.impl.service.operation.UserCrudOperation;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.*;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.familyhelper.assets.stack.cache.*;
import com.dwarfeng.familyhelper.assets.stack.dao.*;
import com.dwarfeng.sfds.api.integration.subgrade.SnowFlakeLongIdKeyFetcher;
import com.dwarfeng.subgrade.impl.bean.key.ExceptionKeyFetcher;
import com.dwarfeng.subgrade.impl.service.*;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;
    private final DaoConfiguration daoConfiguration;

    private final AssetCatalogCrudOperation assetCatalogCrudOperation;
    private final AssetCatalogDao assetCatalogDao;
    private final ItemCrudOperation itemCrudOperation;
    private final ItemDao itemDao;
    private final ItemLabelCrudOperation itemLabelCrudOperation;
    private final ItemLabelDao itemLabelDao;
    private final ItemPropertyDao itemPropertyDao;
    private final ItemPropertyCache itemPropertyCache;
    private final ItemTypeIndicatorDao itemTypeIndicatorDao;
    private final ItemTypeIndicatorCache itemTypeIndicatorCache;
    private final PoacDao poacDao;
    private final PoacCache poacCache;
    private final UserCrudOperation userCrudOperation;
    private final ItemCoverInfoDao itemCoverInfoDao;
    private final ItemCoverInfoCache itemCoverInfoCache;
    private final ItemFileInfoDao itemFileInfoDao;
    private final ItemFileInfoCache itemFileInfoCache;

    @Value("${cache.timeout.entity.item_type_indicator}")
    private long itemTypeIndicatorTimeout;
    @Value("${cache.timeout.entity.item_property}")
    private long itemPropertyTimeout;
    @Value("${cache.timeout.entity.poac}")
    private long poacTimeout;
    @Value("${cache.timeout.entity.item_cover_info}")
    private long itemCoverInfoTimeout;
    @Value("${cache.timeout.entity.item_file_info}")
    private long itemFileInfoTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration, DaoConfiguration daoConfiguration,
            AssetCatalogCrudOperation assetCatalogCrudOperation, AssetCatalogDao assetCatalogDao,
            ItemCrudOperation itemCrudOperation, ItemDao itemDao,
            ItemLabelCrudOperation itemLabelCrudOperation, ItemLabelDao itemLabelDao,
            ItemPropertyDao itemPropertyDao, ItemPropertyCache itemPropertyCache,
            ItemTypeIndicatorDao itemTypeIndicatorDao, ItemTypeIndicatorCache itemTypeIndicatorCache,
            PoacDao poacDao, PoacCache poacCache,
            UserCrudOperation userCrudOperation,
            ItemCoverInfoDao itemCoverInfoDao, ItemCoverInfoCache itemCoverInfoCache,
            ItemFileInfoDao itemFileInfoDao, ItemFileInfoCache itemFileInfoCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.daoConfiguration = daoConfiguration;
        this.assetCatalogCrudOperation = assetCatalogCrudOperation;
        this.assetCatalogDao = assetCatalogDao;
        this.itemCrudOperation = itemCrudOperation;
        this.itemDao = itemDao;
        this.itemLabelCrudOperation = itemLabelCrudOperation;
        this.itemLabelDao = itemLabelDao;
        this.itemPropertyDao = itemPropertyDao;
        this.itemPropertyCache = itemPropertyCache;
        this.itemTypeIndicatorDao = itemTypeIndicatorDao;
        this.itemTypeIndicatorCache = itemTypeIndicatorCache;
        this.poacDao = poacDao;
        this.poacCache = poacCache;
        this.userCrudOperation = userCrudOperation;
        this.itemCoverInfoDao = itemCoverInfoDao;
        this.itemCoverInfoCache = itemCoverInfoCache;
        this.itemFileInfoDao = itemFileInfoDao;
        this.itemFileInfoCache = itemFileInfoCache;
    }

    @Bean
    public KeyFetcher<LongIdKey> longIdKeyKeyFetcher() {
        return new SnowFlakeLongIdKeyFetcher();
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, AssetCatalog> assetCatalogBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                assetCatalogCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AssetCatalog> assetCatalogDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                assetCatalogDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AssetCatalog> assetCatalogDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                assetCatalogDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Item> itemCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                itemCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Item> itemDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                itemDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Item> itemDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                itemDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyBatchRelationService<LongIdKey, StringIdKey> itemDaoOnlyBatchRelationService() {
        return new DaoOnlyBatchRelationService<>(
                daoConfiguration.itemBatchRelationDao(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, ItemLabel> itemLabelCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                itemLabelCrudOperation,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ItemLabel> itemLabelDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                itemLabelDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ItemLabel> itemLabelDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                itemLabelDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyBatchRelationService<StringIdKey, LongIdKey> itemLabelDaoOnlyBatchRelationService() {
        return new DaoOnlyBatchRelationService<>(
                daoConfiguration.itemLabelBatchRelationDao(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<ItemPropertyKey, ItemProperty> itemPropertyGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                itemPropertyDao,
                itemPropertyCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                itemPropertyTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ItemProperty> itemPropertyDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                itemPropertyDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ItemProperty> itemPropertyDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                itemPropertyDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, ItemTypeIndicator>
    itemTypeIndicatorGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                itemTypeIndicatorDao,
                itemTypeIndicatorCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                itemTypeIndicatorTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ItemTypeIndicator> itemTypeIndicatorDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                itemTypeIndicatorDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<PoacKey, Poac> poacGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                poacDao,
                poacCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                poacTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Poac> poacDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                poacDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Poac> poacDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                poacDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, User> userBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                userCrudOperation,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, ItemCoverInfo> itemCoverInfoGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                itemCoverInfoDao,
                itemCoverInfoCache,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                itemCoverInfoTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ItemCoverInfo> itemCoverInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                itemCoverInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ItemCoverInfo> itemCoverInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                itemCoverInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, ItemFileInfo> itemFileInfoGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                itemFileInfoDao,
                itemFileInfoCache,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                itemFileInfoTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ItemFileInfo> itemFileInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                itemFileInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ItemFileInfo> itemFileInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                itemFileInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}
