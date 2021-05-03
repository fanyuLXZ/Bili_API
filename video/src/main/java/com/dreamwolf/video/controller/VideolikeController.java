package com.dreamwolf.video.controller;


import com.dreamwolf.entity.video.Videolike;
import com.dreamwolf.video.service.VideoService;
import com.dreamwolf.video.service.VideolikeService;
import org.springframework.web.bind.annotation.GetMapping;
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
public class VideolikeController {

    @Resource
    private VideolikeService videolikeService;

    @Resource
    private VideoService videoService;

    @GetMapping(value = "/videolikebvid")
    public Map selectbvid(Integer bvid){
        Map map = new HashMap();
        if(bvid !=null) {
            map.put("code", 0);
            map.put("message", "0");
            //根据视频id查询视频下的点赞用户id
            List<Videolike> listmap = videolikeService.selectbvid(bvid);
            map.put("data", listmap);
        }else{
            map.put("code",400);
            map.put("message","传入的参数(bvid)不能为空");
            map.put("data",null);
        }

        return map;
    }

    @GetMapping(value = "/videolikelist")
    public Map selectlistt(){
        Map map = new HashMap();
        if(map !=null) {
            map.put("code", 0);
            map.put("message", "0");
            //查询视频点赞表的所有数据
            List<Videolike> listmap = videolikeService.selectlist();
            map.put("data",listmap);
        }else{
            map.put("code",400);
            map.put("message","数据为空");
            map.put("data",null);
        }

        return map;
    }


    /**
     * 根据用户下面的视频拿到用户下所有的点赞了的视频
     * @param array
     * @return
     */
    @GetMapping("/videolikeuid")
    public List<Videolike> sellist(Integer[] array)
    {
        return videolikeService.selectbvidlist(array);
    }



}

