package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

//扫描Mapper 文件夹
@RefreshScope
@SpringBootApplication
public class Watch_HistoryApplication {

  public static void main(String[] args) {
    SpringApplication.run(Watch_HistoryApplication.class, args);
  }

}
