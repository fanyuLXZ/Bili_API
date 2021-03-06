package com.dreamwolf.video.controller;


import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.video.Videolike;
import com.dreamwolf.entity.video.web_interface.VideoList;
import com.dreamwolf.video.service.VideoService;
import com.dreamwolf.video.service.VideolikeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 修改点赞关系表数据
     * @param bvid
     * @param uid
     * @return
     */
    @PostMapping("/updateinsetuid")
    public ResponseData updateinset(Integer bvid,Integer uid){
        //点了赞的就修改为没点赞的，没点赞就修改为点了赞的
        int result = videolikeService.updateinset(bvid,uid);
        return new ResponseData(0,"",1,result);
    }



    @GetMapping(value = "/videolikebvid")
    public ResponseData selectbvid(Integer bvid){
            //根据视频id查询视频下的点赞用户id
        List<Videolike> listmap = videolikeService.selectbvid(bvid);
        VideoList videoList = new VideoList(listmap);
        return new ResponseData(0,"",0,videoList);
    }

    @GetMapping(value = "/videolikelist")
    public ResponseData selectlistt(){
        //查询视频点赞表的所有数据
        List<Videolike> listmap = videolikeService.selectlist();
        VideoList videoList = new VideoList(listmap);
        return new ResponseData(0,"",0,videoList);
    }


    /**
     * 根据用户下面的视频拿到用户下所有的点赞了的视频
     * @param array
     * @return
     */
    @GetMapping("/videolikeuid")
    public ResponseData<List<Videolike>> sellist(Integer[] array)
    {
        List<Videolike> videolikes = videolikeService.selectbvidlist(array);
        return new ResponseData(0,"",0,videolikes);
    }



}

