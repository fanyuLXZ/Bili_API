package com.dreamwolf.video.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * 视频数据表
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoData {

    private Integer bvID;   //视频对应BV号
    private Long bvPlayNum; //视频播放数
    private Long bvPopupsNum;   //视频弹幕数
    private Long bvLikeNum; //视频顶数
    private Long bvCoinNum; //视频硬币数
    private Long bvFavoriteNum; //视频收藏数
    private Long bvRetweetNum;  //视频转发数
    private Long bvCommentNum;  //视频评论数


}
