package com.wlm.store.service.ex;

/**
 * 类名：UsernameDuplicatedException
 * 描述：用户名被占用异常
 * 作者：汪灵敏
 * 日期：2021/8/26 20:07
 * 版本：V1.0
 */

public class UsernameDuplicatedException extends ServiceException{
    public UsernameDuplicatedException() {
        super();
    }

    public UsernameDuplicatedException(String message) {
        super(message);
    }

    public UsernameDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDuplicatedException(Throwable cause) {
        super(cause);
    }

    protected UsernameDuplicatedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
