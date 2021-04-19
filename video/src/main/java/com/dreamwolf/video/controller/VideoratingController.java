package com.dreamwolf.video.controller;


import com.dreamwolf.video.pojo.Videorating;
import com.dreamwolf.video.service.VideoratingService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频综合评分表，根据该表判断视频排名 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/video/videorating")
public class VideoratingController {

    @Resource
    private VideoratingService videoratingService;

    @RequestMapping(value = "/aaa")
    public Map selectbvidl(Integer bvID){
        Map map = new HashMap();
        //根据视频id查询视频评分
        Videorating videorating = videoratingService.selectbvid(bvID);
        map.put("data",videorating);

        return map;
    }

    @RequestMapping(value = "/bbb")
    public Map selectlists(){
        Map map = new HashMap();
        //查询视频评分表所有数据
        List<Videorating> videoratings = videoratingService.selectlist();
        map.put("data",videoratings);
        return map;
    }


}

