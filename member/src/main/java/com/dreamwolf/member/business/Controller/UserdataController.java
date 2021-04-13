package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.dreamwolf.member.business.entity.Userdata;
import com.dreamwolf.member.business.service.IRelationsService;
import com.dreamwolf.member.business.service.IUserService;
import com.dreamwolf.member.business.service.IUserdataService;
import com.dreamwolf.member.business.service.IVipService;
import com.dreamwolf.member.business.util.Jisuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户个人数据 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
@RequestMapping("/business/userdata")
public class UserdataController {

    @Autowired
    IUserService iUserService;//获取用户名userName,头像路径headImgPath,绑定邮箱boundEmailM,绑定手机号boundPhone
    @Resource
    IUserdataService iUserdataService;
    @Autowired
    IVipService iVipService;
    @Autowired
    IRelationsService iRelationsService;

    @RequestMapping("/info")
    public Map info(){
        Jisuan jisuan=new Jisuan();
        Integer id=1;
        Map<String, Object> map=new HashMap<String, Object>();
        //用户相关
        map.put("uname",iUserService.getById(id).getUserName());//用户名
        map.put("face",iUserService.getById(id).getHeadImgPath());//头像
        map.put("email_verified",iUserService.getById(id).getBoundEmail()!=null);//邮箱
        map.put("mobile_verified",iUserService.getById(id).getBoundPhone()!=null);//电话好吗
        //等级信息相关
        Map<String, Object> level_info=new HashMap<String, Object>();
        level_info.put("current_level",iUserdataService.select(id).getLevel());//当前等级
        level_info.put("current_min",jisuan.residue(iUserdataService.select(id).getLevel()));//最小经验
        level_info.put("current_exp",iUserdataService.select(id).getExp());//当前经验值
        level_info.put("next_exp",jisuan.mincurrent(iUserdataService.select(id).getLevel(),iUserdataService.select(id).getExp().intValue()));//需要的经验值
        map.put("level_info",level_info);
        map.put("money",iUserdataService.select(id).getCoinsNum());//硬币数
        map.put("bcoin_balance",iUserdataService.select(id).getBCoinsNum());//b币数
        //会员相关
        Map<String, Object> vip=new HashMap<String, Object>();
        //判断会员类型 4.1号为小会员
        Calendar cal = Calendar.getInstance();
        int month = (cal.get(Calendar.MONTH)) + 1;
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
        vip.put("vip_type",month+"/"+day_of_month=="4/1"?0:1);//会员类型
        vip.put("vip_status",iVipService.vipselect(id).getuID()==null?0:1);//是否是会员
        vip.put("due_date",iVipService.vipselect(id).getExpirationTime());//会员有效时间
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
        map.put("vip",vip);
        return map;
    }

    //每日奖励
    @RequestMapping("/exp/reward")
    public Map reward(){
        Integer id=1;
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("login",true);//是否登录
        map.put("watch",true);//是否观看视频
        map.put("coins",5);//暂时返回5
        map.put("share",true);//是否分享过视频
        map.put("email_verified",iUserService.getById(id).getBoundEmail()!=null);//是否绑定了邮箱
        map.put("mobile_verified",iUserService.getById(id).getBoundPhone()!=null);//是否绑定了手机号
        return map;
    }

    //大会员信息
    @RequestMapping("/vip/info")
    public Map vipinfo(){
        Integer id=1;//默认id
        Map<String, Object> map=new HashMap<String, Object>();
        //判断会员类型 4.1号为小会员
        Calendar cal = Calendar.getInstance();
        int month = (cal.get(Calendar.MONTH)) + 1;
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);
        map.put("vip_status",iVipService.vipselect(id).getuID()!=null);//是否是会员
        map.put("vip_type",month+"/"+day_of_month=="4/1"?0:1);//会员类型
        map.put("due_date",iVipService.vipselect(id).getExpirationTime());//会员有效时间
        return map;
    }

    //
    @RequestMapping("/account/info")
    public Map account(){
        Integer id=1;//默认id
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("uname",iUserService.getById(id).getNickName());//用户昵称
        map.put("userid","bili_"+iUserService.getById(id).getuID());//用户名
        map.put("birthday",iUserService.getById(id).getBirthday());//出生年月
        map.put("sex",iUserService.getById(id).getSex()==1?"男":"女");//性别
        return map;
    }
}

