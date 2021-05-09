package com.dreamwolf.entity.dynamic;

import com.dreamwolf.entity.member.ReplyUser;
import com.dreamwolf.entity.member.Users;
import com.dreamwolf.entity.message.web_interface.Items;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 点赞集合
 */
@Data
public class IikesItems {
    private Integer id;//个人id
    private List<Users> users;//用户信息对象数组
    private Items item;//被点赞的对象
    private String counts;//此评论的总人数
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime like_time;//最新点赞的时间

    public IikesItems() {
    }

    public IikesItems(Integer id, List<Users> users, Items item, String counts, LocalDateTime like_time) {
        this.id = id;
        this.users=users;
        this.item = item;
        this.counts = counts;
        this.like_time = like_time;
    }
}
