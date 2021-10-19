package com.wlm.store.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wlm.store.entity.User;
import com.wlm.store.mapper.UserMapper;
import com.wlm.store.service.IUserService;
import com.wlm.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 类名：UserServiceImpl
 * 描述：
 * 作者：汪灵敏
 * 日期：2021/8/26 20:16
 * 版本：V1.0
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    public UserMapper userMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void reg(User user) {
       //调用findByUsername方法
        String username = user.getUsername();
        User result = userMapper.findByUsername(username);
        //判断结果集
        if(result !=null){
            //抛出

        throw new UsernameDuplicatedException("用户名被占用");
    }
        String oldPassword = user.getPassword();
        //获取盐值随机生成
        String salt = UUID.randomUUID().toString().toUpperCase();
        //补全盐值
        user.setSalt(salt);
        //将密码和盐值进行加密处理
        String password = getMD5password(oldPassword, salt);
        //重设置到user对象
        user.setPassword(password);
        //补充数据
        user.setIsDelete(0);

        //补充 四个日志字段
        user.setCreatedUser(username);
        user.setModifiedUser(username);
        Date data = new Date();
        user.setCreatedTime(data);
        user.setModifiedTime(data);

        //执行注册 正常执行insert==1
        Integer insert = userMapper.insert(user);
        if (insert!=1){
            throw new InsertException("用户注册过程中产生未知异常");
        }
    }

    @Override
    public User login(String username, String password) {
        User result = userMapper.findByUsername(username);
        if(result == null){
            throw new UserNotFindException("用户数据不存在");
        }
        //检测密码，获取加密后密码
        String oldPassword = result.getPassword();
        //获取盐值
        String salt = result.getSalt();
        //相同方法进行加密
        String newMd5Password = getMD5password(password,salt);

        if (!oldPassword.equals(newMd5Password.toUpperCase())){
            throw new PasswordNotMatchException("用户密码错误");
        }

        //判断id_delete是否1标记删除
        if(result.getIsDelete()==1){
            throw new UserNotFindException("用户数据不存在,已删除");
        }
        //提升系统性能
       /* User user = userMapper.findByUsername(username);*/
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());/*头像*/
        return user;
    }

    @Override
    @Transactional
    public void updatePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword) {
        User result = userMapper.findById(uid);
        if (result==null ||result.getIsDelete()==1){
            throw new UserNotFindException("用户不存在");
        }
      /*加密*/
        String md5OldPassword = getMD5password(oldPassword, result.getSalt());
        if (!result.getPassword().equals(md5OldPassword)){
            throw new PasswordNotMatchException("密码错误");
        }
        /*加密新密码*/
        String md5NewPassword = getMD5password(newPassword, result.getSalt());
        /*修改*/
        Integer rows = userMapper.updatePasswordById(uid, md5NewPassword, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据产生未知异常");
        }

    }

    @Override

    public User getbyId(Integer id) {

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        User user = null;

        if(null == ops.get("id")){
            user = userMapper.findById(id);

            if (user==null ||user.getIsDelete()==1){
                throw new UserNotFindException("用户不存在");
            }
            String json = JSONObject.toJSONString(user);
            Math.random();
            ops.set("id",json);
            redisTemplate.expire("id" ,10000,TimeUnit.MILLISECONDS);

        }else {
            String id1 = ops.get("id");
            JSON parse = (JSON)JSONObject.parse(id1);
            User user1 = JSONObject.toJavaObject(parse, User.class);
            return user1;
        }


//        User user = new User();
//        user.setUsername(byId.getUsername());
//        user.setPhone(byId.getPhone());
//        user.setEmail(byId.getEmail());
//        user.setGender(byId.getGender());
        return user;
    }


    @Override
    public void updateData(Integer uid,  User user) {
        User byId = userMapper.findById(uid);
        if (byId==null ||byId.getIsDelete()==1){
            throw new UserNotFindException("用户不存在");
        }
        user.setUid(uid);
        user.setModifiedUser(user.getUsername());
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateData(user);
        if (rows!=1){
            throw new UpdateException("更新数据产生未知异常");
        }

    }


    /*定义MD5算法加密*/
    private  String getMD5password(String password , String salt){
        //三次加密
        for (int i = 0; i < 3; i++) {
         password = DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
    }
       return  password;
    }
}
