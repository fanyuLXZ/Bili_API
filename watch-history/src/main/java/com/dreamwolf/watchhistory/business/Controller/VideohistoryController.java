package com.dreamwolf.watchhistory.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.watchhistory.Videohistory;
import com.dreamwolf.watchhistory.business.service.IVideohistoryService;
import com.dreamwolf.watchhistory.business.service.UserService;
import com.dreamwolf.watchhistory.business.service.VideoList;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalTime;
import java.util.*;

/**
 * <p>
 * 用户观看历史表 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-23
 */
@RestController
public class VideohistoryController {
    @Resource
    IVideohistoryService videohistoryService;

    @Resource
    VideoList videoList;

    @Resource
    UserService userService;

    @GetMapping("/list")
    public Map list(Integer ps){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message","");
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        QueryWrapper<Videohistory> wrapper = new QueryWrapper<>();//历史表 查看最新的ps条数据
        wrapper.orderByDesc("CloseTime").last("limit "+ps);//条件
        List<Videohistory> videohistoryList=videohistoryService.list(wrapper);//历史数据库的的信息
        Integer[] array = new Integer[videohistoryList.size()];
        for (int i=0;i<videohistoryList.size();i++) {
            array[i]=videohistoryList.get(i).getBvID();
        }
        List<Map<String,Object>> suan=videoList.selectbbid(array);//通过视频id视频返回的内容 相同内容不会返回
        Map<Integer,Map<String,Object>> video_disc =  new HashMap<>();
        for (Map<String,Object> a : suan){
            video_disc.put((Integer) a.get("bvid"),a);
        }
        List<Map<String,Object>> zhi = new ArrayList<>();
        for (int i = 0;i<videohistoryList.size();i++){
            Map wu=new HashMap<String, Object>();
            Map<String,Object> videoObject = video_disc.get(videohistoryList.get(i).getBvID());
            wu.put("title",videoObject.get("title"));
            wu.put("long_title",videoObject.get("long_title"));
            wu.put("cover",videoObject.get("cover"));
            //Map map1=userService.user(videohistoryList.get(i).getBvID());
            wu.put("author_name",userService.user(videohistoryList.get(i).getuID()).get("userName"));
            wu.put("uri",videoObject.get("uri"));
            wu.put("duration",videoObject.get("duration"));
            LocalTime localTime = videohistoryList.get(i).getTimelinePosition();
            int hour =localTime.getHour();
            int min =localTime.getMinute();
            int sec =localTime.getSecond();
            int zong =hour*3600+min*60+sec;
            wu.put("progress",zong==(int)(videoObject.get("duration"))?-1:Integer.parseInt(videoObject.get("duration").toString())-zong);
            wu.put("show_title","");
            wu.put("view_at",videohistoryList.get(i).getCloseTime());
            Map<String, Object> history=new HashMap<String, Object>();
            history.put("oid",videohistoryList.get(i).getOid());
            history.put("business","archive");
            history.put("page",1);
            zhi.add(wu);
        }
        data.put("list",zhi);
        map.put("data",data);
        return map;
    }
}

