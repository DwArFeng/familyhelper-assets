package com.dwarfeng.familyhelper.assets.sdk.bean;

import com.dwarfeng.familyhelper.assets.sdk.bean.entity.*;
import com.dwarfeng.familyhelper.assets.sdk.bean.key.FastJsonItemPropertyKey;
import com.dwarfeng.familyhelper.assets.sdk.bean.key.FastJsonPoacKey;
import com.dwarfeng.familyhelper.assets.stack.bean.entity.*;
import com.dwarfeng.familyhelper.assets.stack.bean.key.ItemPropertyKey;
import com.dwarfeng.familyhelper.assets.stack.bean.key.PoacKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * FastJson Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.0.7
 */
@Mapper
public interface FastJsonMapper {

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    FastJsonItemPropertyKey itemPropertyKeyToFastJson(ItemPropertyKey itemPropertyKey);

    @InheritInverseConfiguration
    ItemPropertyKey itemPropertyKeyFromFastJson(FastJsonItemPropertyKey fastJsonItemPropertyKey);

    FastJsonPoacKey poacKeyToFastJson(PoacKey poacKey);

    @InheritInverseConfiguration
    PoacKey poacKeyFromFastJson(FastJsonPoacKey fastJsonPoacKey);

    FastJsonAssetCatalog assetCatalogToFastJson(AssetCatalog assetCatalog);

    @InheritInverseConfiguration
    AssetCatalog assetCatalogFromFastJson(FastJsonAssetCatalog fastJsonAssetCatalog);

    FastJsonItem itemToFastJson(Item item);

    @InheritInverseConfiguration
    Item itemFromFastJson(FastJsonItem fastJsonItem);

    FastJsonItemCoverInfo itemCoverInfoToFastJson(ItemCoverInfo itemCoverInfo);

    @InheritInverseConfiguration
    ItemCoverInfo itemCoverInfoFromFastJson(FastJsonItemCoverInfo fastJsonItemCoverInfo);

    FastJsonItemFileInfo itemFileInfoToFastJson(ItemFileInfo itemFileInfo);

    @InheritInverseConfiguration
    ItemFileInfo itemFileInfoFromFastJson(FastJsonItemFileInfo fastJsonItemFileInfo);

    FastJsonItemLabel itemLabelToFastJson(ItemLabel itemLabel);

    @InheritInverseConfiguration
    ItemLabel itemLabelFromFastJson(FastJsonItemLabel fastJsonItemLabel);

    FastJsonItemProperty itemPropertyToFastJson(ItemProperty itemProperty);

    @InheritInverseConfiguration
    ItemProperty itemPropertyFromFastJson(FastJsonItemProperty fastJsonItemProperty);

    FastJsonItemTypeIndicator itemTypeIndicatorToFastJson(ItemTypeIndicator itemTypeIndicator);

    @InheritInverseConfiguration
    ItemTypeIndicator itemTypeIndicatorFromFastJson(FastJsonItemTypeIndicator fastJsonItemTypeIndicator);

    FastJsonPoac poacToFastJson(Poac poac);

    @InheritInverseConfiguration
    Poac poacFromFastJson(FastJsonPoac fastJsonPoac);

    FastJsonUser userToFastJson(User user);

    @InheritInverseConfiguration
    User userFromFastJson(FastJsonUser fastJsonUser);
}
