package com.wlm.store.mapper;

import com.wlm.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.rmi.server.UID;
import java.util.Date;

/**
 * 类名：UserMapper
 * 描述：
 * 作者：汪灵敏
 * 日期：2021/8/26 2:16
 * 版本：V1.0
 */
//@Mapper
public interface UserMapper {
    /*插入
    * 返回受印象行数*/
    Integer insert(User user) ;
    /**/
    User findByUsername(@Param("username") String username);

    Integer updatePasswordById(Integer uid ,
                               String password,
                               String modifiedUser,
                               Date   modifiedTime);

   User findById (Integer uid);

   Integer updateData(User user);
}
