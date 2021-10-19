package com.wlm.store.controller;

import com.wlm.store.service.ex.*;
import com.wlm.store.until.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 类名：BaseController
 * 描述：
 * 作者：汪灵敏
 * 日期：2021/8/27 0:53
 * 版本：V1.0
 */
/*控制层基类*/
public class BaseController {
    /*操作成功状态码*/
    public static final int ok = 200;
    //请求处理方法
    //自动将异常对象传递给此方法的参数列表上
    @ExceptionHandler(ServiceException.class)//用于统一处理抛出异常
    public JsonResult<Void> handException(Throwable e){
            JsonResult<Void> result = new JsonResult<>(e);
            if(e instanceof UsernameDuplicatedException){
                result.setState(4000);
                result.setMessage("用户名被占用");
            }else if (e instanceof UserNotFindException){
                result.setState(5001);
                result.setMessage("用户数据不存在");
            }else if (e instanceof PasswordNotMatchException){
                result.setState(5002);
                result.setMessage("用户名密码错误");
            } else if (e instanceof InsertException){
                result.setState(5000);
                result.setMessage("注册时产生未知异常");
            }else if (e instanceof UpdateException){
                result.setState(5003);
                result.setMessage("更新数据产生未知异常");
            }
                return result;
        }
        public final Integer getUidFormSession(HttpSession session)
        {
          return  Integer.valueOf(session.getAttribute("uid").toString());
        }

        public  final  String getUsernameFormSession(HttpSession session){
        return session.getAttribute("username").toString();
        }
}
