package com.dreamwolf.entity.video.web_interface;

import com.dreamwolf.entity.member.web_interface.VideoinfoOwnerInfo;
import com.dreamwolf.entity.zoning.web_interface.Deputydivision;
import com.dreamwolf.entity.zoning.web_interface.Mainpartition;
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
    private VideoinfoOwnerInfo owner;    //用户对象
    private Videoinfo video;//视频对象
    private Statinfo stat;  //对象集合
    private List related;//视频推荐数组
    private Mainpartition mainpartition;
    private Deputydivision deputydivision;


}
