package com.dwarfeng.familyhelper.assets.impl.bean;

import com.dwarfeng.familyhelper.assets.impl.bean.entity.*;
import com.dwarfeng.familyhelper.assets.impl.bean.key.HibernateItemPropertyKey;
import com.dwarfeng.familyhelper.assets.impl.bean.key.HibernatePoacKey;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.*;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Hibernate Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.0.7
 */
@Mapper
public interface HibernateMapper {

    HibernateLongIdKey longIdKeyToHibernate(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromHibernate(HibernateLongIdKey hibernateLongIdKey);

    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    HibernateItemPropertyKey itemPropertyKeyToHibernate(ItemPropertyKey itemPropertyKey);

    @InheritInverseConfiguration
    ItemPropertyKey itemPropertyKeyFromHibernate(HibernateItemPropertyKey hibernateItemPropertyKey);

    HibernatePoacKey poacKeyToHibernate(PoacKey poacKey);

    @InheritInverseConfiguration
    PoacKey poacKeyFromHibernate(HibernatePoacKey hibernatePoacKey);

    @Mapping(target = "poacs", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "items", ignore = true)
    HibernateAssetCatalog assetCatalogToHibernate(AssetCatalog assetCatalog);

    @InheritInverseConfiguration
    AssetCatalog assetCatalogFromHibernate(HibernateAssetCatalog hibernateAssetCatalog);

    @Mapping(target = "parentLongId", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "labels", ignore = true)
    @Mapping(target = "itemFileInfos", ignore = true)
    @Mapping(target = "itemCoverInfos", ignore = true)
    @Mapping(target = "assetCatalogLongId", ignore = true)
    @Mapping(target = "assetCatalog", ignore = true)
    HibernateItem itemToHibernate(Item item);

    @InheritInverseConfiguration
    Item itemFromHibernate(HibernateItem hibernateItem);

    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "itemLongId", ignore = true)
    @Mapping(target = "item", ignore = true)
    HibernateItemCoverInfo itemCoverInfoToHibernate(ItemCoverInfo itemCoverInfo);

    @InheritInverseConfiguration
    ItemCoverInfo itemCoverInfoFromHibernate(HibernateItemCoverInfo hibernateItemCoverInfo);

    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "itemLongId", ignore = true)
    @Mapping(target = "item", ignore = true)
    HibernateItemFileInfo itemFileInfoToHibernate(ItemFileInfo itemFileInfo);

    @InheritInverseConfiguration
    ItemFileInfo itemFileInfoFromHibernate(HibernateItemFileInfo hibernateItemFileInfo);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "items", ignore = true)
    HibernateItemLabel itemLabelToHibernate(ItemLabel itemLabel);

    @InheritInverseConfiguration
    ItemLabel itemLabelFromHibernate(HibernateItemLabel hibernateItemLabel);

    @Mapping(target = "propertyId", ignore = true)
    @Mapping(target = "itemId", ignore = true)
    HibernateItemProperty itemPropertyToHibernate(ItemProperty itemProperty);

    @InheritInverseConfiguration
    ItemProperty itemPropertyFromHibernate(HibernateItemProperty hibernateItemProperty);

    @Mapping(target = "stringId", ignore = true)
    HibernateItemTypeIndicator itemTypeIndicatorToHibernate(ItemTypeIndicator itemTypeIndicator);

    @InheritInverseConfiguration
    ItemTypeIndicator itemTypeIndicatorFromHibernate(HibernateItemTypeIndicator hibernateItemTypeIndicator);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "assetCatalog", ignore = true)
    HibernatePoac poacToHibernate(Poac poac);

    @InheritInverseConfiguration
    Poac poacFromHibernate(HibernatePoac hibernatePoac);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "poacs", ignore = true)
    HibernateUser userToHibernate(User user);

    @InheritInverseConfiguration
    User userFromHibernate(HibernateUser hibernateUser);
}
