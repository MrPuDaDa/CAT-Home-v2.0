package org.jun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = {"org.jun.mapper"})
public class StaffApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaffApplication.class);
    }
}
