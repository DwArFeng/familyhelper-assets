package com.dwarfeng.familyhelper.assets.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 项目文件上传信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class ItemFileUploadInfo implements Dto {

    private static final long serialVersionUID = 7216235298707666791L;

    private LongIdKey itemKey;
    private String originName;
    private byte[] content;

    public ItemFileUploadInfo() {
    }

    public ItemFileUploadInfo(LongIdKey itemKey, String originName, byte[] content) {
        this.itemKey = itemKey;
        this.originName = originName;
        this.content = content;
    }

    public LongIdKey getItemKey() {
        return itemKey;
    }

    public void setItemKey(LongIdKey itemKey) {
        this.itemKey = itemKey;
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
        return "ItemFileUploadInfo{" +
                "itemKey=" + itemKey +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
