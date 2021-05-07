package com.dreamwolf.entity.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

//回复问问的评论对象
@Data
public class DataItem {
    private String source_content;//被回复的评论内容
    private String type;//ideo视频 dynamic动态 reply文字
    private String business;// 视频 动态 文字 三个参数
    private String title;//被回复的评论或视频
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reply_time;//当前时间
    private String uri;//当前评论 视频 动态的地址
    private String image;//当前视频 动态的封面
    private String natice_uri;//个人中心的地址

    public DataItem(String source_content, String type, String business, String title, LocalDateTime reply_time, String uri, String image, String natice_uri) {
        this.source_content = source_content;
        this.type = type;
        this.business = business;
        this.title = title;
        this.reply_time = reply_time;
        this.uri = uri;
        this.image = image;
        this.natice_uri = natice_uri;
    }
}
