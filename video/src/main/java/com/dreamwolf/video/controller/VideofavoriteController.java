package com.dreamwolf.video.controller;


import com.dreamwolf.entity.video.Videofavorite;
import com.dreamwolf.video.service.VideofavoriteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class VideofavoriteController {

    @Resource
    private VideofavoriteService videofavoriteService;

    @PostMapping("/videocount")
    public int selectint(Integer favListID){
        Integer count = videofavoriteService.selectfavListID(favListID);

        return count;
    }


    @GetMapping(value = "/videofavbvID")
    public Map selectbvid(Integer bvID){
        Map map = new HashMap();
        if(bvID !=null) {
            map.put("code",0);
            map.put("message","0");
        //根据被收藏的视频id查询收藏的视频收藏夹id
            Videofavorite videofavorite = videofavoriteService.selectbvID(bvID);
            map.put("data",videofavorite);
        }else{
            map.put("code",400);
            map.put("message","传入的参数(bvID)不能为空");
            map.put("data",null);
        }


        return map;
    }

    @GetMapping(value = "/videofavlist")
    public Map selectlistt(){
        Map map = new HashMap();
        if(map !=null) {
            map.put("code",0);
            map.put("message","0");
        //查询视频收藏表所有数据
            List<Videofavorite> videofavoriteList = videofavoriteService.selectlist();
            map.put("data",videofavoriteList);
        }else{
            map.put("code",400);
            map.put("message","数据为空");
            map.put("data",null);
        }

        return map;
    }


}

