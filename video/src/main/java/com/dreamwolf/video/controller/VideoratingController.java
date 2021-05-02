package com.dreamwolf.video.controller;


import com.dreamwolf.video.pojo.Videorating;
import com.dreamwolf.video.service.VideoratingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 视频综合评分表，根据该表判断视频排名 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
public class VideoratingController {

    @Resource
    private VideoratingService videoratingService;

    @GetMapping(value = "/videoratbvID")
    public Map selectbvidl(Integer bvID){
        Map map = new HashMap();
        if(bvID !=null) {
            map.put("code", 0);
            map.put("message", "0");
            //根据视频id查询视频评分
            Videorating videorating = videoratingService.selectbvid(bvID);
            map.put("data",videorating);
        }else{
            map.put("code",400);
            map.put("message","传入的参数(bvID)不能为空");
            map.put("data",null);
        }

        return map;
    }

    @GetMapping(value = "/videoratlist")
    public Map selectlists(){
        Map map = new HashMap();
        if(map !=null) {
            map.put("code", 0);
            map.put("message", "0");
            //查询视频评分表所有数据
            List<Videorating> videoratings = videoratingService.selectlist();
            map.put("data",videoratings);
        }else{
            map.put("code",400);
            map.put("message","数据为空");
            map.put("data",null);
        }
        return map;
    }


}

