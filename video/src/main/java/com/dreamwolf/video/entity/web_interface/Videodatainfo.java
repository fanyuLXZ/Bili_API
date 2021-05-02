package com.dreamwolf.video.entity.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 视频显示
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Videodatainfo {

    private Integer aid;    //视频id
//    private VideoinfoOwnerInfo    //用户对象
    private Videoinfo videoinfo;//视频对象
    private Statinfo statinfo;  //对象集合
    private List relatedinfo;//视频推荐数组
//     - mainpartition 主分区
//                - id 主分区 int
//                - name 分区名 string
//            - deputydivision 副分区
//                - id 主分区 int
//                - name 分区名 string


}
