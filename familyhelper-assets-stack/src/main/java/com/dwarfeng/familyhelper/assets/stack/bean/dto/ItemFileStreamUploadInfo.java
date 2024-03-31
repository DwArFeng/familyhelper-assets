package com.dwarfeng.familyhelper.assets.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.io.InputStream;

/**
 * 项目文件流上传信息。
 *
 * @author DwArFeng
 * @since 1.1.8
 */
public class ItemFileStreamUploadInfo implements Dto {

    private static final long serialVersionUID = 6064625152368749820L;

    private LongIdKey itemKey;
    private String originName;
    private long length;
    private InputStream content;

    public ItemFileStreamUploadInfo() {
    }

    public ItemFileStreamUploadInfo(LongIdKey itemKey, String originName, long length, InputStream content) {
        this.itemKey = itemKey;
        this.originName = originName;
        this.length = length;
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
        return "ItemFileStreamUploadInfo{" +
                "itemKey=" + itemKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
