package com.nbmly.renting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.nbmly.renting.mapper")
public class HousingServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(HousingServiceApp.class, args);
    }
}
