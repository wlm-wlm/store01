package com.wlm.store;

import org.aspectj.lang.annotation.Aspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wlm.store.mapper")

public class WlmStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(WlmStoreApplication.class, args);
    }

}
