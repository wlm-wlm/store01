package com.wlm.store.controller;

import com.sun.net.httpserver.HttpsServer;
import com.wlm.store.entity.User;
import com.wlm.store.service.IUserService;
import com.wlm.store.until.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 类名：UserController
 * 描述：
 * 作者：汪灵敏
 * 日期：2021/8/27 0:08
 * 版本：V1.0
 */
//@Controller
@RestController
@RequestMapping("users")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;
   /* @RequestMapping("reg")
    //@ResponseBody//响应结果以json格式进行数据处理响应给前端
    public JsonResult<Void>reg(User user){
        JsonResult<Void> result = new JsonResult<>();
        try {
            userService.reg(user);
            result.setState(520);
            result.setMessage("注册成功");
        } catch (UsernameDuplicatedException e) {
            result.setState(4000);
           result.setMessage("用户名被占用");
        }catch (InsertException e){
            result.setState(5000);
            result.setMessage("注册产生未知的异常");
        }

        return result;
    }*/

    @RequestMapping("reg")
    //@ResponseBody//响应结果以json格式进行数据处理响应给前端
    public JsonResult<Void>reg(User user){
        userService.reg(user);
        return new JsonResult<>(ok);
    }
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User user1 = new User();
        System.out.println(user1.hashCode());
        System.out.println(user1);

        User user = userService.login(username, password);
        User data = userService.login(username, password);
        session.setAttribute("uid",data.getUid());
        session.setAttribute("username",data.getUsername());
        //获取session绑定数据
        System.out.println(getUidFormSession(session));
        System.out.println(getUsernameFormSession(session));
        return  new JsonResult<User>(ok,user);
    }
 @RequestMapping("update_password")
 public JsonResult<User> changePassword (String oldPassword,String newPassword,HttpSession session){
     Integer uid = getUidFormSession(session);
     String username = getUsernameFormSession(session);
     userService.updatePassword(uid,username,oldPassword,newPassword);
     return new JsonResult<>(ok);
 }


    @RequestMapping("getById")
    public JsonResult<User> getById(HttpSession session){
        Integer uid = getUidFormSession(session);
        User user = userService.getbyId(uid);
        return  new JsonResult<>(ok,user);

    }
 @RequestMapping("update_data")
 public JsonResult<User> changeData(User user,HttpSession session){
     Integer uid = getUidFormSession(session);
     userService.updateData(uid,user);
     return  new JsonResult<>(ok,user);
 }
}
