package com.dwarfeng.familyhelper.assets.impl.exception;

/**
 * 文件存储异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FileStoreException extends FileException {

    private static final long serialVersionUID = -6505312800654847949L;

    public FileStoreException(String filePath) {
        super(filePath);
    }

    public FileStoreException(Throwable cause, String filePath) {
        super(cause, filePath);
    }

    @Override
    public String getMessage() {
        return "File store failed: " + filePath;
    }
}
