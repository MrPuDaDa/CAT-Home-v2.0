package org.jun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: 蒲俊
 * @Description: 商品微服务启动类
 * @DateTime: 2023/5/31 16:27
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"org.jun.mapper"})
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class,args);}
}
