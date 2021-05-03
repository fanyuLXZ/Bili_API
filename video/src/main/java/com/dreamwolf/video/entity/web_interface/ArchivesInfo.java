package com.dreamwolf.video.entity.web_interface;


import com.dreamwolf.member.business.entity.web_interface.OwnerInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArchivesInfo {
    private int aid;//视频id
    private String bvid;//视频id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;//发表时间
    private String desc;//视频文章
    private int duration;//视频时长
    private OwnerInfo owner;//作者信息
    private String pic;//预览图
    private String title;//视频标题
    private String tname;//视频分区
    private Statinfo statinfo;//视频数据

}
