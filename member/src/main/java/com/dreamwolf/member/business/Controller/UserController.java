package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.member.business.entity.Relations;
import com.dreamwolf.member.business.entity.User;
import com.dreamwolf.member.business.entity.Userdata;
import com.dreamwolf.member.business.entity.Vip;
import com.dreamwolf.member.business.service.*;
import com.dreamwolf.member.business.util.Hide;
import com.dreamwolf.member.business.util.Maps;
import com.dreamwolf.member.business.util.md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

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
    @Resource
    Userdynamic userdynamic;

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
        data.put("userid",user.getUserName());//用户名
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
    public Map bang(Integer id){
        Map<String, Object> map=new HashMap<String, Object>();
        if(id!=null){
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
        }else{
            map.put("code",400);
            map.put("message","id不能为空");
        }
        return map;
    }

    //通过id返回User表所有对应id信息
    @RequestMapping("/useruid")
    public User useruid(Integer uid){
        User user=userService.getById(uid);
        return user;
    }

    //通过id返回User表所有对应id信息
    @RequestMapping("/User")
    public Map User(Integer uid){
        User user=userService.getById(uid);
        Map<String, Object> map=new HashMap<String, Object>();
        if(uid !=null && !uid.equals("")){
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
        }else{
            map.put("code",400);
            map.put("message","值不能为空");
        }
        return map;
    }

    //通过id返回User表所有对应id信息
    @RequestMapping("/User/id")
    public User userid(Integer id){
        User user=userService.getById(id);
        return user;
    }

    //评论回复用户对象 接口调接口
    @GetMapping("/membe")
    public Map membe(Integer uID){
        Map<String, Object> map=new HashMap<String, Object>();
        User user=userService.getById(uID);
        map.put("mid",user.getuID());
        map.put("uname",user.getNickName());
        map.put("sex",user.getSex()==1?"男":"女");
        map.put("face",user.getHeadImgPath());
        Map<String, Object> level_info=new HashMap<String, Object>();
        QueryWrapper<Userdata> wrapper = new QueryWrapper<>();
        wrapper.eq("uID",uID);
        level_info.put("current_level",userdataService.getOne(wrapper).getLevel());
        map.put("level_info",level_info);
        Map<String, Object> vip=new HashMap<String, Object>();
        QueryWrapper<Vip> vipQueryWrapper = new QueryWrapper<>();
        wrapper.eq("uID",uID);
        vip.put("status",vipService.getOne(vipQueryWrapper)!=null);
        map.put("vip",vip);
        return map;
    }

    //用户基本信息
    @GetMapping("/basic-info-by-uid")
    public Map basic(Integer mid){
        Integer id=1;
        Map<String, Object> map=new HashMap<String, Object>();
        if(mid!=null) {
            map.put("code", 0);
            map.put("message","");
            map.put("ttl", 1);
            User user=userService.getById(mid);
            Map<String,Object> data=new HashMap<String, Object>();
            data.put("mid",user.getuID());//id
            data.put("name",user.getNickName());//名称
            data.put("face",user.getHeadImgPath());//头像
            data.put("sex",user.getSex());//性别
            QueryWrapper<Userdata> userdataQueryWrapper=new QueryWrapper<>();
            userdataQueryWrapper.eq("uID",id);
            Userdata userdata=userdataService.getOne(userdataQueryWrapper);
            data.put("fans",userdata.gettFansNum());
            data.put("friend",userdata.gettFollowNum());
            Map<String,Object> level_info=new HashMap<String, Object>();
            level_info.put("current_level",userdata.getLevel());
            data.put("level_info",level_info);
            Map<String,Object> vip=new HashMap<String, Object>();
            Vip vips=vipService.getById(mid);
            if(vips!=null){
                Calendar cal = Calendar.getInstance();
                int month = (cal.get(Calendar.MONTH)) + 1;//月
                int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
                vip.put("type",month+"/"+day_of_month=="4/1"?0:1);//会员类型
                vip.put("status",true);
            }else{
                vip.put("type",null);//会员类型
                vip.put("status",false);
            }
            data.put("vip",vip);
            QueryWrapper<Relations> relationsQueryWrapper=new QueryWrapper<>();
            relationsQueryWrapper.eq("followUID",id).eq("uID",mid);
            Relations relations=relationsService.getOne(relationsQueryWrapper);
            if(relations!=null){
                data.put("following",true);
            }else{
                data.put("following",false);
            }
            map.put("data",data);
        }else{
            map.put("code",400);
            map.put("message","uid值不能为空");
        }
        return map;
    }

    @GetMapping("/user/infos")
    public List<Map<String,Object>> infos(Integer [] uids){
        List<Map<String,Object>> listMap=new ArrayList<>();
        if (uids.length>0){
            QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
            userQueryWrapper.in("uID",uids);
            List<Map<String,Object>> user=userService.listMaps(userQueryWrapper);
            for(int i=0;i<user.size();i++){
                Map<String,Object> map=new HashMap<>();
                map.put("mid",user.get(i).get("uID"));
                map.put("uname",user.get(i).get("userName"));
                map.put("face",user.get(i).get("headImgPath"));
                listMap.add(map);
            }
        }
        return  listMap;
    }

    @GetMapping("card/info")
    public Map<String,Object> cardinfos(){
        Map<String,Object> map=new HashMap<>();
        Integer id=1;
        User user=userService.getById(id);
        Map<String,Object> data=new HashMap<>();
        data.put("mid",user.getuID());
        data.put("name",user.getNickName());
        data.put("face",user.getHeadImgPath());
        QueryWrapper<Relations> integerQueryWrapper=new QueryWrapper<>();
        integerQueryWrapper.eq("uID",id);
        List<Relations> shu=relationsService.list(integerQueryWrapper);
        QueryWrapper<Relations> integerWrapper=new QueryWrapper<>();
        integerWrapper.eq("followUID",id);
        List<Relations> shu2=relationsService.list(integerWrapper);
        data.put("fans",shu.size());
        data.put("friend",shu2.size());
        map.put("data",data);
        return  map;
    }

    @GetMapping("/users")
    public List<Map<String,Object>> users(Integer[] list,Integer uid){//评论着用户id数组  发布动态用户id
        List<Map<String,Object>> listmap=new ArrayList<>();
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();//userdynamic 查找udID为i status=1的用户id
        userQueryWrapper.in("uID",list);
        List<User> dzuser=userService.list(userQueryWrapper);
        for(int i=0;i<list.length;i++){
            Map<String,Object> usermap=new HashMap<>();
            usermap.put("mid",dzuser.get(i).getuID());
            usermap.put("nickname",dzuser.get(i).getNickName());
            usermap.put("avatar",dzuser.get(i).getHeadImgPath());
            QueryWrapper<Relations> relationsWrapper=new QueryWrapper<>();//判断评论用户是否关注了
            relationsWrapper.eq("followUID",dzuser.get(i).getuID()).eq("uID",uid);
            Relations relations=relationsService.getOne(relationsWrapper);
            usermap.put("follow",relations!=null?true:false);
            usermap.put("native_uri","个人中心地址");
            listmap.add(usermap);
        }
        return listmap;
    }

    @GetMapping("/replyuser")
    public List<Map<String,Object>> replyuser(Integer[] uids,Integer id){//回复人  和原评论
        //Map<String,Object> map=new HashMap<>();
        List<Map<String,Object>> listmap=new ArrayList<>();
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.in("uID",uids);
        List<User> userlist=userService.list(userQueryWrapper);//获取全部人员信息 重复不会查询
        Map<Integer,User> userlisttwo=new HashMap<>();
        for(User user:userlist){
            userlisttwo.put(user.getuID(),user);
        }
        for(int i=0;i<uids.length;i++){
            Map<String,Object> map=new HashMap<>();
            User user=userlisttwo.get(uids[i]);
            map.put("mid",user.getuID());
            map.put("nickname",user.getNickName());
            map.put("avatar",user.getHeadImgPath());
            //判断是否关注 判断我是否关注他
            QueryWrapper<Relations> relationsQueryWrapper=new QueryWrapper<>();
            relationsQueryWrapper.eq("uID",user.getuID()).eq("followUID",id);
            List<Relations> relationsList=relationsService.list(relationsQueryWrapper);//查询用户是否关注回复人
            if(relationsList!=null){
                map.put("follow",true);
            }else{
                map.put("follow",false);
            }
            listmap.add(map);
        }
        return listmap;
    }
    @GetMapping("/replyuserb")
    public Map<String,Object> replyuserb(Integer uid,Integer id){//回复人  和原评论
        User userlist=userService.getById(uid);//获取全部人员信息 重复不会查询
        Map<String,Object> map=new HashMap<>();
        map.put("mid",userlist.getuID());
        map.put("nickname",userlist.getNickName());
        map.put("avatar",userlist.getHeadImgPath());
        //判断是否关注 判断我是否关注他
        QueryWrapper<Relations> relationsQueryWrapper=new QueryWrapper<>();
        relationsQueryWrapper.eq("uID",uid).eq("followUID",id);
        Relations relationsList=relationsService.getOne(relationsQueryWrapper);//查询用户是否关注回复人
        if(relationsList!=null){
            map.put("follow",true);
        }else{
            map.put("follow",false);
        }
        return map;
    }
    @GetMapping("/data")
    public List<Map<String,Object>> list(Integer id){
        List<Map<String,Object>> listmap=new ArrayList<>();
        List<Map<String,Object>> replyitem=userdynamic.replyitem(id);
        for(int i=0;i<replyitem.size();i++){
            Map<String,Object> map=new HashMap<>();
            Map<String,Object> item=new HashMap<>();
            item.put("source_content",replyitem.get(i).get("source_content"));
            item.put("type",replyitem.get(i).get("type"));
            item.put("business",replyitem.get(i).get("business"));
            item.put("title",replyitem.get(i).get("title"));
            item.put("reply_time",replyitem.get(i).get("reply_time"));
            item.put("url",replyitem.get(i).get("url"));
            item.put("image",replyitem.get(i).get("image"));
            item.put("native_uri",replyitem.get(i).get("native_uri"));
            //item.put("id",chuan.get("data").get("uid"));
            Map<String,Object> user=replyuserb((Integer) replyitem.get(i).get("id"),id);
            //Map<String,Object> repl=replyuserb((Integer) replyitem.get(i).get("id"),id);
            //user.put("mid",repl.get("mid"));
            map.put("item",item);
            map.put("user",user);
            listmap.add(map);
        }
        return listmap;
    }
}

