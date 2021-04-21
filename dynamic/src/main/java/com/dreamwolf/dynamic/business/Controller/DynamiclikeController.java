package com.dreamwolf.dynamic.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.dynamic.business.entity.Dynamiccomment;
import com.dreamwolf.dynamic.business.entity.Dynamicdata;
import com.dreamwolf.dynamic.business.entity.Dynamiclike;
import com.dreamwolf.dynamic.business.service.DynamiclikeService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 动态点赞表，用于区分用户点赞 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class DynamiclikeController {
    @Resource
    DynamiclikeService dynamiclikeService;

    //通过动态id 查看谁点赞和点赞时间
    @RequestMapping("/dynamiclike")
    public Map info(){
        QueryWrapper<Dynamiclike> wrapper = new QueryWrapper<>();
        wrapper.eq("udID","2");
        Map<String, Object> map=new HashMap<String, Object>();
        List<Dynamiclike> dynamiclike=dynamiclikeService.list(wrapper);
        map.put("dynamiclike",dynamiclike);
        return map;
    }
}

