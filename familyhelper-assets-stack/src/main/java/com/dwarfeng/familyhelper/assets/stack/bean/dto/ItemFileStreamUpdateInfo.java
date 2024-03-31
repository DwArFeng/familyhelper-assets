package com.dwarfeng.familyhelper.assets.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.io.InputStream;

/**
 * 更新项目文件流信息。
 *
 * @author DwArFeng
 * @since 1.1.8
 */
public class ItemFileStreamUpdateInfo implements Dto {

    private static final long serialVersionUID = -2793710611772117606L;

    private LongIdKey itemFileKey;
    private String originName;
    private long length;
    private InputStream content;

    public ItemFileStreamUpdateInfo() {
    }

    public ItemFileStreamUpdateInfo(LongIdKey itemFileKey, String originName, long length, InputStream content) {
        this.itemFileKey = itemFileKey;
        this.originName = originName;
        this.length = length;
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

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ItemFileStreamUpdateInfo{" +
                "itemFileKey=" + itemFileKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
