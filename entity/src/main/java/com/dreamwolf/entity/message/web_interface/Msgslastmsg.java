package com.dreamwolf.entity.message.web_interface;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msgslastmsg {

    private Integer sender_uid;//最新聊天数据的发送者
    private Integer receiver_id;//最新聊天数据的接收这者
    private String content;//最新聊天的内容
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;//聊天记录添加的数据 //发送这一条数据的时间


}
