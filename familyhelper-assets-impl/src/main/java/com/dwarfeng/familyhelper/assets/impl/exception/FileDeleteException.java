package com.dwarfeng.familyhelper.assets.impl.exception;

/**
 * 文件删除异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FileDeleteException extends FileException {

    private static final long serialVersionUID = -1005379453087970157L;

    public FileDeleteException(String filePath) {
        super(filePath);
    }

    public FileDeleteException(Throwable cause, String filePath) {
        super(cause, filePath);
    }

    @Override
    public String getMessage() {
        return "File delete failed: " + filePath;
    }
}
