package com.dreamwolf.fav;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

/**
 * a
 */
@RefreshScope
@SpringBootApplication
@EnableFeignClients(basePackages = "com.dreamwolf.fav.*")
public class FavApplication {
    public static void main(String[] args) {
        SpringApplication.run(FavApplication.class, args);
    }



}
