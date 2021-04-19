package com.dreamwolf.video.controller;


import com.dreamwolf.video.pojo.Videofavorite;
import com.dreamwolf.video.service.VideofavoriteService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频收藏表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/video/videofavorite")
public class VideofavoriteController {

    @Resource
    private VideofavoriteService videofavoriteService;


    @RequestMapping(value = "/aaa")
    public Map selectbvid(Integer bvID){
        Map map = new HashMap();
        //根据被收藏的视频id查询收藏的视频收藏夹id
        Videofavorite videofavorite = videofavoriteService.selectbvID(bvID);
        map.put("data",videofavorite);

        return map;
    }

    @RequestMapping(value = "/bbb")
    public Map selectlistt(){
        Map map = new HashMap();
        //查询视频收藏表所有数据
        List<Videofavorite> videofavoriteList = videofavoriteService.selectlist();
        map.put("data",videofavoriteList);

        return map;
    }


}

