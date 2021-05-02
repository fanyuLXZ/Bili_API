package com.dreamwolf.video.entity.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Relatedinfo {

    private int aid;//视频id
    private String pic;//图片
    private String title;   //标题
//    private owner owner;     //用户对象
    private Statinfo statinfo;//播放量对象

}
