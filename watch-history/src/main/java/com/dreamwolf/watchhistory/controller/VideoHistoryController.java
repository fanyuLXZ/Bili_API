package com.dreamwolf.watchhistory.controller;

import com.dreamwolf.watchhistory.entities.VideoHistory;
import com.dreamwolf.watchhistory.service.VideoHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

///**
// * @author: wzx
// * @data: 2021/4/7 10:02
// * @version: 1.0
// */
//用户观看历史表

@Slf4j
@RestController
@RequestMapping("/watch-history/videoHistoryController")
public class VideoHistoryController {
    public VideoHistory videoHistoryEntities;

    @Resource
    VideoHistoryService videoHistoryService;

    @GetMapping("/list")
    public Map videoHistorylist(){
        Map list=new HashMap();
        Integer id=1;
        list.put("title",videoHistoryService.getCxqb(id).getBvID());
        list.put("long_title","");
        return list;
    }


}
