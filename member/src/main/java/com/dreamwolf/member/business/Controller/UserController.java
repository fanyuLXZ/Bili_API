package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.member.business.entity.User;
import com.dreamwolf.member.business.service.IRelationsService;
import com.dreamwolf.member.business.service.IUserService;
import com.dreamwolf.member.business.service.IUserdataService;
import com.dreamwolf.member.business.service.IVipService;
import com.dreamwolf.member.business.util.Hide;
import com.dreamwolf.member.business.util.md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class UserController {
    @Autowired
    IUserService iUserService;
    @Autowired
    IUserdataService iUserdataService;
    @Autowired
    IVipService iVipService;
    @Autowired
    IRelationsService iRelationsService;

    //登录
    @RequestMapping("/user/verify")
    public Map verify(@RequestParam("username")String username, @RequestParam("password")String password) throws Exception {
        //mybatis-plus条件查询 技术有限 没用出来
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName",username).or().eq("boundEmail",username);
        User user=iUserService.getOne(wrapper);//查找对应用户名和密码的对象
        md5 md=new md5();//加密
        Map<String, Object> map=new HashMap<String, Object>();
        if (md.message(user.getPassword()).equals(md.message(password))){//查看密码是否正确
            map.put("succeed",true);//是否登录成功
            map.put("uid",user.getuID());//对应用户id
        }else{
            map.put("succeed",false);//是否登录成功
        }
        return map;
    }

    //账号基本信息
    @RequestMapping("/account/info")
    public Map account(){
        Integer id=1;//默认id
        Map<String, Object> map=new HashMap<String, Object>();
        User user=iUserService.getById(id);
        map.put("uname",user.getNickName());//用户昵称
        map.put("userid","bili_"+user.getUserName());//用户名
        map.put("birthday",user.getBirthday());//出生年月
        map.put("sex",user.getSex()==1?"男":"女");//性别
        return map;
    }

    //每日奖励
    @RequestMapping("/exp/reward")
    public Map reward(){
        Integer id=1;
        Map<String, Object> map=new HashMap<String, Object>();
        User user=iUserService.getById(id);
        map.put("login",true);//是否登录
        map.put("watch",true);//是否观看视频
        map.put("coins",5);//暂时返回5
        map.put("share",true);//是否分享过视频
        map.put("email_verified",user.getBoundEmail()!=null);//是否绑定了邮箱
        map.put("mobile_verified",user.getBoundPhone()!=null);//是否绑定了手机号
        return map;
    }

    //用户基本信息
    @RequestMapping("/user/info")
    public Map userinfo(){
        Integer id=1;
        User user=iUserService.getById(id);
        Hide hide=new Hide();//加密工具类
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        Map<String, Object> account_info=new HashMap<String, Object>();
        account_info.put("hide_tel",hide.hidePhoneNum(user.getBoundPhone()));
        account_info.put("hide_mail",hide.hidePhoneNum(user.getBoundEmail()));
        account_info.put("bind_tel",user.getBoundPhone()!=null);//是否绑定了手机号
        account_info.put("bind_mail",user.getBoundEmail()!=null);//是否绑定了邮箱
        map.put("data",account_info);
        return map;
    }
}

