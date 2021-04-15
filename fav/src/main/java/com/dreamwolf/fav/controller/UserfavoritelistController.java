package com.dreamwolf.fav.controller;

import com.dreamwolf.fav.pojo.User;
import com.dreamwolf.fav.pojo.Video;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户收藏列表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/fav/userfavoritelist")
public class UserfavoritelistController {

    public Video video;
    public User user;

    @GetMapping("/folder")
    public Map userfavlist(){

        Map map = new HashMap();
        map.put("code",0);
        map.put("message","0");
        map.put("ttl",1);
//      data:   返回list 固定返回两个值 第一个值为普通收藏夹("我创建的收藏夹") 第二个为稍后再看
            Map datamap = new HashMap();
                Map maplist1 = new HashMap();//固定值：普通收藏夹为1，稍后再看为2  maplist1
                maplist1.put("id",1);
                maplist1.put("name","我创建的收藏夹");
                    Map map1 = new HashMap();   //收藏夹对象
                    map1.put("count",1);        //数量
                        Map list1 = new HashMap();
                            Map list1map = new HashMap();
                            list1map.put("id",1066079554);  //收藏夹id
                            list1map.put("mid",628677354);
                            list1map.put("title","默认收藏夹");     //收藏夹名
                            list1map.put("media_count",0);    //收藏夹内视频(媒体)数
                        list1.put("id",list1map);
                    map1.put("list",list1);         //收藏夹对象集合
                maplist1.put("mediaListResponse",map1);
//                -------------------------------
                Map maplist2 = new HashMap();//固定值：普通收藏夹为1，稍后再看为2  maplist2
                maplist2.put("id",2);
                maplist2.put("name","稍后再看");
                     Map map2 = new HashMap();    //收藏夹对象
                     map2.put("count",0);       //数量
                     map2.put("list",null);      // 收藏夹对象集合
                maplist2.put("mediaListResponse",map2);
            datamap.put("0",maplist1);
            datamap.put("1",maplist2);
        map.put("data",datamap);

        return map;
    }



    @GetMapping("/resource")
    public Map fovresource(Integer media_id){
        Map map = new HashMap();
        map.put("id",media_id);     //收藏夹id
        map.put("title",video.getBvTitle());       //标题
        map.put("cover",null);      //封面
        map.put("page",1);      //p数 暂时为1
        map.put("type",2);      //类型 暂时为2   2: 'archive', 12: 'audio', 21: 'ugcSeason'4
            Map uppermap = new HashMap();
            uppermap.put("mid",user.getUID());      //uid
            uppermap.put("name",user.getUserName());        //名字
            uppermap.put("face",user.getHeadImgPath());        //头像图片路径
        map.put("upper",uppermap);     //up主 object
        map.put("bvid",video.getBvID());    //bv号

        return map;
    }


}

