package com.dreamwolf.video.entity.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statinfo {

    private Integer aid;    //aid
    private Long dislike;   //点踩数
    private Long reply; //评论数
    private Long share;//转发数
    private int his_rank;   //排名
    private Long view ;//播放量
    private Long favorite; //收藏数
    private Long coin;   //投币数;
    private Long like;//点赞数

}
