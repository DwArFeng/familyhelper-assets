package com.dwarfeng.familyhelper.assets.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.io.InputStream;

/**
 * 项目文件流。
 *
 * @author DwArFeng
 * @since 1.1.8
 */
public class ItemFileStream implements Dto {

    private static final long serialVersionUID = -3807834779258338491L;

    private String originName;
    private long length;
    private InputStream content;

    public ItemFileStream() {
    }

    public ItemFileStream(String originName, long length, InputStream content) {
        this.originName = originName;
        this.length = length;
        this.content = content;
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
        return "ItemFileStream{" +
                "originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
