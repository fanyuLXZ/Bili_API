package com.dreamwolf.video.entity.web_interface;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Region {

    private String author;  //作者
    private int aid;   //视频id
    private String bvid;    //bv号视频id
    private Long coins; //投币数
    private String description;//视频文章 简介
    private int duration;//时长
    private Long favorites;  //收藏数
    private int mid;    //作者id
    private String pic;//预览图
    private Long play;//观看数
    private double pts;    //综合评分
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create; //视频发表时间
    private Long review; //评论数
    private String title;   //视频标题
    private String typename;    //视频类型 子分区名


}
