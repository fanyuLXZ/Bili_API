package com.dreamwolf.entity.watchhistory.web_interface;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Data
public class ListCursor {
    private String title;//视频标题
    private String long_title;//分p标题
    private String cover;//封面图片路径
    private String author_name;//作者名称
    private String uri;//视频链接
    private History history;//历史对象
    private Integer duration;//总时长
    private Integer progress;//观看时长
    private String show_title;//pgc卡用
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime view_at;//最后观看的时间

    public ListCursor() {
    }

    public ListCursor(String title, String long_title, String cover, String author_name, String uri, History history, Integer duration, Integer progress, String show_title, LocalDateTime view_at) {
        this.title = title;
        this.long_title = long_title;
        this.cover = cover;
        this.author_name = author_name;
        this.uri = uri;
        this.history = history;
        this.duration = duration;
        this.progress = progress;
        this.show_title = show_title;
        this.view_at = view_at;
    }
}
