package org.jun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: 蒲俊
 * @Description: TODO
 * @DateTime: 2023/7/2 10:49
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"org.jun.mapper"})
public class SellerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SellerApplication.class);
    }
}
