package com.dreamwolf.video.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 *
 * 视频点赞表
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoLike {

    private Integer bvID;   //视频ID
    private Integer uID;    //点赞的用户ID
    private Integer status; //点赞状态，0为未作任何操作，1为点赞  默认为0
    private Date createTime;    //点赞时间


}
