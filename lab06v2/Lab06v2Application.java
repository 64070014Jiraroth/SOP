package com.example.lab06v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Lab06v2Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab06v2Application.class, args);
    }

}
