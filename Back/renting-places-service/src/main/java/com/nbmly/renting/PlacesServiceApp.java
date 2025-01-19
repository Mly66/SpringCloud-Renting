package com.nbmly.renting;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.nbmly.renting.mapper")
public class PlacesServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(PlacesServiceApp.class, args);
    }
}