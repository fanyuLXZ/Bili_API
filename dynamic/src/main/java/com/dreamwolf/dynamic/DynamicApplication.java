package com.dreamwolf.dynamic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
@SpringBootApplication
public class DynamicApplication {
    public static void main(String[] args) {
        SpringApplication.run(DynamicApplication.class, args);
    }

}
