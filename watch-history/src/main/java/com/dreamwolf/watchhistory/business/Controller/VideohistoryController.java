package com.dreamwolf.watchhistory.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.video.web_interface.VideoMaplist;
import com.dreamwolf.entity.watchhistory.Videohistory;
import com.dreamwolf.entity.watchhistory.web_interface.Cursor;
import com.dreamwolf.entity.watchhistory.web_interface.CursorLisr;
import com.dreamwolf.entity.watchhistory.web_interface.History;
import com.dreamwolf.entity.watchhistory.web_interface.ListCursor;
import com.dreamwolf.watchhistory.business.service.IVideohistoryService;
import com.dreamwolf.watchhistory.business.service.UserService;
import com.dreamwolf.watchhistory.business.service.VideoList;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;
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

    @GetMapping("/cursor2")
    public Map list2(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message","");
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        QueryWrapper<Videohistory> wrapper = new QueryWrapper<>();//历史表 查看最新的ps条数据
        wrapper.orderByDesc("CloseTime").last("limit "+20);//条件
        List<Videohistory> videohistoryList=videohistoryService.list(wrapper);//历史数据库的的信息
        Integer[] array = new Integer[videohistoryList.size()];
        for (int i=0;i<videohistoryList.size();i++) {
            array[i]=videohistoryList.get(i).getBvID();
        }
        List<VideoMaplist> suan=videoList.selectbbid(array).getData();//通过视频id视频返回的内容 相同内容不会返回
        Map<Integer,VideoMaplist> video_disc =  new HashMap<>();
        for (VideoMaplist a : suan){
            video_disc.put(a.getAid(),a);
        }
        List<Map<String,Object>> zhi = new ArrayList<>();
        for (int i = 0;i<videohistoryList.size();i++){
            Map wu=new HashMap<String, Object>();
            VideoMaplist videoObject = video_disc.get(videohistoryList.get(i).getBvID());
            wu.put("title",videoObject.getTitle());
            wu.put("long_title",videoObject.getLong_title());
            wu.put("cover",videoObject.getCover());
            //Map map1=userService.user(videohistoryList.get(i).getBvID());
            wu.put("author_name",userService.userid(videohistoryList.get(i).getuID()).getData().getUserName());
            wu.put("uri",videoObject.getUri());
            wu.put("duration",videoObject.getDuration());
            LocalTime localTime = videohistoryList.get(i).getTimelinePosition();
            int hour =localTime.getHour();
            int min =localTime.getMinute();
            int sec =localTime.getSecond();
            int zong =hour*3600+min*60+sec;
            wu.put("progress",zong==(videoObject.getDuration())?-1:videoObject.getDuration()-zong);
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

    @GetMapping("/cursor")
    public ResponseData<CursorLisr> list(Integer ps,String max,String view_at,String business){
        Integer id=1;
        int code = 0;
        String message="";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime shijian=null;
        if(ps==null){
            ps=20;
        }
        List<Videohistory> videohistoryList=videohistoryService.videohistory(id,ps,max,view_at);//历史数据库的的信息
        if(max.equals("0")){
            QueryWrapper<Videohistory> queryWrapper=new QueryWrapper<>();
            queryWrapper.orderByDesc("CloseTime").last("limit "+1);
            Videohistory videohistory=videohistoryService.getOne(queryWrapper);
            shijian=videohistory.getCloseTime();
        }else {
            shijian = LocalDateTime.parse(max,df);
        }

        Integer[] array = new Integer[videohistoryList.size()];
        for (int i=0;i<videohistoryList.size();i++) {
            array[i]=videohistoryList.get(i).getBvID();
        }
        List<VideoMaplist> suan=videoList.selectbbid(array).getData();//通过视频id视频返回的内容 相同内容不会返回
        Map<Integer,VideoMaplist> video_disc =  new HashMap<>();
        for (VideoMaplist a : suan){
            video_disc.put(a.getAid(),a);
        }
        List<ListCursor> listCursors = new ArrayList<>();
        Cursor cursorLisr=null;
        for (int i = 0;i<videohistoryList.size();i++){
            VideoMaplist videoObject = video_disc.get(videohistoryList.get(i).getBvID());
            History history=new History(videohistoryList.get(i).getOid(),"archive",1);
            LocalTime localTime = videohistoryList.get(i).getTimelinePosition();
            int hour =localTime.getHour();
            int min =localTime.getMinute();
            int sec =localTime.getSecond();
            int zong =hour*3600+min*60+sec;
            ListCursor Cursors=new ListCursor(videoObject.getTitle(),"",
                    videoObject.getCover(),userService.userid(videohistoryList.get(i).getuID()).getData().getUserName(),
                    videoObject.getUri(),history,videoObject.getDuration(),
                    zong==(videoObject.getDuration())?-1:videoObject.getDuration()-zong,"",
                    videohistoryList.get(i).getCloseTime());
            listCursors.add(Cursors);
            cursorLisr=new Cursor(shijian,
                    videohistoryList.get(videohistoryList.size()-1).getCloseTime(), ps,"archive");
        }

        return new ResponseData<CursorLisr>(0,"",1,new CursorLisr(cursorLisr,listCursors));
    }
}

