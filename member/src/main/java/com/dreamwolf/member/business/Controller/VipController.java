package com.dreamwolf.member.business.Controller;


import com.dreamwolf.member.business.entity.Vip;
import com.dreamwolf.member.business.service.IRelationsService;
import com.dreamwolf.member.business.service.IUserService;
import com.dreamwolf.member.business.service.IUserdataService;
import com.dreamwolf.member.business.service.IVipService;
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
    IUserService iUserService;
    @Autowired
    IUserdataService iUserdataService;
    @Autowired
    IVipService iVipService;
    @Autowired
    IRelationsService iRelationsService;

    //大会员信息
    @RequestMapping("/vip/info")
    public Map vipinfo(){
        Integer id=1;//默认id
        Map<String, Object> map=new HashMap<String, Object>();
        Vip vip=iVipService.vipselect(id);
        //判断会员类型 4.1号为小会员
        Calendar cal = Calendar.getInstance();//时间对象
        int month = (cal.get(Calendar.MONTH)) + 1;//月
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
        map.put("vip_status",vip.getuID()!=null);//是否是会员
        map.put("vip_type",month+"/"+day_of_month=="4/1"?0:1);//会员类型
        map.put("due_date",vip.getExpirationTime());//会员有效时间
        return map;
    }
}

