package com.dreamwolf.video.controller;


import com.dreamwolf.video.pojo.Videodata;
import com.dreamwolf.video.service.VideodataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频数据表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
public class VideodataController {

    @Resource
    private VideodataService videodataService;

    @GetMapping(value = "/videodatabvID")
    public Map selectdvid(Integer bvID){
        Map map = new HashMap();
        if(bvID !=null) {
            map.put("code",0);
            map.put("message","0");
            Videodata videodata = videodataService.selectbvID(bvID); //根据bvID查询的视频基本数据
            map.put("data",videodata);
        }else{
            map.put("code",400);
            map.put("message","传入的参数(bvID)不能为空");
            map.put("data",null);
        }

        return map;
    }

    @GetMapping(value = "/videodatalist")
    public Map selectlistt(){
        Map map = new HashMap();
        if(map !=null) {
            map.put("code",0);
            map.put("message","0");
            List<Videodata> videodata = videodataService.selectlist();//查询视频数据表所有数据
            map.put("data",videodata);
        }else{
            map.put("code",400);
            map.put("message","数据为空");
            map.put("data",null);
        }

        return map;
    }

}

