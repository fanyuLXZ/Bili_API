package com.dreamwolf.fav.controller;


import com.dreamwolf.fav.pojo.User;
import com.dreamwolf.fav.pojo.Userfavoritelist;
import com.dreamwolf.fav.pojo.Video;
import com.dreamwolf.fav.service.UserService;
import com.dreamwolf.fav.service.UserfavoritelistService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户收藏列表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/fav/userfavoritelist")
public class UserfavoritelistController {

    @Resource
    private UserfavoritelistService userfavoritelistService; //收藏夹

    @Resource
    private UserService userService;

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
                    List<Userfavoritelist> userfavoritelists = userfavoritelistService.selectlist();
                    map1.put("list",userfavoritelists);         //收藏夹对象集合
                maplist1.put("mediaListResponse",map1);
    //                -------------------------------
                Map maplist2 = new HashMap();//固定值：普通收藏夹为1，稍后再看为2  maplist2
                maplist2.put("id",2);
                maplist2.put("mediaListResponse",null);
            datamap.put("0",maplist1);
            datamap.put("1",maplist2);
        map.put("data",datamap);

        return map;
    }



    @GetMapping("/resource")
    public Map fovresource(Integer media_id){
        Map map = new HashMap();
        map.put("id",media_id);     //收藏夹id
        map.put("title","aa");      //标题
        map.put("cover","bbb");      //封面
        map.put("page",1);      //p数 暂时为1
        map.put("type",2);      //类型 暂时为2   2: 'archive', 12: 'audio', 21: 'ugcSeason'4
        Integer uid = userfavoritelistService.selectfavListID(media_id);//返回用户id
        User user = userService.selectuser(uid);//根据用户id查询的用户对象
        map.put("upper",user);     //up主 object
        map.put("bvid","aaa");    //bv号

        return map;
    }



}

