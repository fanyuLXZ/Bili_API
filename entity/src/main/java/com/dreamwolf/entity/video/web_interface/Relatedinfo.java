package com.dreamwolf.entity.video.web_interface;

import com.dreamwolf.entity.member.web_interface.OwnerInfo;
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
    private OwnerInfo ownerInfo;     //用户对象
    private Statinfo statinfo;//播放量对象

}
