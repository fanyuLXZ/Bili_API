package com.dreamwolf.video.controller;


import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.video.Videocomment;
import com.dreamwolf.entity.video.web_interface.VideoList;
import com.dreamwolf.video.service.VideocommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
public class VideocommentController {

    @Resource
    private VideocommentService videocommentService;

    //根据视频id数组查询视频评论表数据
    @GetMapping("/viderbvidcommlist")
    public ResponseData<List<Videocomment>> videocombvidlist(Integer[] array){
        List<Videocomment> videocomments= videocommentService.selectvidercomlist(array);
        return new ResponseData(0,"",0,videocomments);
    }

    //根据视频id数组查询视频评论id 返回list
    @GetMapping("/videocommmarr")
    public ResponseData<List<Integer>> selectarr(Integer[] arr){
        List<Integer> integer= videocommentService.selectbvidarray(arr);
//        VideoList videoList = new VideoList(integer);
        return new ResponseData(0,"",0,integer);
    }

    /**
     * 根据bvid视频id去查找评论id
     * @param bvid
     * @return
     */
    @GetMapping(value = "/videocommbvid")
    public ResponseData selectbvids(Integer bvid){
        List<Videocomment> videocommentList = videocommentService.selectbvID(bvid);//根据视频id查询评论id
        VideoList videoList = new VideoList(videocommentList);
        return new ResponseData(0,"",0,videoList);
    }

    @GetMapping(value = "/videocommlist")
    public ResponseData selectlist(){
        List<Videocomment> videocommentList = videocommentService.selectlist();//查询视频评论表所有数据
        VideoList videoList = new VideoList(videocommentList);
        return new ResponseData(0,"",0,videoList);
    }

}

