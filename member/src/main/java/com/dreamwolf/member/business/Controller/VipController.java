package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.member.business.entity.Vip;
import com.dreamwolf.member.business.service.RelationsService;
import com.dreamwolf.member.business.service.UserService;
import com.dreamwolf.member.business.service.UserdataService;
import com.dreamwolf.member.business.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户大会员信息 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class VipController {
    @Autowired
    UserService userService;
    @Autowired
    UserdataService userdataService;
    @Autowired
    VipService vipService;
    @Autowired
    RelationsService relationsService;

    //大会员信息
    @RequestMapping("/vip/info")
    public Map vipinfo(){
        Integer id=1;//默认id
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message",0);
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        Vip vip= vipService.vipselect(id);
        //判断会员类型 4.1号为小会员
        Calendar cal = Calendar.getInstance();//时间对象
        int month = (cal.get(Calendar.MONTH)) + 1;//月
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
        data.put("vip_status",vip.getuID()!=null);//是否是会员
        data.put("vip_type",month+"/"+day_of_month=="4/1"?0:1);//会员类型
        data.put("due_date",vip.getExpirationTime());//会员有效时间
        map.put("data",data);
        return map;
    }


    //通过用户id 查询对应大会员信息
    @RequestMapping("/Vip")
    public Map vip(){
        Integer id=1;
        QueryWrapper<Vip> wrapper = new QueryWrapper<>();
        wrapper.eq("uID",id);
        Vip vip=vipService.getOne(wrapper);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("vID",vip.getvID());
        map.put("uID",vip.getuID());//用户id
        map.put("ExpirationTime",vip.getExpirationTime());//大会员过期时间
        map.put("vPoint",vip.getvPoint());//会员积分
        return map;
    }
}

