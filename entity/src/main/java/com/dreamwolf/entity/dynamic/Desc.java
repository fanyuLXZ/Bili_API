package com.dreamwolf.entity.dynamic;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class Desc {
    private Integer uid;//发布人
    private Integer type;//类型
    private Integer comment;//评论数量
    private Integer like;//点赞数
    private Integer is_liked;//是否点赞
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;//发布时间
    private Integer dynamic_id;//动态id
    private UserProfile user_profile;//用户信息

    public Desc(Integer uid, Integer type, Integer comment, Integer like, Integer is_liked, LocalDateTime timestamp, Integer dynamic_id, UserProfile userProfile) {
        this.uid = uid;
        this.type = type;
        this.comment = comment!=null?comment:0;
        this.like = like!=null?like:0;
        this.is_liked = is_liked!=null?is_liked:0;
        this.timestamp = timestamp;
        this.dynamic_id = dynamic_id;
        this.user_profile = userProfile;
    }

    public Desc() {
    }
}
