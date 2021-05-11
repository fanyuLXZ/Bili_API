package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.Vip;
import com.dreamwolf.entity.member.web_interface.VipInfo;
import com.dreamwolf.entity.member.VipPoint;
import com.dreamwolf.member.business.service.*;
import com.dreamwolf.safety.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    @Resource
    SafetyService safetyService;

    //大会员信息
    @GetMapping("/vip/info")
    public ResponseData<VipInfo> reward(HttpServletRequest request){
        ResponseData<Integer> logon_uid_result = safetyService.logon_uid(TokenUtil.getToken(request));
        if (logon_uid_result.getCode()==0) {
            Integer id = logon_uid_result.getData();
            Vip vip = vipService.vipselect(id);
            Calendar cal = Calendar.getInstance();//时间对象
            int month = (cal.get(Calendar.MONTH)) + 1;//月
            int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
            VipInfo vipInfo = new VipInfo(vip.getuID() != null, (month + "/" + day_of_month).equals("4/1") ? 0 : 1, vip.getExpirationTime());
            return new ResponseData<VipInfo>(0, "", 1, vipInfo);
        }else {
            return new ResponseData<VipInfo>(logon_uid_result.getCode(), logon_uid_result.getMessage(), 1, null);
        }
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
    public ResponseData<VipPoint> vippoint(HttpServletRequest request){
        ResponseData<Integer> logon_uid_result = safetyService.logon_uid(TokenUtil.getToken(request));
        if (logon_uid_result.getCode()==0) {
            Integer id = logon_uid_result.getData();
            QueryWrapper<Vip> queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("uID",id);
            Vip vip=vipService.getOne(queryWrapper);
            VipPoint vipPoint=new VipPoint(vip.getuID(),vip.getvPoint());
            return new ResponseData<VipPoint>(0,"",1,vipPoint);
        }else {
            return new ResponseData<VipPoint>(logon_uid_result.getCode(), logon_uid_result.getMessage(), 1, null);
        }
    }

}

