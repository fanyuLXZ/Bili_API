package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//扫描Mapper 文件夹
@SpringBootApplication
public class FavApplication {

  public static void main(String[] args) {
    SpringApplication.run(FavApplication.class, args);
  }

}
