package com.wlm.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类名：LoginInterceptor
 * 描述：
 * 作者：汪灵敏
 * 日期：2021/8/29 9:32
 * 版本：V1.0
 */
/*拦截器*/

public class LoginInterceptor implements HandlerInterceptor {
    //调用所有方法之前执行的方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object uid = request.getSession().getAttribute("uid");
        if (uid==null){
            //用户没有登录,进行重定向
            response.sendRedirect("/web/login.html");
            return false;
        }
        //放行
        return true;

    }
/*//在ModelAndView对象返回之后被调用的方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
//在所有请求关联的资源执行完毕后最后执行的方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }*/
}
