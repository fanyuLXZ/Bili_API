package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.User;
import com.dreamwolf.entity.member.Userdata;
import com.dreamwolf.entity.member.Vip;
import com.dreamwolf.entity.member.Label;
import com.dreamwolf.entity.member.Level_info;
import com.dreamwolf.entity.member.Member;
import com.dreamwolf.entity.member.VipStatus;
import com.dreamwolf.member.business.service.RelationsService;
import com.dreamwolf.member.business.service.UserService;
import com.dreamwolf.member.business.service.UserdataService;
import com.dreamwolf.member.business.service.VipService;
import com.dreamwolf.member.business.util.Jisuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

/**
 * <p>
 * 用户个人数据 前端控制器
 * </p>
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class UserdataController {

    @Autowired
    UserService userService;
    @Autowired
    UserdataService userdataService;
    @Autowired
    VipService vipService;
    @Autowired
    RelationsService relationsService;

    //用户信息
    @RequestMapping("/all-info")
    public ResponseData<Member> info(){
        Jisuan jisuan=new Jisuan();
        Integer id=1;
        Userdata userdata= userdataService.select(id);
        Level_info level_info=new Level_info(userdata.getLevel(),jisuan.residue(userdata.getLevel()),userdata.getExp(),jisuan.residue(userdata.getLevel()+1));//jisuan.mincurrent(userdata.getLevel(),userdata.getExp().intValue()) 经验计算
        //会员相关
        Vip ivip= vipService.vipselect(id);
        //判断会员类型 4.1号为小会员
        Calendar cal = Calendar.getInstance();
        int month = (cal.get(Calendar.MONTH)) + 1;//月
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
        VipStatus vipStatus=new VipStatus((month+"/"+day_of_month).equals("4/1")?0:1,ivip!=null, ivip.getExpirationTime(),new Label("","大会员","vip","#FFFFFF","1","#FB7299",""),"http://i0.hdslb.com/bfs/vip/icon_Certification_big_member_22_3x.png");
        User user= userService.getById(id);
        Member member=new Member(true,user.getuID(),user.getUserName(),user.getHeadImgPath(),level_info, vipStatus,userdata.getCoinsNum().intValue(),userdata.getBCoinsNum().intValue(),user.getBoundEmail()!=null,user.getBoundPhone()!=null);
        return new ResponseData<Member>(0,"",1,member);
    }

    //通过用户id 返回Userdata表所有信息 用户等级经验等.通过id返回Userdata表所有对应id信息
    @RequestMapping("/Userdata")
    public ResponseData<Userdata> userdata(Integer uid){
        QueryWrapper<Userdata> wrapper = new QueryWrapper<>();
        wrapper.eq("uID",uid);
        Userdata userdata=userdataService.getOne(wrapper);
        return new ResponseData<Userdata>(0,"",1,userdata);
    }

}

