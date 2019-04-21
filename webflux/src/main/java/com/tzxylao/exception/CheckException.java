package com.tzxylao.exception;

/**
 * @author laoliangliang
 * @since 19/4/20 下午12:15
 */
public class CheckException extends RuntimeException{

    private static final long serialVersionUID = 6348131320619186341L;

    /**
     * 出错字段的名字
     */
    private String filedName;

    /**
     * 出错字段的值
     */
    private String fieldValue;

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public CheckException(String filedName, String fieldValue) {
        this.fieldValue = fieldValue;
        this.filedName = filedName;
    }

    public CheckException() {
        super();
    }

    public CheckException(String message) {
        super(message);
    }


    public CheckException(Throwable cause) {
        super(cause);
    }

    protected CheckException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
