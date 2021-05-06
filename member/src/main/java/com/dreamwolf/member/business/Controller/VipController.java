package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.Vip;
import com.dreamwolf.entity.member.web_interface.VipInfo;
import com.dreamwolf.entity.member.VipPoint;
import com.dreamwolf.member.business.service.RelationsService;
import com.dreamwolf.member.business.service.UserService;
import com.dreamwolf.member.business.service.UserdataService;
import com.dreamwolf.member.business.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

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
    @GetMapping("/vip/info")
    public ResponseData<VipInfo> reward(){
        Integer id=1;//默认id
        Vip vip= vipService.vipselect(id);
        Calendar cal = Calendar.getInstance();//时间对象
        int month = (cal.get(Calendar.MONTH)) + 1;//月
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
        VipInfo vipInfo=new VipInfo(vip.getuID()!=null,(month+"/"+day_of_month).equals("4/1")?0:1,vip.getExpirationTime());
        return new ResponseData<VipInfo>(0,"",1,vipInfo);
    }

    //通过用户id 查询对应大会员信息
    @RequestMapping("/Vip")
    public ResponseData<Vip> vip(Integer uID){
        int code = 0;
        String message="";
        Vip vip=null;
        if(uID!=null){
            vip=vipService.getById(uID);
        }else{
            code = 1;
            message="uID不能为空";
        }
        return new ResponseData<Vip>(code,message,1,vip);
    }

    //大会员积分
    @RequestMapping("/vip/point")
    public ResponseData<VipPoint> vippoint(){
        Integer id=1;
        QueryWrapper<Vip> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("uID",id);
        Vip vip=vipService.getOne(queryWrapper);
        VipPoint vipPoint=new VipPoint(vip.getuID(),vip.getvPoint());
        return new ResponseData<VipPoint>(0,"",1,vipPoint);
    }

}

