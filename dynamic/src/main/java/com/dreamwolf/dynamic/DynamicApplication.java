package com.dreamwolf.dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RefreshScope
@SpringBootApplication
@EnableFeignClients(basePackages = "com.dreamwolf.dynamic.*")
public class DynamicApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicApplication.class, args);
    }

}
