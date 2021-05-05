package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.Relations;
import com.dreamwolf.member.business.service.RelationsService;
import com.dreamwolf.member.business.service.UserService;
import com.dreamwolf.member.business.service.UserdataService;
import com.dreamwolf.member.business.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseData<List<Relations>> relations(Integer uid){
        QueryWrapper<Relations> wrapper=new QueryWrapper();
        wrapper.eq("uID",uid);
        List<Relations> relations=relationsService.list(wrapper);
        return new ResponseData<List<Relations>>(0,"",1,relations);
    }

    //通过粉丝id查看对应关注的up主id
    @RequestMapping("/intuid")
    public ResponseData<List<Relations>> intuid(Integer followUID){
        List<Relations> relations=new ArrayList<>();
        if (followUID!=null && !followUID.equals("")){
            QueryWrapper<Relations> relationsQueryWrapper=new QueryWrapper<>();
            relationsQueryWrapper.eq("followUID",followUID);
            relations=relationsService.list(relationsQueryWrapper);
        }
        return new ResponseData<List<Relations>>(0,"",1,relations);
    }
}

