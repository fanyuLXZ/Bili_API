package com.dreamwolf.dynamic.business.Controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
@RequestMapping("/business/dynamiclike")
public class DynamiclikeController {

    @RequestMapping("/info")
    public Map info(){
        Integer id=1;
        Map<String, Object> map=new HashMap<String, Object>();

        return map;
    }
}

