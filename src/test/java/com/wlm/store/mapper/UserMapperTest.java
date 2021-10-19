package com.wlm.store.mapper;

import com.wlm.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类名：UserMapperTest
 * 描述：
 * 作者：汪灵敏
 * 日期：2021/8/26 15:15
 * 版本：V1.0
 */
@SpringBootTest//标注当前类为测试类 不随项目打包发送
@RunWith(SpringRunner.class)//启动单元测试类
public class UserMapperTest {
  /*必须被@Test修饰
  * 返回类型为void
  * 方法参数列表不指定任何内容
  * 方法的访问修饰必须是public*/
  //idea 有监测功能，接口不能直接创建bean
  @Autowired
  private UserMapper userMapper;
  @Test
    public void insert(){
      User user = new User();
      user.setUsername("tom");
      user.setPassword("123");
     Integer rows = userMapper.insert(user);
        System.out.println(rows);
    }



}
