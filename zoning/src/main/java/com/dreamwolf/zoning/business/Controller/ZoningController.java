package com.dreamwolf.zoning.business.Controller;


import com.dreamwolf.zoning.business.service.ZoningService;
import com.dreamwolf.zoning.business.service.ZoningrelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 分区表 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-16
 */
@RestController
@RequestMapping("/business/zoning")
public class ZoningController {
    @Autowired
    ZoningService zoningService;
    @Autowired
    ZoningrelationService zoningrelationService;

    //全部分区当日投稿数量
    @RequestMapping("/online/all")
    public Map online(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message",0);
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        /*Map<String, Object> region_count=zoningService.mapsele("2021-01-14",1);
        data.put("region_count",region_count);
        map.put("data",data);*/
        return map;
    }
}

