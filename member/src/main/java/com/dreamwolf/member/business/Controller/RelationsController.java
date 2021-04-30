package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.member.business.entity.Relations;
import com.dreamwolf.member.business.entity.User;
import com.dreamwolf.member.business.entity.Userdata;
import com.dreamwolf.member.business.entity.Vip;
import com.dreamwolf.member.business.service.RelationsService;
import com.dreamwolf.member.business.service.UserService;
import com.dreamwolf.member.business.service.UserdataService;
import com.dreamwolf.member.business.service.VipService;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户关系表 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class RelationsController {
    @Autowired
    UserService userService;
    @Autowired
    UserdataService userdataService;
    @Autowired
    VipService vipService;
    @Autowired
    RelationsService relationsService;

    //通过用户id 查看对应id的粉丝id
    @RequestMapping("/relations")
    public List<Map<String, Object>> relations(Integer uid){
        QueryWrapper<Relations> wrapper=new QueryWrapper();
        wrapper.eq("uID",uid);
        List<Map<String, Object>> relations=relationsService.listMaps(wrapper);
        return relations;
    }

    //通过粉丝id查看对应关注的up主id
    @RequestMapping("/intuid")
    public List<Map<String, Object>> intuid(Integer followUID){
        List<Map<String,Object>> udId=new ArrayList<>();
        if (followUID!=null && !followUID.equals("")){
            QueryWrapper<Relations> relationsQueryWrapper=new QueryWrapper<>();
            relationsQueryWrapper.eq("followUID",followUID);
            udId=relationsService.listMaps(relationsQueryWrapper);
        }
        return udId;
    }



}

