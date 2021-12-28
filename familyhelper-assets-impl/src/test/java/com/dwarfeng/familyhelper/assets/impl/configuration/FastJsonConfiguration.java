package com.dwarfeng.familyhelper.assets.impl.configuration;

import com.alibaba.fastjson.parser.ParserConfig;
import com.dwarfeng.familyhelper.assets.sdk.bean.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FastJsonConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonConfiguration.class);

    public FastJsonConfiguration() {
        LOGGER.info("正在配置 FastJson autotype 白名单");
        //实体对象。
        ParserConfig.getGlobalInstance().addAccept(FastJsonAssetCatalog.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonItem.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonItemLabel.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonItemProperty.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonItemTypeIndicator.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonPoac.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonUser.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonItemCoverInfo.class.getCanonicalName());
        ParserConfig.getGlobalInstance().addAccept(FastJsonItemFileInfo.class.getCanonicalName());
        LOGGER.debug("FastJson autotype 白名单配置完毕");
    }
}
