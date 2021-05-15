package com.dreamwolf.video.controller;


import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.video.Videofavorite;
import com.dreamwolf.entity.video.web_interface.VideoCount;
import com.dreamwolf.entity.video.web_interface.VideoList;
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
    public ResponseData<VideoCount> selectint(Integer favListID){
        Integer count = videofavoriteService.selectfavListID(favListID);
        VideoCount videoCount = new VideoCount(count);
        return new ResponseData(0,"",0,videoCount);
    }


    @GetMapping(value = "/videofavbvID")
    public ResponseData selectbvid(Integer bvID){
        Map map = new HashMap();
        Videofavorite videofavorite = new Videofavorite();
        if(bvID !=null) {
            map.put("code",0);
            map.put("message","0");
        //根据被收藏的视频id查询收藏的视频收藏夹id
            videofavorite = videofavoriteService.selectbvID(bvID);
        }else{
            map.put("code",400);
            map.put("message","传入的参数(bvID)不能为空");
            map.put("data",null);
        }
        return new ResponseData(0,"",0,videofavorite);
    }

    @GetMapping(value = "/videofavlist")
    public ResponseData selectlistt(){
        //查询视频收藏表所有数据
            List<Videofavorite> videofavoriteList = videofavoriteService.selectlist();
        VideoList videoList = new VideoList(videofavoriteList);
        return new ResponseData(0,"",0,videoList);
    }

    /**
     * 添加操作
     * @param bvid 视频id
     * @param favid 收藏夹id
     * @return
     */
    @PostMapping("/inserfav")
    public ResponseData inserfav(Integer bvid,Integer favid){
        int result = videofavoriteService.inertfav(bvid,favid);
        return new ResponseData(0,"",1,result);
    }


}

