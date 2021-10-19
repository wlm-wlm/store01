package com.wlm.store.test;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * 类名：WlmAspect
 * 描述：
 * 作者：汪灵敏
 * 日期：2021/9/20 11:35
 * 版本：V1.0
 */
@Component
@Aspect
public class WlmAspect {
    @Pointcut
    public void  pointcut(){

    }

}
