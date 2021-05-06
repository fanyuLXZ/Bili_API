package com.dreamwolf.entity.video.web_interface;

import com.dreamwolf.entity.member.web_interface.OwnerInfo;
import com.dreamwolf.entity.video.Videodata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoMaplist {

    private Integer aid;    //视频id
    private String pic; //视频封面图
    private String cover;//封面
    private Integer uid;//作者id
    private String uri;//路径
    private String title;//标题
    private OwnerInfo owner;    //用户对象
    private StatMap stat;       //视频数据对象
    private int duration;//视频时长
    private String bvid;//bv号
    private double pts; //评分
    private String typename;//分区名
    private String long_title;//空

}
