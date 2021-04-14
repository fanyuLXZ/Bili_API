package com.dreamwolf.dynamic.business.Controller;


import com.dreamwolf.dynamic.business.entity.Dynamiclike;
import com.dreamwolf.dynamic.business.service.DynamiccommentService;
import com.dreamwolf.dynamic.business.service.DynamicdataService;
import com.dreamwolf.dynamic.business.service.DynamiclikeService;
import com.dreamwolf.dynamic.business.service.UserdynamicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class DynamiccommentController {
    @Autowired
    DynamiccommentService dynamiccommentService;
    @Autowired
    DynamicdataService dynamicdataService;
    @Autowired
    DynamiclikeService dynamiclikeService;
    @Autowired
    UserdynamicService userdynamicService;

    @RequestMapping("/entrance")
    public Map entrance(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);//"code":0,"message":"0","ttl":1,
        map.put("message",0);
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> icon=new HashMap<String, Object>();
        /*icon.put("icon",);
        data.put("entrance",)*/
        map.put("data",data);
        return map;
    }
}

