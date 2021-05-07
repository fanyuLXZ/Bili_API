package com.dreamwolf.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;

@RefreshScope
@SpringBootApplication
@EnableFeignClients(basePackages = "com.dreamwolf.video.*")
public class VideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoApplication.class, args);
    }

    @GetMapping("a")
    public String a(){
        return "a";
    }
}
