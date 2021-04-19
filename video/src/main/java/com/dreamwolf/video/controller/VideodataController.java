package com.dreamwolf.video.controller;


import com.dreamwolf.video.pojo.Videodata;
import com.dreamwolf.video.service.VideodataService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频数据表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/video/videodata")
public class VideodataController {

    @Resource
    private VideodataService videodataService;

    @RequestMapping(value = "/aaa")
    public Map selectdvid(Integer bvID){
        Map map = new HashMap();
        Videodata videodata = videodataService.selectbvID(bvID); //根据bvID查询的视频基本数据
        map.put("data",videodata);

        return map;
    }

    @RequestMapping(value = "/bbb")
    public Map selectlistt(){
        Map map = new HashMap();
        List<Videodata> videodata = videodataService.selectlist();//查询视频数据表所有数据
        map.put("data",videodata);

        return map;
    }

}

