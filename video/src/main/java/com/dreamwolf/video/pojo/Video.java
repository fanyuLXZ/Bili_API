package com.dreamwolf.video.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 视频基础信息表
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {

    private Integer bvID;   //bv号 (视频id)
    private Integer uID;    //视频作者ID
    private String bvTitle; //视频标题
    private String bvDesc;  //视频简介
    private Date bvPostTime; //视频上传日期（默认为当前时间）
    private Integer bvChildZoning;  //视频子分区ID
    private Integer bvIsDel;    //是否已删除，0为未删除，1为已删除


}
