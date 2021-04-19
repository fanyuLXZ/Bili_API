package com.dreamwolf.dynamic.business.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.dynamic.business.entity.Dynamiccomment;
import com.dreamwolf.dynamic.business.entity.Dynamicdata;
import com.dreamwolf.dynamic.business.service.DynamicdataService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 动态数据表 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class DynamicdataController {
    @Resource
    DynamicdataService dynamicdataService;

    //通过动态idID dynamicdata表所有信息 点赞数 转发数 评论数
    @RequestMapping("/dynamicdata")
    public Map dynamicdata(){
        QueryWrapper<Dynamicdata> wrapper = new QueryWrapper<>();
        wrapper.eq("udID","1");
        Map<String, Object> map=new HashMap<String, Object>();
        Dynamicdata dynamicdata=dynamicdataService.getOne(wrapper);
        map.put("udLikeNum",dynamicdata.getUdLikeNum());
        map.put("udRetweetNum",dynamicdata.getUdRetweetNum());
        map.put("udCommentNum",dynamicdata.getUdCommentNum());
        return map;
    }
}

