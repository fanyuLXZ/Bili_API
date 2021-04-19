package com.dreamwolf.video.controller;


import com.dreamwolf.video.pojo.Videolike;
import com.dreamwolf.video.service.VideolikeService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频点赞表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/video/videolike")
public class VideolikeController {

    @Resource
    private VideolikeService videolikeService;

    @RequestMapping(value = "/aaa")
    public Map selectbvid(Integer bvid){
        Map map = new HashMap();
        //根据视频id查询视频下的点赞用户id
        List<Videolike> listmap = videolikeService.selectbvid(bvid);
        map.put("data",listmap);

        return map;
    }

    @RequestMapping(value = "/bbb")
    public Map selectlistt(){
        Map map = new HashMap();
        //查询视频点赞表的所有数据
        List<Videolike> listmap = videolikeService.selectlist();
        map.put("data",listmap);

        return map;
    }



}

