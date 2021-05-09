package com.dreamwolf.entity.watchhistory.web_interface;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class Cursor {
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime max;//最大时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime view_at;//当前返回的视频列表中最后的观看时间
    private Integer ps;//数量
    private String business;//分类 暂未archive

    public Cursor() {
    }

    public Cursor(LocalDateTime max, LocalDateTime view_at, Integer ps, String business) {
        this.max = max;
        this.view_at = view_at;
        this.ps = ps;
        this.business = business;
    }
}
