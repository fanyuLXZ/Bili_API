package com.dreamwolf.zoning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RefreshScope
@SpringBootApplication
@EnableFeignClients(basePackages = "com.dreamwolf.zoning.business.*")
public class ZoningApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZoningApplication.class, args);
    }
}
