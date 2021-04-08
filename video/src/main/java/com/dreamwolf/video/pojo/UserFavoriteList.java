package com.dreamwolf.video.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

/**
 * 用户收藏列表
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFavoriteList {

    private Integer favListID;  //视频收藏夹ID，主键唯一
    private Integer uID;    //创建收藏夹的用户ID
    private String name;    //收藏夹名称，可以重复
    private Integer isSecret;   //是否为私密收藏夹，私密为1，公开为0
    private Integer tLikeNum;   //收藏夹的总点赞数
    private String desc;    //收藏夹描述


}
