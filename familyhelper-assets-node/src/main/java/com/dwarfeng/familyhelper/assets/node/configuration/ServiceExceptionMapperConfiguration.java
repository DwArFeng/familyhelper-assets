package com.dwarfeng.familyhelper.assets.node.configuration;

import com.dwarfeng.familyhelper.assets.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.familyhelper.assets.stack.exception.AssetCatalogNotExistsException;
import com.dwarfeng.familyhelper.assets.stack.exception.InvalidPermissionLevelException;
import com.dwarfeng.familyhelper.assets.stack.exception.UserNotExistsException;
import com.dwarfeng.familyhelper.assets.stack.exception.UserNotPermittedException;
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
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINE);
    }
}
