package com.dwarfeng.familyhelper.assets.impl.configuration;

import com.dwarfeng.familyhelper.assets.sdk.bean.FastJsonMapper;
import com.dwarfeng.familyhelper.assets.sdk.bean.entity.*;
import com.dwarfeng.familyhelper.assets.sdk.bean.key.formatter.ItemPropertyStringKeyFormatter;
import com.dwarfeng.familyhelper.assets.sdk.bean.key.formatter.PoacStringKeyFormatter;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.*;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;

    @Value("${cache.prefix.entity.asset_catalog}")
    private String assetCatalogPrefix;
    @Value("${cache.prefix.entity.item}")
    private String itemPrefix;
    @Value("${cache.prefix.entity.item_label}")
    private String itemLabelPrefix;
    @Value("${cache.prefix.entity.item_property}")
    private String itemPropertyPrefix;
    @Value("${cache.prefix.entity.item_type_indicator}")
    private String itemTypeIndicatorPrefix;
    @Value("${cache.prefix.entity.poac}")
    private String poacPrefix;
    @Value("${cache.prefix.entity.user}")
    private String userPrefix;
    @Value("${cache.prefix.entity.item_cover_info}")
    private String itemCoverInfoPrefix;
    @Value("${cache.prefix.entity.item_file_info}")
    private String itemFileInfoPrefix;

    public CacheConfiguration(RedisTemplate<String, ?> template) {
        this.template = template;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AssetCatalog, FastJsonAssetCatalog> assetCatalogRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAssetCatalog>) template,
                new LongIdStringKeyFormatter(assetCatalogPrefix),
                new MapStructBeanTransformer<>(AssetCatalog.class, FastJsonAssetCatalog.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Item, FastJsonItem> itemRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonItem>) template,
                new LongIdStringKeyFormatter(itemPrefix),
                new MapStructBeanTransformer<>(Item.class, FastJsonItem.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, ItemLabel, FastJsonItemLabel> itemLabelRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonItemLabel>) template,
                new StringIdStringKeyFormatter(itemLabelPrefix),
                new MapStructBeanTransformer<>(ItemLabel.class, FastJsonItemLabel.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<ItemPropertyKey, ItemProperty, FastJsonItemProperty> itemPropertyRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonItemProperty>) template,
                new ItemPropertyStringKeyFormatter(itemPropertyPrefix),
                new MapStructBeanTransformer<>(ItemProperty.class, FastJsonItemProperty.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, ItemTypeIndicator, FastJsonItemTypeIndicator>
    itemTypeIndicatorRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonItemTypeIndicator>) template,
                new StringIdStringKeyFormatter(itemTypeIndicatorPrefix),
                new MapStructBeanTransformer<>(
                        ItemTypeIndicator.class, FastJsonItemTypeIndicator.class, FastJsonMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<PoacKey, Poac, FastJsonPoac> poacRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonPoac>) template,
                new PoacStringKeyFormatter(poacPrefix),
                new MapStructBeanTransformer<>(Poac.class, FastJsonPoac.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, User, FastJsonUser> userRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonUser>) template,
                new StringIdStringKeyFormatter(userPrefix),
                new MapStructBeanTransformer<>(User.class, FastJsonUser.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, ItemCoverInfo, FastJsonItemCoverInfo> itemCoverInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonItemCoverInfo>) template,
                new LongIdStringKeyFormatter(itemCoverInfoPrefix),
                new MapStructBeanTransformer<>(ItemCoverInfo.class, FastJsonItemCoverInfo.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, ItemFileInfo, FastJsonItemFileInfo> itemFileInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonItemFileInfo>) template,
                new LongIdStringKeyFormatter(itemFileInfoPrefix),
                new MapStructBeanTransformer<>(ItemFileInfo.class, FastJsonItemFileInfo.class, FastJsonMapper.class)
        );
    }
}
