package com.dwarfeng.familyhelper.assets.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 项目文件更新信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemFileUpdateInfo implements Dto {

    private static final long serialVersionUID = 2882862997221755405L;

    private LongIdKey itemFileKey;
    private String originName;
    private byte[] content;

    public ItemFileUpdateInfo() {
    }

    public ItemFileUpdateInfo(LongIdKey itemFileKey, String originName, byte[] content) {
        this.itemFileKey = itemFileKey;
        this.originName = originName;
        this.content = content;
    }

    public LongIdKey getItemFileKey() {
        return itemFileKey;
    }

    public void setItemFileKey(LongIdKey itemFileKey) {
        this.itemFileKey = itemFileKey;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ItemFileUpdateInfo{" +
                "itemFileKey=" + itemFileKey +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
