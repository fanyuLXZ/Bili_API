package com.dreamwolf.video.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 视频收藏表
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoFavorite {

    private Integer bvID;   //被收藏的视频ID
    private Integer favListID;  //视频收藏夹ID
    private Date favTime;   //收藏时间，默认为当前时间，不支持设置系统时间


}
