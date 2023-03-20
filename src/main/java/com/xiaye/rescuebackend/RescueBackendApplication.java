package com.xiaye.rescuebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaye.rescuebackend.mapper")
public class RescueBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(RescueBackendApplication.class, args);
    }
}