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
public class VideocommentController {

    @Resource
    private VideocommentService videocommentService;

    @RequestMapping(value = "/videocommbvid")
    public Map selectbvids(Integer bvid){
        Map map = new HashMap();
        if(bvid !=null) {
            map.put("code",0);
            map.put("message","0");
            Map listmap = new HashMap();
            List<Videocomment> videocommentList = videocommentService.selectbvID(bvid);//根据视频id查询评论id
            listmap.put("cID", videocommentList);   //评论ID
            map.put("data",listmap);
        }else{
            map.put("code",400);
            map.put("message","传入的参数(bvid)不能为空");
            map.put("data",null);
        }

        return map;
    }

    @RequestMapping(value = "/videocommlist")
    public Map selectlist(){
        Map map = new HashMap();
        if(map !=null) {
            map.put("code",0);
            map.put("message","0");
            List<Videocomment> videocommentList = videocommentService.selectlist();//查询视频评论表所有数据
            map.put("data",videocommentList);
        }else{
            map.put("code",400);
            map.put("message","数据为空");
            map.put("data",null);
        }
        return map;
    }

}

