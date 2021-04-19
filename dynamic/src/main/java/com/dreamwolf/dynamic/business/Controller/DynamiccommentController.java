package com.dreamwolf.dynamic.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.dynamic.business.entity.Dynamiccomment;
import com.dreamwolf.dynamic.business.service.*;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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
    @Resource
    DynamiccommentService dynamiccommentService;
    @Resource
    DynamicdataService dynamicdataService;
    @Resource
    DynamiclikeService dynamiclikeService;
    @Resource
    UserdynamicService userdynamicService;
    @Resource
    MemberService memberService;

    //动态的最新信息
    @RequestMapping("/entrance")
    public Map entrance(@RequestParam("id")Integer id) {
        Map map= memberService.verify(1);
        return map;
    }

    //dynamicComment表所有信息
    @RequestMapping("/dynamicComment")
    public Map dynamicComment(){
        Integer id=1;
        QueryWrapper<Dynamiccomment> wrapper = new QueryWrapper<>();
        wrapper.eq("udID","1");
        List<Dynamiccomment> relations=dynamiccommentService.list(wrapper);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("dynamicComment",relations);
        return map;
    }
}

