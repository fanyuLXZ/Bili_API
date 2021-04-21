package com.dreamwolf.watchhistory.controller;

import com.dreamwolf.watchhistory.entities.User;
import com.dreamwolf.watchhistory.entities.Video;
import com.dreamwolf.watchhistory.entities.VideoHistory;
import com.dreamwolf.watchhistory.entities.Videocomment;
import com.dreamwolf.watchhistory.service.UserService;
import com.dreamwolf.watchhistory.service.VideoHistoryService;
import com.dreamwolf.watchhistory.service.VideoService;
import com.dreamwolf.watchhistory.service.VideocommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

///**
// * @author: wzx
// * @data: 2021/4/7 10:02
// * @version: 1.0
// */
//用户观看历史表

@ResponseBody
@RestController
@RequestMapping("/watch-history/videoHistoryController")
public class VideoHistoryController {
    @Autowired
    VideoHistoryService videoHistoryService;
    @Autowired
    VideocommentService videocommentService;
    @Autowired
    VideoService videoService;
    @Autowired
    UserService userService;
    @GetMapping("/list")
    public Map videoHistorylist(){
        Integer id=5;
//        videoHistoryService.getCxqb(id).getBvID();
        Map<String,Object> map=new HashMap();
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> listmap=new HashMap();
        listmap.put("title",videoService.getVideobvID(id).getBvTitle());
        listmap.put("long_title","");//分p标题
        listmap.put("cover",videoService.getVideobvID(id).getBvCoverImgPath());//封面图片路径
        listmap.put("author_name",userService.getUseruID(id).getUserName());//作者名称
        listmap.put("uri",videoService.getVideobvID(id).getBvVideoPath());//视频链接
        Map<String,Object> history=new HashMap();
            history.put("oid","hid");
            history.put("business","archive");
            history.put("page",1);
            listmap.put("history",history);
//        listmap.put("history",);//历史对象
//                - oid hid
//                - business 类型 string 暂时"archive"
//                - page p数 int 暂时1

        listmap.put("duration","duration");//总时长
        listmap.put("progress",-1);//观看时长
        listmap.put("show_title","");//pgc卡用
        listmap.put("view_at",videoHistoryService.getCxqb(id).getTimelinePosition());
        list.add(listmap);
        map.put("list",list);
        return map;
    }
    //返回表数据
    


}
