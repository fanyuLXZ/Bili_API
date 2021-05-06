package com.dreamwolf.entity.video.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatMap {

    private Integer aid;//  视频id
    private Long coin;//硬币数
    private Long view; //视频弹幕数
    private Long like; //点赞数

}
