package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.*;
import com.dreamwolf.entity.member.web_interface.*;
import com.dreamwolf.entity.message.web_interface.MMItems;
import com.dreamwolf.entity.message.web_interface.MMres;
import com.dreamwolf.member.business.service.*;
import com.dreamwolf.member.business.util.Hide;
import com.dreamwolf.member.business.util.md5;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    @Resource
    UserService userService;
    @Resource
    UserdataService userdataService;
    @Resource
    VipService vipService;
    @Resource
    RelationsService relationsService;
    @Resource
    Userdynamic userdynamic;

    //登录
    @RequestMapping("/user/verify")
    public ResponseData<UserVerify> verify(String username,String password)throws Exception{
        int code = 0;
        String message="";
        UserVerify userVerify=null;
        if(username!=null && !username.equals("") && password!=null && !password.equals("") ){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("boundPhone",username).or().eq("boundEmail",username);
            User user=userService.getOne(wrapper);//查找对应用户名和密码的对象
            if(user!=null){
                md5 md=new md5();//加密
                if(md.message(user.getPassword()).toUpperCase().equals(password.toUpperCase()) && user.getPassword()!=null && !user.getPassword().equals("")){//查看密码是否正确
                    userVerify=new UserVerify(true,user.getuID(),"登录成功");
                }else{
                    code = 1;
                    message="密码或者账号异常";
                }
            }else{
                userVerify=new UserVerify(false,null,"登录失败");
            }
        }else{
            code = 1;
            message="密码或者账号不能为空";
        }
        return new ResponseData<UserVerify>(code,message,1,userVerify);
    }

    //账号基本信息
    @RequestMapping("/account/info")
    public ResponseData<AccountInfo> account(){
        Integer id=1;//默认id
        User user= userService.getById(id);
        AccountInfo accountInfo = new AccountInfo(user.getNickName(),user.getUserName(),user.getBirthday(),user.getSex()==1?"男":"女");
        return new ResponseData<AccountInfo>(0,"",1,accountInfo);
    }

    //账号基本信息
    @RequestMapping("/exp/reward")
    public ResponseData<ExpReward> reward(){
        Integer id=1;//默认id
        User user= userService.getById(id);
        ExpReward expReward = new ExpReward(true,true,5,true,user.getBoundEmail()!=null,user.getBoundPhone()!=null);
        return new ResponseData<ExpReward>(0,"",1,expReward);
    }

    //用户基本信息
    @RequestMapping("/user/info")
    public ResponseData<UserInfo> userinfo(){
        Integer id=1;//默认id
        User user= userService.getById(id);
        Hide hide=new Hide();//加密工具类
        UserInfo userInfo = new UserInfo(hide.hidePhoneNum(user.getBoundPhone())
                ,hide.hidePhoneNum(user.getBoundEmail()),
                user.getBoundPhone()!=null,user.getBoundEmail()!=null);
        return new ResponseData<UserInfo>(0,"",1,userInfo);
    }

    //接口调接口 动态的最新信息entrance
    @RequestMapping("/bang")
    public ResponseData<Bang> bang(Integer id){
        int code = 0;
        String message="";
        Bang bang=null;
        if(id!=null){
            User user=userService.getById(id);
            bang = new Bang(user.getHeadImgPath(),user.getuID(),"up");
        }else {
            code =1;
            message="id不能为空";
        }
        return new ResponseData<Bang>(code,message,1,bang);
    }

    //通过id返回User表所有对应id信息
    @GetMapping("/useruid")
    public ResponseData<User> userid(Integer uid){
        int code = 0;
        String message="";
        User user=null;
        if(uid!=null){
            user=userService.getById(uid);
        }else{
            code=1;
            message="uid不能为空";
        }
        return new ResponseData<User>(code,message,1,user);
    }


    @GetMapping("/membe")
    public ResponseData<Member> membe(Integer uID){
        User user=userService.getById(uID);
        QueryWrapper<Userdata> wrapper = new QueryWrapper<>();
        wrapper.eq("uID",uID);
        Level_info level_info=new Level_info(userdataService.getOne(wrapper).getLevel());
        QueryWrapper<Vip> vipQueryWrapper = new QueryWrapper<>();
        wrapper.eq("uID",uID);
        VipStatus vipStatus=new VipStatus(vipService.getOne(vipQueryWrapper)!=null);
        Member member=new Member(user.getuID(),user.getNickName(),user.getSex()==1?"男":"女",user.getHeadImgPath(),level_info,vipStatus);
        return new ResponseData<Member>(0,"",1,member);
    }



    @GetMapping("/basic-info-by-uid")
    public ResponseData<Member> basic(Integer mid){
        User user=userService.getById(mid);
        QueryWrapper<Userdata> userdataQueryWrapper=new QueryWrapper<>();
        userdataQueryWrapper.eq("uID",mid);
        Userdata userdata=userdataService.getOne(userdataQueryWrapper);
        Level_info level_info=new Level_info(userdata.getLevel());
        Vip vips=vipService.getById(mid);
        Calendar cal = Calendar.getInstance();
        int month = (cal.get(Calendar.MONTH)) + 1;//月
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
        VipStatus vipStatus=new VipStatus(vips!=null,(month+"/"+day_of_month).equals("4/1")?0:1);
        QueryWrapper<Relations> relationsQueryWrapper=new QueryWrapper<>();
        relationsQueryWrapper.eq("followUID",mid).eq("uID",mid);
        Relations relations=relationsService.getOne(relationsQueryWrapper);
        Member member=new Member(user.getuID(),user.getNickName(),user.getSex()==1?"男":"女",user.getHeadImgPath(),
                userdata.gettFansNum(),userdata.gettFollowNum(),
                level_info,vipStatus,relations!=null);
        return new ResponseData<Member>(0,"",1,member);
    }

    @GetMapping("/user/infos")
    public ResponseData<List<Member>> infos(Integer [] uids){
        List<Member> list=new ArrayList();
        if (uids.length>0){
            QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
            userQueryWrapper.in("uID",uids);
            List<User> user=userService.list(userQueryWrapper);
            for(int i=0;i<user.size();i++){
                Member member=new Member(user.get(i).getuID(),user.get(i).getUserName(),user.get(i).getHeadImgPath());
                list.add(member);
            }
        }
        return new ResponseData<List<Member>>(0,"",1,list);
    }

    //当前登陆的用户基本信息
    @GetMapping("/card/info")
    public ResponseData<Member> cardinfos(){
        Integer id=1;
        User user=userService.getById(id);
        QueryWrapper<Relations> integerQueryWrapper=new QueryWrapper<>();
        integerQueryWrapper.eq("uID",id);//查找uID为id的 关注up的
        QueryWrapper<Relations> integerWrapper=new QueryWrapper<>();
        integerWrapper.eq("followUID",id);//查找followUID为id的 up关注的
        Member member=new Member(user.getuID(),user.getNickName(),user.getHeadImgPath(),
                (long)relationsService.list(integerQueryWrapper).size(),(long)relationsService.list(integerWrapper).size());
        return new ResponseData<Member>(0,"",1,member);
    }

    //用户对象数组
    @GetMapping("/users")
    public ResponseData<List<Users>> users(Integer[] list,Integer uid){//评论着用户id数组  发布动态用户id
        List<Users> usersList=new ArrayList<>();
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();//userdynamic 查找udID为i status=1的用户id
        userQueryWrapper.in("uID",list);
        List<User> dzuser=userService.list(userQueryWrapper);
        for(int i=0;i<list.length;i++){
            QueryWrapper<Relations> relationsWrapper=new QueryWrapper<>();//判断评论用户是否关注了
            relationsWrapper.eq("followUID",dzuser.get(i).getuID()).eq("uID",uid);
            Relations relations=relationsService.getOne(relationsWrapper);
            Users users=new Users(dzuser.get(i).getuID(),dzuser.get(i).getNickName(),
                    dzuser.get(i).getHeadImgPath(),relations!=null,"个人中心地址");
            usersList.add(users);
        }
        return new ResponseData<List<Users>>(0,"",1,usersList);
    }

    //回复我的用户对象List 接口调接口
    @GetMapping("/replyuser")
    public ResponseData<List<ReplyUser>> replyuser(Integer[] uids,Integer id){//评论着用户id数组  发布动态用户id
        List<ReplyUser> replyUserList=new ArrayList<>();
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.in("uID",uids);
        List<User> userlist=userService.list(userQueryWrapper);//获取全部人员信息 重复不会查询
        Map<Integer,User> userlisttwo=new HashMap<>();
        for(User user:userlist){
            userlisttwo.put(user.getuID(),user);
        }
        for(int i=0;i<uids.length;i++){
            User user=userlisttwo.get(uids[i]);
            //判断是否关注 判断我是否关注他
            QueryWrapper<Relations> relationsQueryWrapper=new QueryWrapper<>();
            relationsQueryWrapper.eq("uID",user.getuID()).eq("followUID",id);
            Relations relations=relationsService.getOne(relationsQueryWrapper);//查询用户是否关注回复人
            ReplyUser replyUser=new ReplyUser(user.getuID(),user.getNickName(),user.getHeadImgPath(),relations!=null);
            replyUserList.add(replyUser);
        }
        return new ResponseData<List<ReplyUser>>(0,"",1,replyUserList);
    }

    //回复我的用户对象 接口调接口
    @GetMapping("/replyuserb")
    public ResponseData<ReplyUser> replyuserb(Integer uid,Integer id){//回复人  和原评论
        User userlist=userService.getById(uid);//查询对应uid的用户信息
        //判断是否关注 判断我是否关注他
        QueryWrapper<Relations> relationsQueryWrapper=new QueryWrapper<>();
        relationsQueryWrapper.eq("uID",uid).eq("followUID",id);
        Relations relations=relationsService.getOne(relationsQueryWrapper);//查询用户是否关注回复人
        ReplyUser replyUser=new ReplyUser(userlist.getuID(),userlist.getNickName(),userlist.getHeadImgPath(),relations!=null);
        return new ResponseData<ReplyUser>(0,"",1,replyUser);
    }

    //接口调接口
    @GetMapping("/data")
    public ResponseData<List<MMres>> list(Integer id){
        List<MMres> reply=new ArrayList<>();
        List<MMItems> replyitem=userdynamic.replyitem(id).getData();
        for(int i=0;i<replyitem.size();i++){
            MMItems mmItems=new MMItems(replyitem.get(i).getSource_content(),replyitem.get(i).getType(),
                    replyitem.get(i).getBusiness(),replyitem.get(i).getTitle(),replyitem.get(i).getReply_time(),
                    replyitem.get(i).getUri(),replyitem.get(i).getBvid(),replyitem.get(i).getImage(),
                    replyitem.get(i).getNative_uri());
            ReplyUser repl=replyuserb(replyitem.get(i).getBvid(),id).getData();
            MMres mm = new MMres(repl,mmItems);
            reply.add(mm);
        }
        return new ResponseData(0,"",1,reply);
    }

    //根据用户id查询对象 /video/info
    @GetMapping("/video/info")
    public ResponseData<VideoinfoOwnerInfo> video_info(Integer uid,Integer mid){//当前用户id  用户id
        User user=userService.getById(mid);
        QueryWrapper<Userdata> userdataQueryWrapper=new QueryWrapper<>();
        userdataQueryWrapper.eq("uID",mid);
        Userdata userdata=userdataService.getOne(userdataQueryWrapper);
        QueryWrapper<Relations> relationsQueryWrapper=new QueryWrapper<>();
        relationsQueryWrapper.eq("followUID",uid).eq("uID",mid);
        Relations relations=relationsService.getOne(relationsQueryWrapper);
        VideoinfoOwnerInfo videoinfoOwnerInfo=new VideoinfoOwnerInfo(user,userdata,relations!=null);
        return new ResponseData<VideoinfoOwnerInfo>(0,"",1,videoinfoOwnerInfo);
    }

    @GetMapping("/ownerinfo")
    public ResponseData<OwnerInfo> OwnerInfo(Integer uID){
        User user=userService.getById(uID);
        return new ResponseData<OwnerInfo>(0,"",1,new OwnerInfo(user));
    }
}

