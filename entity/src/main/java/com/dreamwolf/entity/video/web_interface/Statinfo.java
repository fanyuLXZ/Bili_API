package com.dreamwolf.entity.video.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statinfo {

    private Integer aid = 0;    //aid
    private Long dislike = 0L;   //点踩数
    private Long reply = 0L; //评论数
    private Long share = 0L;//转发数
    private int his_rank;   //排名
    private Long view = 0L;//播放量
    private Long favorite = 0L; //收藏数
    private Long coin = 0L;   //投币数;
    private Long like = 0L;//点赞数

}
