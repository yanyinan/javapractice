package com.demo.shopdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demo.shopdemo.login.dao")
public class ShopDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShopDemoApplication.class, args);
    }

}
