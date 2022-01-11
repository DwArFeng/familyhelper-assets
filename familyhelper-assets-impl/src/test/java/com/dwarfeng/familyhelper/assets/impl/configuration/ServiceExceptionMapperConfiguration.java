package com.dwarfeng.familyhelper.assets.impl.configuration;

import com.dwarfeng.familyhelper.assets.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.familyhelper.assets.stack.exception.*;
import com.dwarfeng.subgrade.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServiceExceptionMapperConfiguration {

    @Bean
    public MapServiceExceptionMapper mapServiceExceptionMapper() {
        Map<Class<? extends Exception>, ServiceException.Code> destination = ServiceExceptionHelper.putDefaultDestination(null);
        destination.put(AssetCatalogNotExistsException.class, ServiceExceptionCodes.ASSET_CATALOG_NOT_EXISTS);
        destination.put(UserNotExistsException.class, ServiceExceptionCodes.USER_NOT_EXISTS);
        destination.put(UserNotPermittedException.class, ServiceExceptionCodes.USER_NOT_PERMITTED);
        destination.put(InvalidPermissionLevelException.class, ServiceExceptionCodes.INVALID_PERMISSION_LEVEL);
        destination.put(ItemNotExistsException.class, ServiceExceptionCodes.ITEM_NOT_EXISTS);
        destination.put(IllegalItemStateException.class, ServiceExceptionCodes.ILLEGAL_ITEM_STATE);
        destination.put(ItemLabelNotExistsException.class, ServiceExceptionCodes.ITEM_LABEL_NOT_EXISTS);
        destination.put(ItemCoverNotExistsException.class, ServiceExceptionCodes.ITEM_COVER_NOT_EXISTS);
        destination.put(ItemCoverTransportException.class, ServiceExceptionCodes.ITEM_COVER_TRANSPORT_FAILED);
        destination.put(IllegalItemCoverStateException.class, ServiceExceptionCodes.ILLEGAL_ITEM_COVER_STATE);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINE);
    }
}
