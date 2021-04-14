package com.dreamwolf.watchhistory.controller;

import com.dreamwolf.watchhistory.entities.Video;
import com.dreamwolf.watchhistory.entities.VideoHistory;
import com.dreamwolf.watchhistory.entities.Videocomment;
import com.dreamwolf.watchhistory.service.VideoHistoryService;
import com.dreamwolf.watchhistory.service.VideoService;
import com.dreamwolf.watchhistory.service.VideocommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

///**
// * @author: wzx
// * @data: 2021/4/7 10:02
// * @version: 1.0
// */
//用户观看历史表


@RestController
@RequestMapping("/watch-history/videoHistoryController")
public class VideoHistoryController {
    public VideoHistory videoHistory;
    public Video video;
    public Videocomment videocomment;
    @Resource
    VideoHistoryService videoHistoryService;
    VideocommentService videocommentService;
    VideoService videoService;
    @GetMapping("/list")
    public Map videoHistorylist(){
        Integer id=1;
//        videoHistoryService.getCxqb(id).getBvID();
        Map<String,Object> map=new HashMap();
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> listmap=new HashMap();
        listmap.put("title",videoService.getVideobvID(id).getBvTitle());
//        listmap.put("long_title",);//分p标题
//        listmap.put("cover",);//封面图片路径
//        listmap.put("author_name",);//作者名称
//        listmap.put("uri",);//视频链接
//        listmap.put("history",);//历史对象
//                - oid hid
//                - business 类型 string 暂时"archive"
//                - page p数 int 暂时1
//        listmap.put("duration",);//总时长
//        listmap.put("progress",);//观看时长
//        listmap.put("show_title",);//pgc卡用
        listmap.put("view_at",videoHistoryService.getCxqb(id).getTimelinePosition());
        list.add(listmap);
        map.put("list",list);
        return map;
    }
//    @RequestMapping("/info")
//    public Map info(){
//        Integer id=1;
//        Map<String, Object> map=new HashMap<String, Object>();
//        map.put("uname",iUserService.getById(id).getuID());
//        map.put("face",iUserService.getById(id).getHeadImgPath());
//        map.put("email_verified",iUserService.getById(id).getBoundEmail()!=null);
//        map.put("mobile_verified",iUserService.getById(id).getBoundPhone()!=null);
//
//        return map;
//    }

}
