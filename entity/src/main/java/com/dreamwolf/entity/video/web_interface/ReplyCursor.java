package com.dreamwolf.entity.video.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCursor {
    int all_count = 0;
    boolean is_begin = false;
    boolean is_end = false;
    // 查询模式 3为热度 2为时间 默认热度
    int mode= 3;
    String name = "热门评论";
    // 上一页
    int prev = 1;
    // 下一页
    int next = 1;
}
