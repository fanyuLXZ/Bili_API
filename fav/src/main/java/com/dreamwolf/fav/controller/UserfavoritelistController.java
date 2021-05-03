package com.dreamwolf.fav.controller;


import com.dreamwolf.entity.fav.Userfavoritelist;
import com.dreamwolf.entity.fav.Videofavorite;
import com.dreamwolf.entity.member.User;
import com.dreamwolf.fav.service.*;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
public class UserfavoritelistController {

    @Resource
    private UserfavoritelistService userfavoritelistService; //收藏夹

    @Resource
    private VideofavoriteService videofavoriteService;

    @Resource
    private Videofav videofav;

    @Resource
    private UserlistService selectuser;

    @GetMapping("/folder")
    public Map userfavlist(){

        Map map = new HashMap();
        if(map !=null) {
            map.put("code", 0);
            map.put("message", "0");
            map.put("ttl", 1);
            List data = new ArrayList<>();
            int[] array = new int[]{1, 2};
            Map maplistmap = new HashMap();//固定值：普通收藏夹为1，稍后再看为2  maplist1
            maplistmap.put("id", array[0]);
            maplistmap.put("name", "我创建的收藏夹");
            Map mapobject = new HashMap();   //收藏夹对象
            List<Userfavoritelist> userfavoritelists = userfavoritelistService.selectlist();
            mapobject.put("count", userfavoritelists.size());        //数量
            List<Map> new_userfavoritelists = new ArrayList<>();
            for (Userfavoritelist userfavorite : userfavoritelists) {
                Map mapmap = new HashMap();
                Integer videocount = videofav.selectfavListID(userfavorite.getFavListID());
                mapmap.put("id", userfavorite.getFavListID());
                mapmap.put("mid", userfavorite.getUID());
                mapmap.put("title", userfavorite.getName());
                mapmap.put("media_count", videocount);
                new_userfavoritelists.add(mapmap);
            }
            mapobject.put("list", new_userfavoritelists);         //收藏夹对象集合
            maplistmap.put("mediaListResponse", mapobject);
            //                -------------------------------
            Map maplistnull = new HashMap();//固定值：普通收藏夹为1，稍后再看为2  maplist2
            maplistnull.put("id", array[1]);
            Map mapcou = new HashMap();
            mapcou.put("count", 1);
            mapcou.put("list", null);
            maplistnull.put("mediaListResponse", mapcou);

            data.add(maplistmap);
            data.add(maplistnull);

            map.put("data", data);
        }else{
            map.put("code",400);
            map.put("message","数据为空");
            map.put("data",null);
        }
        return map;
    }



    @GetMapping("/resource")
    public Map fovresource(Integer media_id){
        Map map = new HashMap();
        if(media_id!=null) {
            map.put("code",0);
            map.put("message","0");
            //根据收藏夹id(media_id)查询视频
            List<Videofavorite> list= videofavoriteService.selectlist(media_id);
            List<Map> listmap = new ArrayList<>();
            for (Videofavorite videofavorite : list) {
                Map data = new HashMap();
                data.put("id", videofavorite.getFavListID());     //收藏夹id
                data.put("title",videofavorite.getVideo().getBvTitle());      //标题
                data.put("cover", videofavorite.getVideo().getBvCoverImgPath());      //封面
                data.put("page", 1);      //p数 暂时为1
                data.put("type", 2);      //类型 暂时为2   2: 'archive', 12: 'audio', 21: 'ugcSeason'4
                Integer uid = userfavoritelistService.selectfavListID(media_id);//返回用户id
                User user = selectuser.selectuid(uid);//根据用户id查询的用户对象
                Map usermap = new HashMap();
                usermap.put("mid",user.getuID());
                usermap.put("name",user.getUserName());
                usermap.put("face",user.getHeadImgPath());
                data.put("upper", usermap);     //up主 object
                data.put("bvid", "bv"+videofavorite.getVideo().getBvID());    //bv号
                listmap.add(data);
            }
            map.put("data",listmap);

        }else{
            map.put("code",400);
            map.put("message","传入的参数(media_id)不能为空");
            map.put("data",null);
        }

        return map;
    }





}

