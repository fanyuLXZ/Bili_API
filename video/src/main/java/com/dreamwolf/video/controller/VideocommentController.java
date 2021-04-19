package com.dreamwolf.video.controller;


import com.dreamwolf.video.pojo.Videocomment;
import com.dreamwolf.video.service.VideocommentService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
@RequestMapping("/video/videocomment")
public class VideocommentController {

    @Resource
    private VideocommentService videocommentService;

    @RequestMapping(value = "/aaa")
    public Map selectbvid(Integer bvid){
        Map map = new HashMap();
        Map listmap = new HashMap();
        List<Videocomment> videocommentList = videocommentService.selectbvID(bvid);//根据视频id查询评论id
        listmap.put("cID", videocommentList);   //评论ID
        map.put("data",listmap);

        return map;
    }

    @RequestMapping(value = "/bbb")
    public Map selectlist(){
        Map map = new HashMap();
        List<Videocomment> videocommentList = videocommentService.selectlist();//查询视频评论表所有数据
        map.put("data",videocommentList);

        return map;
    }

}

