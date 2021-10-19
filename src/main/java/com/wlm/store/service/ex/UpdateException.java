package com.wlm.store.service.ex;

/**
 * 类名：UpdateException
 * 描述：
 * 作者：汪灵敏
 * 日期：2021/8/29 16:35
 * 版本：V1.0
 */

public class UpdateException extends ServiceException {
    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
