package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.member.business.entity.User;
import com.dreamwolf.member.business.entity.Userdata;
import com.dreamwolf.member.business.entity.Vip;
import com.dreamwolf.member.business.service.RelationsService;
import com.dreamwolf.member.business.service.UserService;
import com.dreamwolf.member.business.service.UserdataService;
import com.dreamwolf.member.business.service.VipService;
import com.dreamwolf.member.business.util.Jisuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
    public Map info(){
        Jisuan jisuan=new Jisuan();
        Integer id=1;
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message",0);
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        User user= userService.getById(id);
        Userdata userdata= userdataService.select(id);
        Vip ivip= vipService.vipselect(id);
        //用户相关
        data.put("isLogin",true);//是否登录
        data.put("mid",user.getuID());//id
        data.put("uname",user.getUserName());//用户名
        data.put("face",user.getHeadImgPath());//头像
        data.put("email_verified",user.getBoundEmail()!=null);//邮箱
        data.put("mobile_verified",user.getBoundPhone()!=null);//电话好吗
        //等级信息相关
        Map<String, Object> level_info=new HashMap<String, Object>();
        level_info.put("current_level",userdata.getLevel());//当前等级
        level_info.put("current_min",jisuan.residue(userdata.getLevel()));//最小经验
        level_info.put("current_exp",userdata.getExp());//当前经验值
        level_info.put("next_exp",jisuan.mincurrent(userdata.getLevel(),userdata.getExp().intValue()));//需要的经验值
        data.put("level_info",level_info);
        data.put("money",userdata.getCoinsNum());//硬币数
        data.put("bcoin_balance",userdata.getBCoinsNum());//b币数
        //会员相关
        Map<String, Object> vip=new HashMap<String, Object>();
        //判断会员类型 4.1号为小会员
        Calendar cal = Calendar.getInstance();
        int month = (cal.get(Calendar.MONTH)) + 1;
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
        vip.put("vip_type",month+"/"+day_of_month=="4/1"?0:1);//会员类型
        vip.put("vip_status",ivip.getuID()==null?0:1);//是否是会员
        vip.put("due_date",ivip.getExpirationTime());//会员有效时间
        Map<String, Object> label=new HashMap<String, Object>();
        label.put("path","");
        label.put("text","大会员");
        label.put("label_theme","vip");
        label.put("text_color","#FFFFFF");
        label.put("bg_style","1");
        label.put("bg_color","#FB7299");
        label.put("border_color","");
        vip.put("label",label);
        vip.put("avatar_subscript_url","http://i0.hdslb.com/bfs/vip/icon_Certification_big_member_22_3x.png");
        data.put("vip",vip);
        map.put("data",data);
        return map;
    }

    //通过用户id 返回Userdata表所有信息 用户等级经验等.
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





}
