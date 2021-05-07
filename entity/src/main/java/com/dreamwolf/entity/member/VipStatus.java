package com.dreamwolf.entity.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

//vip对象
@Data
public class VipStatus {
    private boolean status;//是否为会员
    private Integer type;//会员类型
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime due_data;//会员有效时间
    private Label label;//固定值 对象
    private String avatar_subscript_url;//返回固定链接

    public VipStatus(boolean status, Integer type, LocalDateTime due_data) {
        this.status = status;
        this.type = type;
        this.due_data = due_data;
    }

    public VipStatus(Integer type, boolean status, LocalDateTime due_data, Label label, String avatar_subscript_url) {
        this.status = status;
        this.type = type;
        this.due_data = due_data;
        this.label = label;
        this.avatar_subscript_url = avatar_subscript_url;
    }

    public VipStatus(boolean status) {
        this.status = status;
    }

    public VipStatus(boolean status, Integer type) {
        this.status = status;
        this.type = type;
    }
    public VipStatus(){}
}
