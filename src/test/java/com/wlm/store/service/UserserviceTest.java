package com.wlm.store.service;

import com.wlm.store.entity.User;
import com.wlm.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 类名：UserserviceTest
 * 描述：
 * 作者：汪灵敏
 * 日期：2021/8/26 20:41
 * 版本：V1.0
 */
@SpringBootTest//标注当前类为测试类 不随项目打包发送
@RunWith(SpringRunner.class)//启动单元测试类
public class UserserviceTest {
         /*必须被@Test修饰
         * 返回类型为void
         * 方法参数列表不指定任何内容
         * 方法的访问修饰必须是public*/
        //idea 有监测功能，接口不能直接创建bean
        @Autowired
        private IUserService userService;
        @Test
        public void reg(){
            try {
                User user = new User();
                user.setUsername("张三");
                user.setPassword("123");
                userService.reg(user);
                System.out.println("ok");
            } catch (ServiceException e) {
                System.out.println(e.getClass().getName());
                System.out.println(e.getMessage());
            }
        }
}
