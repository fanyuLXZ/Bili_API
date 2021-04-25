package com.dreamwolf.watchhistory;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

//扫描Mapper 文件夹
@RefreshScope
@SpringBootApplication
@EnableFeignClients(basePackages = "com.dreamwolf.watchhistory.*")
public class WatchHistoryApplication {
  public static void main(String[] args) {
    SpringApplication.run(WatchHistoryApplication.class, args);
  }

}
