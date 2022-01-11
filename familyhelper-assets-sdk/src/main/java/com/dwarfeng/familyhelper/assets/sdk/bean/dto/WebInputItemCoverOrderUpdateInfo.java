package com.dwarfeng.familyhelper.assets.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.familyhelper.assets.stack.bean.dto.ItemCoverOrderUpdateInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * WebInput 项目封面顺序更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputItemCoverOrderUpdateInfo implements Dto {

    private static final long serialVersionUID = 4650435209515904907L;

    public static ItemCoverOrderUpdateInfo toStackBean(
            WebInputItemCoverOrderUpdateInfo webInputItemCoverOrderUpdateInfo
    ) {
        if (Objects.isNull(webInputItemCoverOrderUpdateInfo)) {
            return null;
        } else {
            return new ItemCoverOrderUpdateInfo(
                    webInputItemCoverOrderUpdateInfo.getItemCoverKeys().stream().map(WebInputLongIdKey::toStackBean)
                            .collect(Collectors.toList())
            );
        }
    }

    @JSONField(name = "item_cover_keys")
    @Valid
    @NotEmpty
    private List<WebInputLongIdKey> itemCoverKeys;

    public WebInputItemCoverOrderUpdateInfo() {
    }

    public List<WebInputLongIdKey> getItemCoverKeys() {
        return itemCoverKeys;
    }

    public void setItemCoverKeys(List<WebInputLongIdKey> itemCoverKeys) {
        this.itemCoverKeys = itemCoverKeys;
    }

    @Override
    public String toString() {
        return "WebInputItemCoverOrderUpdateInfo{" +
                "itemCoverKeys=" + itemCoverKeys +
                '}';
    }
}
