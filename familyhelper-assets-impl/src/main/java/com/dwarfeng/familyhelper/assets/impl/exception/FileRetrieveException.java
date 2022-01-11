package com.dwarfeng.familyhelper.assets.impl.exception;

/**
 * 文件取回异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FileRetrieveException extends FileException {

    private static final long serialVersionUID = -7843109740357668148L;

    public FileRetrieveException(String filePath) {
        super(filePath);
    }

    public FileRetrieveException(Throwable cause, String filePath) {
        super(cause, filePath);
    }

    @Override
    public String getMessage() {
        return "File retrieve failed: " + filePath;
    }
}
