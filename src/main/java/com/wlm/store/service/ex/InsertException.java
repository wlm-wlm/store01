package com.wlm.store.service.ex;

/**
 * 类名：InsertException
 * 描述：插入异常
 * 作者：汪灵敏
 * 日期：2021/8/26 20:10
 * 版本：V1.0
 */

public class InsertException extends ServiceException{
    public InsertException() {
        super();
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    protected InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
