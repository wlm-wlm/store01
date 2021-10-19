package com.wlm.store.service.ex;

/**
 * 类名：UserNotFindException
 * 描述：
 * 作者：汪灵敏
 * 日期：2021/8/28 13:27
 * 版本：V1.0
 */

public class UserNotFindException extends ServiceException{
    public UserNotFindException() {
        super();
    }

    public UserNotFindException(String message) {
        super(message);
    }

    public UserNotFindException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFindException(Throwable cause) {
        super(cause);
    }

    protected UserNotFindException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
