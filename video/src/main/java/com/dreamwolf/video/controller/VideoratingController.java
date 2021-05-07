package com.dreamwolf.video.controller;


import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.video.Videorating;
import com.dreamwolf.entity.video.web_interface.VideoList;
import com.dreamwolf.video.service.VideoratingService;
import org.springframework.web.bind.annotation.GetMapping;
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
public class VideoratingController {

    @Resource
    private VideoratingService videoratingService;

    @GetMapping(value = "/videoratbvID")
    public ResponseData selectbvidl(Integer bvID){
            //根据视频id查询视频评分
            Videorating videorating = videoratingService.selectbvid(bvID);
        return new ResponseData(0,"",0,videorating);
    }

    @GetMapping(value = "/videoratlist")
    public ResponseData selectlists(){
            //查询视频评分表所有数据
        List<Videorating> videoratings = videoratingService.selectlist();
        VideoList videoList = new VideoList(videoratings);
        return new ResponseData(0,"",0,videoList);
    }


}

