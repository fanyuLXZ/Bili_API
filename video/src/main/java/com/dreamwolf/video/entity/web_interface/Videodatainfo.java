package com.dreamwolf.video.entity.web_interface;

import com.dreamwolf.member.business.entity.web_interface.OwnerInfo;
import com.dreamwolf.member.business.entity.web_interface.VideoinfoOwnerInfo;
import com.dreamwolf.zoning.business.entity.web_interface.Deputydivision;
import com.dreamwolf.zoning.business.entity.web_interface.MainpardeputyInfo;
import com.dreamwolf.zoning.business.entity.web_interface.Mainpartition;
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
    private VideoinfoOwnerInfo videoinfoOwnerInfo;    //用户对象
    private Videoinfo videoinfo;//视频对象
    private Statinfo statinfo;  //对象集合
    private List relatedinfo;//视频推荐数组
    private Mainpartition mainpartition;
    private Deputydivision deputydivision;


}
