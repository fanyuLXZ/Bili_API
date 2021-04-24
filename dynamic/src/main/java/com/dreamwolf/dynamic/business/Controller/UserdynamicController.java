package com.dreamwolf.dynamic.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.dynamic.business.entity.Dynamiclike;
import com.dreamwolf.dynamic.business.entity.Userdynamic;
import com.dreamwolf.dynamic.business.service.UserdynamicService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户动态表 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class UserdynamicController {
    @Resource
    UserdynamicService userdynamicService;

    //获取对应用户id的所有动态
    @RequestMapping("/userdynamicList")
    public Map userdynamicList(){
        QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();
        wrapper.eq("uID","1");
        Map<String, Object> map=new HashMap<String, Object>();
        List<Userdynamic> userdynamics=userdynamicService.list(wrapper);
        map.put("userdynamics",userdynamics);
        return map;
    }
    //获取动态id的所有信息
    @RequestMapping("/userdynamic")
    public Map userdynamic(){
        QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();
        wrapper.eq("udID","1");
        Map<String, Object> map=new HashMap<String, Object>();
        List<Userdynamic> userdynamics=userdynamicService.list(wrapper);
        map.put("userdynamics",userdynamics);
        return map;
    }

    @RequestMapping("/dynamic_new")
    public Map dynamic_new(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message","");
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        List cards=new ArrayList();
        Map <String, Object> desc=new HashMap<String, Object>();
        //查询内容对象
        Map <String,Object> user_profile=new HashMap<String, Object>();
        Map <String,Object> info=new HashMap<String, Object>();
        cards.add(desc);
        data.put("cards",cards);
        map.put("data",data);
        return map;
    }
}

