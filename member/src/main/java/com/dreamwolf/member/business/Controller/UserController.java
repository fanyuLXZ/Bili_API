package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.member.business.entity.User;
import com.dreamwolf.member.business.service.RelationsService;
import com.dreamwolf.member.business.service.UserService;
import com.dreamwolf.member.business.service.UserdataService;
import com.dreamwolf.member.business.service.VipService;
import com.dreamwolf.member.business.util.Hide;
import com.dreamwolf.member.business.util.Maps;
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
    UserService userService;
    @Autowired
    UserdataService userdataService;
    @Autowired
    VipService vipService;
    @Autowired
    RelationsService relationsService;

    //登录
    @RequestMapping("/user/verify")
    public Map verify(String username,String password) throws Exception {
        Map<String, Object> map=new HashMap<String, Object>();
        if(username!=null && !username.equals("") && password!=null && !password.equals("") ){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("boundPhone",username).or().eq("boundEmail",username);
            User user=userService.getOne(wrapper);//查找对应用户名和密码的对象
            if(user!=null){
                md5 md=new md5();//加密
                if(md.message(user.getPassword()).toUpperCase().equals(password.toUpperCase()) && user.getPassword()!=null && !user.getPassword().equals("")){//查看密码是否正确
                    map.put("succeed",true);//是否登录成功
                    map.put("uid",user.getuID());//对应用户id
                }else{
                    map.put("succeed",false);//是否登录成功
                    map.put("message","密码不正确");
                }
            }else{
                map.put("succeed",false);//是否登录成功
                map.put("message","密码账号不正确");
            }
        }else{
            map.put("succeed",false);
            map.put("message","账号或密码为空");
        }
        return map;
    }

    //账号基本信息
    @RequestMapping("/account/info")
    public Map account(){
        Integer id=1;//默认id
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message",0);
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        User user= userService.getById(id);
        data.put("uname",user.getNickName());//用户昵称
        data.put("userid","bili_"+user.getuID());//用户名
        data.put("birthday",user.getBirthday());//出生年月
        data.put("sex",user.getSex()==1?"男":"女");//性别
        map.put("data",data);
        return map;
    }

    //每日奖励
    @RequestMapping("/exp/reward")
    public Map reward(){
        Integer id=1;
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message",0);
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        User user= userService.getById(id);
        data.put("login",true);//是否登录
        data.put("watch",true);//是否观看视频
        data.put("coins",5);//暂时返回5
        data.put("share",true);//是否分享过视频
        data.put("email_verified",user.getBoundEmail()!=null);//是否绑定了邮箱
        data.put("mobile_verified",user.getBoundPhone()!=null);//是否绑定了手机号
        map.put("data",data);
        return map;
    }

    //用户基本信息
    @RequestMapping("/user/info")
    public Map userinfo(){
        Integer id=1;
        User user= userService.getById(id);
        Hide hide=new Hide();//加密工具类
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message",0);
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        data.put("hide_tel",hide.hidePhoneNum(user.getBoundPhone()));
        data.put("hide_mail",hide.hidePhoneNum(user.getBoundEmail()));
        data.put("bind_tel",user.getBoundPhone()!=null);//是否绑定了手机号
        data.put("bind_mail",user.getBoundEmail()!=null);//是否绑定了邮箱
        map.put("data",data);
        return map;
    }

    //接口调接口
    @RequestMapping("/bang")
    public Map bang(@RequestParam("id")Integer id){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);//"code":0,"message":"0","ttl":1,
        map.put("message",0);
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> entrance=new HashMap<String, Object>();
        User user=userService.getById(id);
        entrance.put("icon",user.getHeadImgPath());
        entrance.put("mid",user.getuID());//id
        entrance.put("type","up");
        data.put("entrance",entrance);
        map.put("data",data);
        return map;
    }

    //通过id返回User表所有对应id信息
    @RequestMapping("/User")
    public Map user(Integer uid){
        User user=userService.getById(uid);
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

    //通过id返回User表所有对应id信息
    @RequestMapping("/User/id")
    public User userid(Integer id){
        User user=userService.getById(id);
        return user;
    }
}

