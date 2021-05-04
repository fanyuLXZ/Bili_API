package com.dreamwolf.dynamic.business.Controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.dynamic.Dynamiccomment;
import com.dreamwolf.entity.dynamic.Dynamicdata;
import com.dreamwolf.entity.dynamic.Dynamiclike;
import com.dreamwolf.entity.dynamic.Userdynamic;
import com.dreamwolf.dynamic.business.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 用户动态表 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class UserdynamicController {
    @Resource
    DynamiclikeService dynamiclikeService;
    @Resource
    UserdynamicService userdynamicService;
    @Resource
    DynamicdataService dynamicdataService;
    @Resource
    MemberService memberService;
    @Resource
    CommentService commentService;
    @Resource
    DynamiccommentService dynamiccommentService;

    //获取对应用户id的所有动态
    @SentinelResource(value = "userdynamicList",fallback="handlerUserdynamicList")
    @RequestMapping("/userdynamicList")
    public Map userdynamicList(Integer uid){
        QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();
        wrapper.eq("uID",uid);
        Map<String, Object> map=new HashMap<String, Object>();
        List<Userdynamic> userdynamics=userdynamicService.list(wrapper);
        map.put("userdynamics",userdynamics);
        return map;
    }
    public Map handlerUserdynamicList(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //获取动态id的所有信息
    @SentinelResource(value = "userdynamic",fallback="handlerUserdynamic")
    @RequestMapping("/userdynamic")
    public Map userdynamic(Integer udID){
        QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();
        wrapper.eq("udID",udID);
        Map<String, Object> map=new HashMap<String, Object>();
        List<Userdynamic> userdynamics=userdynamicService.list(wrapper);
        map.put("userdynamics",userdynamics);
        return map;
    }
    public Map handlerUserdynamic(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //最新的20条动态
    @RequestMapping("/dynamic_new")
    @SentinelResource(value = "dynamic_new",fallback="handlerDynamic_new")
    public Map dynamic_new(){
        Integer id=1;
        List<Map<String,Object>> listMap=memberService.intuid(id);
        List<String> resultList=new ArrayList<>();
        for (int i=0; i<listMap.size();i++){
            resultList.add(listMap.get(i).get("uID").toString());
        }
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message","");
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        List<Map<String,Object>> cards=new ArrayList();//大List
        QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();//条件构造器
        wrapper.in("uID",resultList).orderByDesc("updateTime").last("limit "+20);
        List<Map<String,Object>> userdynamic=userdynamicService.listMaps(wrapper);//返回关注up的最新的20条动态 userdynamic表
        /*List<String> dynamicList=new ArrayList<>();//放userdynamic表数据中的udID
        for(int i=0;i<userdynamic.size();i++){
            dynamicList.add(userdynamic.get(i).get("udID").toString());
        }
        QueryWrapper<Dynamicdata> dynamicwrapper = new QueryWrapper<>();//条件构造器
        wrapper.in("udID",dynamicList);
        List<Map<String,Object>> dynamic=dynamicdataService.listMaps(dynamicwrapper);//返回所有的dynamicdata表*/
        for (int i=0;i<userdynamic.size();i++){
            Map <String, Object> listmap=new HashMap<String, Object>();//内部层
            Map<String, Object> desc=new HashMap<String, Object>();
            desc.put("uid",userdynamic.get(i).get("uID"));
            desc.put("type",2);
            QueryWrapper<Dynamicdata> dynamicwrapper = new QueryWrapper<>();//条件构造器
            dynamicwrapper.eq("udID",userdynamic.get(i).get("udID").toString());
            Map<String,Object> mapdynamic=dynamicdataService.getMap(dynamicwrapper);
            if(mapdynamic!=null){
                desc.put("comment",mapdynamic.get("udCommentNum"));
                desc.put("is_like",mapdynamic.get("udLikeNum"));
            }else{
                desc.put("comment",0);
                desc.put("is_like",0);
            }
            QueryWrapper<Dynamiclike> dynamiclikewrapper = new QueryWrapper<>();//条件构造器
            dynamiclikewrapper.eq("udID",userdynamic.get(i).get("udID").toString()).eq("uID",id);
            Map<String,Object> dynamiclike=dynamiclikeService.getMap(dynamiclikewrapper);
            if (dynamiclike!=null) {
                desc.put("like", dynamiclike.get("status"));
            }else{
                desc.put("like", 0);
            }
            desc.put("timestamp",userdynamic.get(i).get("updateTime"));
            desc.put("dynamic_id",userdynamic.get(i).get("udID"));
            Map<String,Object> user_profile=new  HashMap<String, Object>();
            Map<String,Object> info=new HashMap<>();
            Map<String,Object> inin=memberService.user((Integer)userdynamic.get(i).get("uID"));
            info.put("uid",inin.get("uID"));
            info.put("uname",inin.get("userName"));
            info.put("face",inin.get("headImgPath"));
            user_profile.put("info",info);
            Map<String,Object> vip=new HashMap<>();
            Map<String,Object> vipin=memberService.vip((Integer)userdynamic.get(i).get("uID"));
            if(vipin!=null){
                vip.put("status",true);
                Calendar cal = Calendar.getInstance();//时间对象
                int month = (cal.get(Calendar.MONTH)) + 1;//月
                int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
                vip.put("type",month+"/"+day_of_month=="4/1"?0:1);//会员类型
                vip.put("due_date",vipin.get("ExpirationTime"));
            }else{
                vip.put("status",false);
                vip.put("type","无");//会员类型
                vip.put("due_date",0);
            }
            user_profile.put("vip",vip);
            Map<String,Object> level_info=new HashMap<>();
            Map<String,Object> userdata=memberService.userdata((Integer)userdynamic.get(i).get("uID"));
            level_info.put("current_info",userdata.get("Level"));
            user_profile.put("level_info",level_info);
            desc.put("user_profile",user_profile);
            listmap.put("card",userdynamic.get(i).get("content"));
            listmap.put("desc",desc);
            cards.add(listmap);
        }
        //data.put("userdynamic",userdynamic);
        data.put("cards",cards);
        map.put("data",data);
        return map;
    }
    public Map handlerDynamic_new(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //根据偏移动态id获取后面20条动态信息 /dynamic_history
    @SentinelResource(value = "dynamic_history",fallback="handlerDynamic_history")
    @GetMapping("dynamic_history")
    public Map dynamic_history(Integer offset_dynamic_id){
        Integer id=1;
        List<Map<String,Object>> listMap=memberService.intuid(id);
        List<Integer> resultList=new ArrayList<>();
        for (int i=0; i<listMap.size();i++){
            resultList.add((Integer)listMap.get(i).get("uID"));
        }
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message","");
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        List<Map<String,Object>> cards=new ArrayList();//大List
        /*QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();//条件构造器
        wrapper.in("uID",resultList).orderByDesc("updateTime").last("limit "+20);*/
        List<Map<String,Object>> userdynamic=userdynamicService.listmap(offset_dynamic_id,resultList.toArray(new Integer[0]));
        for (int i=0;i<userdynamic.size();i++){
            Map <String, Object> listmap=new HashMap<String, Object>();//内部层
            Map<String, Object> desc=new HashMap<String, Object>();
            desc.put("uid",userdynamic.get(i).get("uID"));
            desc.put("type",2);
            QueryWrapper<Dynamicdata> dynamicwrapper = new QueryWrapper<>();//条件构造器
            dynamicwrapper.eq("udID",userdynamic.get(i).get("udID").toString());
            Map<String,Object> mapdynamic=dynamicdataService.getMap(dynamicwrapper);
            if(mapdynamic!=null){
                desc.put("comment",mapdynamic.get("udCommentNum"));
                desc.put("is_like",mapdynamic.get("udLikeNum"));
            }else{
                desc.put("comment",0);
                desc.put("is_like",0);
            }
            QueryWrapper<Dynamiclike> dynamiclikewrapper = new QueryWrapper<>();//条件构造器
            dynamiclikewrapper.eq("udID",userdynamic.get(i).get("udID").toString()).eq("uID",id);
            Map<String,Object> dynamiclike=dynamiclikeService.getMap(dynamiclikewrapper);
            if (dynamiclike!=null) {
                desc.put("like", dynamiclike.get("status"));
            }else{
                desc.put("like", 0);
            }
            desc.put("timestamp",userdynamic.get(i).get("updateTime"));
            desc.put("dynamic_id",userdynamic.get(i).get("udID"));
            Map<String,Object> user_profile=new  HashMap<String, Object>();
            Map<String,Object> info=new HashMap<>();
            Map<String,Object> inin=memberService.user((Integer)userdynamic.get(i).get("uID"));//接口调接口
            info.put("uid",inin.get("uID"));
            info.put("uname",inin.get("userName"));
            info.put("face",inin.get("headImgPath"));
            user_profile.put("info",info);
            Map<String,Object> vip=new HashMap<>();
            Map<String,Object> vipin=memberService.vip((Integer)userdynamic.get(i).get("uID"));//接口调接口
            if(vipin!=null){
                vip.put("status",true);
                Calendar cal = Calendar.getInstance();//时间对象
                int month = (cal.get(Calendar.MONTH)) + 1;//月
                int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
                vip.put("type",month+"/"+day_of_month=="4/1"?0:1);//会员类型
                vip.put("due_date",vipin.get("ExpirationTime"));
            }else{
                vip.put("status",false);
                vip.put("type","无");//会员类型
                vip.put("due_date",0);
            }
            user_profile.put("vip",vip);
            Map<String,Object> level_info=new HashMap<>();
            Map<String,Object> userdata=memberService.userdata((Integer)userdynamic.get(i).get("uID"));
            level_info.put("current_info",userdata.get("Level"));
            user_profile.put("level_info",level_info);

            desc.put("user_profile",user_profile);
            listmap.put("desc",desc);
            listmap.put("card",userdynamic.get(i).get("content"));
            cards.add(listmap);
        }
        //data.put("userdynamic",userdynamic);
        data.put("cards",cards);
        map.put("data",data);
        return map;
    }
    public Map handlerDynamic_history(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //查询指定多个uid的动态数
    @SentinelResource(value = "dynamic_num",fallback="handlerDynamic_num")
    @GetMapping("/dynamic_num")
    public  Map dynamic_num(Integer [] uids){
        Map<String, Object> map=new HashMap<String, Object>();
        if(uids.length>0){
            map.put("code",0);
            map.put("message","");
            map.put("ttl",1);
            Map<String, Object> data=new HashMap<String, Object>();
            map.put("data",data);
            QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();
            wrapper.select("`uID` AS uid,COUNT(`uID`) AS num");
            wrapper.in("uid",uids);
            wrapper.groupBy("uID");
            List<Map<String,Object>> userdy=userdynamicService.listMaps(wrapper);
            data.put("items",userdy);
        }else{
            map.put("code",400);
            map.put("message","数组长度不能等于0");
        }
        return map;
    }
    public Map handlerDynamic_num(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //动态详细信息
    @SentinelResource(value = "dynamic_detail",fallback="handlerDynamic_detail")
    @GetMapping("/dynamic_detail")
    public Map dynamic_detail(Integer dynamic_id){
        Integer id=1;
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message","");
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        Map<String,Object> card=new HashMap<>();//大List
        QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();//条件构造器
        wrapper.eq("udID",dynamic_id);
        List<Map<String,Object>> userdynamic=userdynamicService.listMaps(wrapper);//返回关注up的最新的20条动态 userdynamic表
        for (int i=0;i<userdynamic.size();i++){
            Map <String, Object> listmap=new HashMap<String, Object>();//内部层
            Map<String, Object> desc=new HashMap<String, Object>();
            desc.put("uid",userdynamic.get(i).get("uID"));
            desc.put("type",2);
            QueryWrapper<Dynamicdata> dynamicwrapper = new QueryWrapper<>();//条件构造器
            dynamicwrapper.eq("udID",userdynamic.get(i).get("udID").toString());
            Map<String,Object> mapdynamic=dynamicdataService.getMap(dynamicwrapper);
            if(mapdynamic!=null){
                desc.put("comment",mapdynamic.get("udCommentNum"));
                desc.put("is_like",mapdynamic.get("udLikeNum"));
            }else{
                desc.put("comment",0);
                desc.put("is_like",0);
            }
            QueryWrapper<Dynamiclike> dynamiclikewrapper = new QueryWrapper<>();//条件构造器
            dynamiclikewrapper.eq("udID",userdynamic.get(i).get("udID").toString()).eq("uID",id);
            Map<String,Object> dynamiclike=dynamiclikeService.getMap(dynamiclikewrapper);
            if (dynamiclike!=null) {
                desc.put("like", dynamiclike.get("status"));
            }else{
                desc.put("like", 0);
            }
            desc.put("timestamp",userdynamic.get(i).get("updateTime"));
            desc.put("dynamic_id",userdynamic.get(i).get("udID"));
            Map<String,Object> user_profile=new  HashMap<String, Object>();
            Map<String,Object> info=new HashMap<>();
            Map<String,Object> inin=memberService.user((Integer)userdynamic.get(i).get("uID"));
            info.put("uid",inin.get("uID"));
            info.put("uname",inin.get("userName"));
            info.put("face",inin.get("headImgPath"));
            user_profile.put("info",info);
            Map<String,Object> vip=new HashMap<>();
            Map<String,Object> vipin=memberService.vip((Integer)userdynamic.get(i).get("uID"));
            if(vipin!=null){
                vip.put("status",true);
                Calendar cal = Calendar.getInstance();//时间对象
                int month = (cal.get(Calendar.MONTH)) + 1;//月
                int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
                vip.put("type",month+"/"+day_of_month=="4/1"?0:1);//会员类型
                vip.put("due_date",vipin.get("ExpirationTime"));
            }else{
                vip.put("status",false);
                vip.put("type","无");//会员类型
                vip.put("due_date",0);
            }
            user_profile.put("vip",vip);
            Map<String,Object> level_info=new HashMap<>();
            Map<String,Object> userdata=memberService.userdata((Integer)userdynamic.get(i).get("uID"));
            level_info.put("current_info",userdata.get("Level"));
            user_profile.put("level_info",level_info);
            desc.put("user_profile",user_profile);
            listmap.put("card",userdynamic.get(i).get("content"));
            listmap.put("desc",desc);
            card=listmap;
        }
        data.put("card",card);
        map.put("data",data);
        return map;
    }
    public Map handlerDynamic_detail(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //接口调接口
    @SentinelResource(value = "replyitem",fallback="handlerReplyitem")
    @GetMapping("/replyitem")
    public List<Map<String,Object>> replyitem(Integer userdy){
        List<Map<String,Object>> list=new ArrayList<>();
        QueryWrapper<Userdynamic> userdynamicQueryWrapper=new QueryWrapper<>();
        userdynamicQueryWrapper.eq("uID",userdy);
        List<Userdynamic> userdynamics=userdynamicService.list(userdynamicQueryWrapper);//所有指定id的动态
        Map<Integer,Userdynamic> maplistdynamic=new HashMap<>();
        for (Userdynamic yd:userdynamics){
            maplistdynamic.put(yd.getUdID(),yd);
        }
        Integer[] inte=new Integer[userdynamics.size()];//吧动态id取出来
        for(int i=0;i<userdynamics.size();i++){
            inte[i]=userdynamics.get(i).getUdID();
        }
        QueryWrapper<Dynamiccomment> dynamiccommentQueryWrapper=new QueryWrapper<>();
        dynamiccommentQueryWrapper.in("udID",inte);
        List<Dynamiccomment> listdynamic=dynamiccommentService.list(dynamiccommentQueryWrapper);//用指定的动态查出所有的用户评论
        for (int i=0;i<listdynamic.size();i++){
            Map<String,Object> mapdynamic=new HashMap<>();
            Map<String,Map<String,Object>> chuan=commentService.commcidlist(listdynamic.get(i).getcID());
            mapdynamic.put("source_content",chuan.get("data").get("source_content"));
            mapdynamic.put("type","dynamic");
            mapdynamic.put("business","动态");
            mapdynamic.put("title",maplistdynamic.get(listdynamic.get(i).getUdID()).getContent());
            mapdynamic.put("reply_time",chuan.get("data").get("reply_time"));
            mapdynamic.put("url","");
            mapdynamic.put("image","");
            mapdynamic.put("native_uri","");
            mapdynamic.put("id",chuan.get("data").get("uid"));
            list.add(mapdynamic);
        }
        return list;
    }
    public Map handlerReplyitem(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }
}

