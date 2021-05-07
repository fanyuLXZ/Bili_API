package com.dreamwolf.entity.message.web_interface;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.comment.web_interface.Commcidmap;
import com.dreamwolf.entity.video.Video;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MMItems {

    private String source_content;//被回复的评论内容
    private String type;//video代表视频，dynamic代表动态 reply代表文字
    private String business;//视频，动态，文字三个参数
    private String title;//被回复的评论 视频则为空
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reply_time;//当前时间
    private String uri;
    private Integer bvid;
    private String image;//当前 视频，动态的封面
    private String native_uri;


}
