package com.wlm.store.service;

import com.wlm.store.entity.User;

/**
 * 类名：IUserService
 * 描述：
 * 作者：汪灵敏
 * 日期：2021/8/26 20:14
 * 版本：V1.0
 */

public interface IUserService {
    void reg(User user);//注册
    User login(String username,String password);
    void updatePassword(Integer uid,
                        String username,
                        String oldPassword,
                        String newPassword
            );
   User getbyId(Integer id);

    void updateData(Integer uid,
                    User user);
}
