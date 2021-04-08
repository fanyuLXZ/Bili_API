package com.dreamwolf.video.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 用户消息表
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMsgs {

    private Integer umID;   //主键自增ID，无意义
    private Integer userID; //发送信息的用户ID
    private Integer friendID;   //接收信息的用户ID
    private String sender;  //留言发送者
    private String receiver;    //留言接收者
    private Date updateTime;    //发送信息时间
    private String content; //留言内容



}
