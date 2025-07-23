package com.takeout.dish;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 菜品服务启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.takeout.dish.mapper")
@ComponentScan({"com.takeout.dish", "com.takeout.common"})
public class DishServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DishServiceApplication.class, args);
    }
} 