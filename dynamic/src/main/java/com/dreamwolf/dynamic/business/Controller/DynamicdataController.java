package com.dreamwolf.dynamic.business.Controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.dynamic.Dynamicdata;
import com.dreamwolf.dynamic.business.service.DynamicdataService;
import org.springframework.web.bind.annotation.PathVariable;
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
    @SentinelResource(value = "dynamicdata",fallback="handlerDynamicdata")
    @RequestMapping("/dynamicdata")
    public ResponseData<Dynamicdata> dynamicdata(Integer id){
        QueryWrapper<Dynamicdata> wrapper = new QueryWrapper<>();
        wrapper.eq("udID",id);
        Dynamicdata dynamicdata=dynamicdataService.getOne(wrapper);
        return new ResponseData<Dynamicdata>(0,"",1,dynamicdata);
    }
    public Map handlerDynamicdata(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }
}

