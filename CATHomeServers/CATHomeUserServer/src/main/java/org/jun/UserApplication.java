package org.jun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: 蒲俊
 * @Description: 用户微服务启动接口
 * @DateTime: 2023/6/19 10:06
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"org.jun.mapper"})
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }
}
