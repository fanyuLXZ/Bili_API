package com.dreamwolf.video.controller;


import com.dreamwolf.video.pojo.Video;
import com.dreamwolf.video.service.VideoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频基础信息表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/video/video")
public class VideoController {

    @Resource
    private VideoService videoService;

    //通过子分区id查视频,返回list
    @GetMapping("/aaa")
    public Map videobvChildZoning(Integer bvChildZoning){
        Map map = new HashMap();
        List<Video> list = videoService.videoZoningIdlist(bvChildZoning);
        map.put("data",list);
        return map;
    }

    //通过bv号(视频id)查视频，返回对象
    @GetMapping("/bbb")
    public Map videobvID(Integer bvID){
        Map map = new HashMap();
        Video video = videoService.videobvIDlist(bvID);
        map.put("data",video);
        return map;
    }

    //通过作者id查视频，返回list
    @GetMapping("/ccc")
    public Map videouID(Integer uID){
        Map map = new HashMap();
        List<Video> list = videoService.videouIDlist(uID);
        map.put("data",list);
        return map;
    }

    //查video的所有数据
    @GetMapping("/ddd")
    public Map videoselectlist(){
        Map map = new HashMap();
        List<Video> list = videoService.selectlist();
        map.put("data", list);

        return map;
    }





}

