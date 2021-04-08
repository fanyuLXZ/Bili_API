package com.dreamwolf.zoning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class ZoningApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZoningApplication.class, args);
    }
}
