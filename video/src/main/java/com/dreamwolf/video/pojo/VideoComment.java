package com.dreamwolf.video.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * 视频评论关系表
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoComment {

    private Integer bvID;   //视频ID
    private Integer cID;    //评论ID



}
