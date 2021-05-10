package com.dreamwolf.dynamic.business.Controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.dynamic.Cards;
import com.dreamwolf.entity.dynamic.Desc;
import com.dreamwolf.entity.dynamic.UserProfile;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.comment.web_interface.Commcidmap;
import com.dreamwolf.entity.dynamic.*;
import com.dreamwolf.dynamic.business.service.*;
import com.dreamwolf.entity.dynamic.Userdata;
import com.dreamwolf.entity.dynamic.web_interface.DynamicCards;
import com.dreamwolf.entity.dynamic.web_interface.DynamicCardsid;
import com.dreamwolf.entity.dynamic.web_interface.DynamicNumObject;
import com.dreamwolf.entity.dynamic.web_interface.ItemsNumObject;
import com.dreamwolf.entity.member.*;
import com.dreamwolf.entity.member.User;
import com.dreamwolf.entity.member.Vip;
import com.dreamwolf.entity.message.web_interface.Items;
import com.dreamwolf.entity.message.web_interface.MMItems;
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
    public ResponseData<List<Userdynamic>> userdynamicList(Integer uid){
        QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();
        wrapper.eq("uID",uid);
        List<Userdynamic> userdynamics=userdynamicService.list(wrapper);
        return new ResponseData<List<Userdynamic>>(0,"",1,userdynamics);
    }
    public Map handlerUserdynamicList(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //获取动态id的所有信息
    @SentinelResource(value = "userdynamic",fallback="handlerUserdynamic")
    @RequestMapping("/userdynamic")
    public ResponseData<List<Userdynamic>> userdynamic(Integer udID){
        QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();
        wrapper.eq("udID",udID);
        List<Userdynamic> userdynamics=userdynamicService.list(wrapper);
        return new ResponseData<List<Userdynamic>>(0,"",1,userdynamics);
    }
    public Map handlerUserdynamic(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //最新的20条动态
    @RequestMapping("/dynamic_new")
    @SentinelResource(value = "dynamic_new",fallback="handlerDynamic_new")
    public ResponseData<DynamicCards> dynamic_new(){

        Integer id=1;
        List<Relations> listMap=memberService.intuid(id).getData();
        List<String> resultList=new ArrayList<>();
        for (int i=0; i<listMap.size();i++){
            resultList.add(listMap.get(i).getuID().toString());
        }
        List<Cards> cards=new ArrayList<>();
        QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();//条件构造器
        wrapper.in("uID",resultList).orderByDesc("updateTime").last("limit "+20);
        List<Userdynamic> userdynamic=userdynamicService.list(wrapper);//返回关注up的最新的20条动态 userdynamic表
        for (int i=0;i<userdynamic.size();i++){
            QueryWrapper<Dynamicdata> dynamicwrapper = new QueryWrapper<>();//条件构造器
            dynamicwrapper.eq("udID",userdynamic.get(i).getUdID().toString());
            Dynamicdata mapdynamic=dynamicdataService.getOne(dynamicwrapper);//
            QueryWrapper<Dynamiclike> dynamiclikewrapper = new QueryWrapper<>();//条件构造器
            dynamiclikewrapper.eq("udID",userdynamic.get(i).getUdID().toString()).eq("uID",id);
            Dynamiclike dynamiclike=dynamiclikeService.getOne(dynamiclikewrapper);
            User inin=memberService.useruid(userdynamic.get(i).getuID()).getData();//查询User对象
            Member member=new Member(inin.getuID(),inin.getNickName(),inin.getHeadImgPath());
            Vip vipin=memberService.vip(userdynamic.get(i).getuID()).getData();
            Calendar cal = Calendar.getInstance();//时间对象
            int month = (cal.get(Calendar.MONTH)) + 1;//月
            int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
            VipStatus vipStatus=null;
            if(vipin!=null){
                vipStatus=new VipStatus(true,(month+"/"+day_of_month).equals("4/1")?0:1,vipin.getExpirationTime());
            }
            Userdata userdata=memberService.userdata(userdynamic.get(i).getuID()).getData();
            Level_info level_info=new Level_info(userdata.getLevel());
            Desc desc=new Desc(
                    userdynamic.get(i).getuID(),
                    2,
                    mapdynamic!=null?mapdynamic.getUdCommentNum().intValue():null,
                    mapdynamic!=null?mapdynamic.getUdLikeNum().intValue():null,
                    dynamiclike!=null?dynamiclike.getStatus():null,
                    userdynamic.get(i).getUpdateTime(),
                    userdynamic.get(i).getUdID(),
                    new UserProfile(member,vipStatus,level_info));
            String card=userdynamic.get(i).getContent();
            cards.add(new Cards(desc,card));
        }
        return new  ResponseData<DynamicCards>(0,"",1,new DynamicCards(cards));
    }
    public Map handlerDynamic_new(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //根据偏移动态id获取后面20条动态信息 /dynamic_history
    @SentinelResource(value = "dynamic_history",fallback="handlerDynamic_history")
    @GetMapping("dynamic_history")
    public ResponseData<DynamicCards> dynamic_history(Integer offset_dynamic_id){
        Integer id=1;
        List<Relations> listMap=memberService.intuid(id).getData();
        List<Integer> resultList=new ArrayList<>();
        for (int i=0; i<listMap.size();i++){
            resultList.add(listMap.get(i).getuID());
        }
        List<Cards> cards=new ArrayList<>();
        List<Userdynamic> userdynamic=userdynamicService.listmap(offset_dynamic_id,resultList.toArray(new Integer[0]));
        for (int i=0;i<userdynamic.size();i++){
            QueryWrapper<Dynamicdata> dynamicwrapper = new QueryWrapper<>();//条件构造器
            dynamicwrapper.eq("udID",userdynamic.get(i).getUdID().toString());
            Dynamicdata mapdynamic=dynamicdataService.getOne(dynamicwrapper);//
            QueryWrapper<Dynamiclike> dynamiclikewrapper = new QueryWrapper<>();//条件构造器
            dynamiclikewrapper.eq("udID",userdynamic.get(i).getUdID().toString()).eq("uID",id);
            Dynamiclike dynamiclike=dynamiclikeService.getOne(dynamiclikewrapper);
            User inin=memberService.useruid(userdynamic.get(i).getuID()).getData();//查询User对象
            Member member=new Member(inin.getuID(),inin.getNickName(),inin.getHeadImgPath());
            Vip vipin=memberService.vip(userdynamic.get(i).getuID()).getData();
            Calendar cal = Calendar.getInstance();//时间对象
            int month = (cal.get(Calendar.MONTH)) + 1;//月
            int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
            VipStatus vipStatus=null;
            if(vipin!=null){
                vipStatus=new VipStatus(true,(month+"/"+day_of_month).equals("4/1")?0:1,vipin.getExpirationTime());
            }
            Userdata userdata=memberService.userdata(userdynamic.get(i).getuID()).getData();
            Level_info level_info=new Level_info(userdata.getLevel());
            Desc desc=new Desc(
                    userdynamic.get(i).getuID(),
                    2,
                    mapdynamic!=null?mapdynamic.getUdCommentNum().intValue():null,
                    mapdynamic!=null?mapdynamic.getUdLikeNum().intValue():null,
                    dynamiclike!=null?dynamiclike.getStatus():null,
                    userdynamic.get(i).getUpdateTime(),
                    userdynamic.get(i).getUdID(),
                    new UserProfile(member,vipStatus,level_info));
            String card=userdynamic.get(i).getContent();
            cards.add(new Cards(desc,card));
        }
        return new  ResponseData<DynamicCards>(0,"",1,new DynamicCards(cards));
    }
    public Map handlerDynamic_history(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }



    //动态详细信息
    @SentinelResource(value = "dynamic_detail",fallback="handlerDynamic_detail")
    @GetMapping("/dynamic_detail")
    public ResponseData<DynamicCardsid> dynamic_detail(Integer dynamic_id){
        Integer id=1;
        QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();//条件构造器
        wrapper.eq("udID",dynamic_id);
        Userdynamic userdynamic=userdynamicService.getOne(wrapper);//返回关注up的最新的20条动态 userdynamic表

        QueryWrapper<Dynamicdata> dynamicwrapper = new QueryWrapper<>();//条件构造器
        dynamicwrapper.eq("udID",userdynamic.getUdID().toString());
        Dynamicdata mapdynamic=dynamicdataService.getOne(dynamicwrapper);//
        QueryWrapper<Dynamiclike> dynamiclikewrapper = new QueryWrapper<>();//条件构造器
        dynamiclikewrapper.eq("udID",userdynamic.getUdID().toString()).eq("uID",id);
        Dynamiclike dynamiclike=dynamiclikeService.getOne(dynamiclikewrapper);
        User inin=memberService.useruid(userdynamic.getuID()).getData();//查询User对象
        Member member=new Member(inin.getuID(),inin.getNickName(),inin.getHeadImgPath());
        Vip vipin=memberService.vip(userdynamic.getuID()).getData();
        Calendar cal = Calendar.getInstance();//时间对象
        int month = (cal.get(Calendar.MONTH)) + 1;//月
        int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
        VipStatus vipStatus=null;
        if(vipin!=null){
            vipStatus=new VipStatus(true,(month+"/"+day_of_month).equals("4/1")?0:1,vipin.getExpirationTime());
        }
        Userdata userdata=memberService.userdata(userdynamic.getuID()).getData();
        Level_info level_info=new Level_info(userdata.getLevel());
        Desc desc=new Desc(
                userdynamic.getuID(),
                2,
                mapdynamic!=null?mapdynamic.getUdCommentNum().intValue():null,
                mapdynamic!=null?mapdynamic.getUdLikeNum().intValue():null,
                dynamiclike!=null?dynamiclike.getStatus():null,
                userdynamic.getUpdateTime(),
                userdynamic.getUdID(),
                new UserProfile(member,vipStatus,level_info));
        String card=userdynamic.getContent();//文本
        Cards cards=new Cards(desc,card);
        return new ResponseData<DynamicCardsid>(0,"",1,new DynamicCardsid(cards));
    }
    public Map handlerDynamic_detail(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //查询指定多个uid的动态数
    @SentinelResource(value = "dynamic_num",fallback="handlerDynamic_num")
    @GetMapping("/dynamic_num")
    public  ResponseData<ItemsNumObject> dynamic_num(Integer [] uids){
        List<DynamicNumObject> dynamicNumObjectList=new ArrayList<>();
        int code = 0;
        String message="";
        if(uids.length>0){
            QueryWrapper<Userdynamic> wrapper = new QueryWrapper<>();
            wrapper.select("`uID` AS uid,COUNT(`uID`) AS num");
            wrapper.in("uid",uids);
            wrapper.groupBy("uID");
            List<Map<String,Object>> userdy=userdynamicService.listMaps(wrapper);
            DynamicNumObject dynamicNumObject=null;
            for(Map<String,Object> user:userdy){
                dynamicNumObject=new DynamicNumObject(user);
                dynamicNumObjectList.add(dynamicNumObject);
            }
        }else{
            code = 1;
            message="uids下标为0";
        }
        ItemsNumObject items=new ItemsNumObject(dynamicNumObjectList);
        return new ResponseData<ItemsNumObject>(code,message,1,items);
    }
    public Map handlerDynamic_num(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //接口调接口  根据动态id返回评论的我数据
    @SentinelResource(value = "replyitem",fallback="handlerReplyitem")
    @GetMapping("/replyitem")
    public ResponseData<List<MMItems>> replyitem(Integer userdy){
        List<MMItems> mmItemsList=new ArrayList<>();
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
            Commcidmap chuan=commentService.commcidlist(listdynamic.get(i).getcID()).getData();
            MMItems commcidmap=new MMItems(chuan.getSource_content(),"dynamic","动态",
                    maplistdynamic.get(listdynamic.get(i).getUdID()).getContent(),chuan.getReply_time(),"",chuan.getUid(),"","");
            mmItemsList.add(commcidmap);
        }
        return new ResponseData<List<MMItems>>(0,"",1,mmItemsList);
    }
    public Map handlerReplyitem(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }
}

