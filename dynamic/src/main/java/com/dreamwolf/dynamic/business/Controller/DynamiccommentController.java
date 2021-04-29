package com.dreamwolf.dynamic.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.dynamic.business.entity.Dynamiccomment;
import com.dreamwolf.dynamic.business.entity.User;
import com.dreamwolf.dynamic.business.entity.Userdata;
import com.dreamwolf.dynamic.business.entity.Vip;
import com.dreamwolf.dynamic.business.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class DynamiccommentController {
    @Resource
    DynamiccommentService dynamiccommentService;
    @Resource
    MemberService memberService;
    @Resource
    CommentService commentService;
    //动态的最新信息
    @RequestMapping("/entrance")
    public Map entrance() {
        Integer id=1;
        Map map= memberService.verify(id);
        return map;
    }

    //dynamicComment表所有信息
    @RequestMapping("/dynamicComment")
    public Map dynamicComment(){
        QueryWrapper<Dynamiccomment> wrapper = new QueryWrapper<>();
        wrapper.eq("udID","1");
        List<Dynamiccomment> relations=dynamiccommentService.list(wrapper);
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("dynamicComment",relations);
        return map;
    }

    //动态评论简略信息
    @GetMapping("/reply")
    public Map reply(Integer dynamic_id,Integer sort){//dynamic_id 动态id sort 1按照热度 2按照时间排序
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("message",0);
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> page=new HashMap<String, Object>();
        QueryWrapper<Dynamiccomment> queryWrapper=new QueryWrapper();
        queryWrapper.eq("udID",dynamic_id);
        List<Map<String,Object>> dynamiccomment=dynamiccommentService.listMaps(queryWrapper);//返回对应动态id的父评论

        page.put("count",dynamiccomment.size());//父评论数
        page.put("num",1);
        page.put("size",10);
        Integer[] list = new Integer[dynamiccomment.size()];//数组 用来储存所有父评论id
        for (int i=0;i<dynamiccomment.size();i++){
            list[i]=(Integer)dynamiccomment.get(i).get("cID");
        }
        List<Map<String,Object>> replies = new ArrayList<>();
        if (list.length>0){
            replies=commentService.commselecarlist(sort,list);
        }
        //显示 评论对象集合的ListMap
        //page.put("acount",);//父加子总评论数
        data.put("replies",replies);
        data.put("page",page);
        map.put("data",data);
        return  map;
    }

    @GetMapping("/reply/main")
    public Map replymain(Integer sort,Integer dynamic_id,Integer next){//sort1按热度 2按时间 动态dynamic_id  next页码
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("message",0);
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        QueryWrapper<Dynamiccomment> queryWrapper=new QueryWrapper();
        queryWrapper.eq("udID",dynamic_id);
        List<Map<String,Object>> dynamiccomment=dynamiccommentService.listMaps(queryWrapper);//返回对应动态id的父评论
        Integer[] list = new Integer[dynamiccomment.size()];//数组 用来储存所有父评论id
        for (int i=0;i<dynamiccomment.size();i++){
            list[i]=(Integer)dynamiccomment.get(i).get("cID");
        }
        List<Map<String,Object>> replies = new ArrayList<>();
        if (list.length>0){
            replies=commentService.commselecarlistpage(sort,list,next);
        }
        //显示 评论对象集合的ListMap
        //page.put("acount",);//父加子总评论数
        data.put("replies",replies);
        map.put("data",data);
        return  map;
    }

    //member发表评论人对象
    @GetMapping("/memberid")
    public Map memberid(Integer id){
        Map<String,Object> data=new HashMap<String, Object>();
        User user=memberService.useruid(id);
        data.put("mid",user.getuID());
        data.put("sex",user.getSex()==1?"男":"女");
        data.put("uname",user.getNickName());
        data.put("avatar",user.getHeadImgPath());
        Map<String,Object> level_info=new HashMap<String, Object>();
        Userdata userdata=memberService.Userdataid(id);
        level_info.put("current_level",userdata.getLevel());
        data.put("level_info",level_info);
        Map<String,Object> vip=new HashMap<String, Object>();
        Vip vips=memberService.vipid(id);
        if(vips!=null){
            vip.put("status",true);
        }else{
            vip.put("status",false);
        }
        data.put("vip",vip);
        return data;
    }

}

