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

import java.util.HashMap;
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

    @RequestMapping("/relations")
    public Map relations(){
        Integer id=1;
        Relations relations=relationsService.getById(1);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("uID",relations.getuID());
        map.put("followUID",relations.getFollowUID());
        return map;
    }

    @RequestMapping("/User")
    public Map user(){
        Integer id=1;
        User user=userService.getById(1);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("uID",user.getuID());
        map.put("userName",user.getUserName());
        map.put("password",user.getPassword());
        map.put("nickName",user.getNickName());
        map.put("sex",user.getSex());
        map.put("birthday",user.getBirthday());
        map.put("boundEmail",user.getBoundEmail());
        map.put("boundPhone",user.getBoundEmail());
        map.put("boundQQ",user.getBoundQQ());
        map.put("headImgPath",user.getHeadImgPath());
        return map;
    }

    @RequestMapping("/Userdata")
    public Map userdata(){
        Integer id=1;
        QueryWrapper<Userdata> wrapper = new QueryWrapper<>();
        wrapper.eq("uID",id);
        Userdata userdata=userdataService.getOne(wrapper);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("uID",userdata.getuID());
        map.put("Level",userdata.getLevel());
        map.put("Exp",userdata.getExp());
        map.put("CoinsNum",userdata.getCoinsNum());
        map.put("BCoinsNum",userdata.getBCoinsNum());
        map.put("tFollowNum",userdata.gettFollowNum());
        map.put("tFansNum",userdata.gettFansNum());
        map.put("tLikeNum",userdata.gettLikeNum());
        map.put("tPlaysNum",userdata.gettPlaysNum());
        map.put("tReadNum",userdata.gettReadNum());
        map.put("uDescription",userdata.getuDescription());
        return map;
    }

    @RequestMapping("/Vip")
    public Map vip(){
        Integer id=1;
        QueryWrapper<Vip> wrapper = new QueryWrapper<>();
        wrapper.eq("uID",id);
        Vip vip=vipService.getOne(wrapper);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("vID",vip.getvID());
        map.put("uID",vip.getuID());
        map.put("ExpirationTime",vip.getExpirationTime());
        map.put("vPoint",vip.getvPoint());
        return map;
    }
}

