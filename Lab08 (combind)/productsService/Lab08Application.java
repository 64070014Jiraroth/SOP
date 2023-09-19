package com.example.ProductsService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Lab08Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab08Application.class, args);
    }

}
