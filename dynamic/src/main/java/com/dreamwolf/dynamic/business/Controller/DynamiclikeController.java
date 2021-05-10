package com.dreamwolf.dynamic.business.Controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.dynamic.IikesItems;
import com.dreamwolf.dynamic.business.service.DynamicdataService;
import com.dreamwolf.dynamic.business.service.DynamiclikeService;
import com.dreamwolf.dynamic.business.service.MemberService;
import com.dreamwolf.dynamic.business.service.UserdynamicService;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.dynamic.Dynamicdata;
import com.dreamwolf.entity.dynamic.Dynamiclike;
import com.dreamwolf.entity.dynamic.Userdynamic;
import com.dreamwolf.entity.member.Users;
import com.dreamwolf.entity.message.web_interface.Items;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * <p>
 * 动态点赞表，用于区分用户点赞 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class DynamiclikeController {
    @Resource
    DynamiclikeService dynamiclikeService;
    @Resource
    DynamicdataService dynamicdataService;
    @Resource
    UserdynamicService userdynamicService;
    @Resource
    MemberService memberService;

    //通过动态id查看谁点赞和点赞时间
    @SentinelResource(value = "info",fallback="handlerInfo")
    @RequestMapping("/dynamiclike")
    public ResponseData<List<Dynamiclike>> info(Integer id){
        QueryWrapper<Dynamiclike> wrapper = new QueryWrapper<>();
        wrapper.eq("udID",id);
        List<Dynamiclike> dynamiclike=dynamiclikeService.list(wrapper);
        return new ResponseData<List<Dynamiclike>>(0,"",1,dynamiclike);
    }
    public Map handlerInfo(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //收到的点赞 接口调接口
    @SentinelResource(value = "likeitems",fallback="handlerLikeitems")
    @RequestMapping("/likesitems")
    public ResponseData<List<IikesItems>> likeitems(Integer id){
        List<IikesItems> iikesItems=new ArrayList<>();
        QueryWrapper<Userdynamic> queryWrapper=new QueryWrapper<>();//userdynamic 获取id1的全部动态
        queryWrapper.eq("uID",id);
        List<Userdynamic> fuitem=userdynamicService.list(queryWrapper);
        Map<Integer,Userdynamic> mapintuser=new HashMap<>();
        for(Userdynamic user:fuitem){
            mapintuser.put(user.getUdID(),user);
        }
        List<String> list=new ArrayList<>();
        for(int i=0;i<fuitem.size();i++){
            list.add(fuitem.get(i).getUdID().toString());
        }
        QueryWrapper<Dynamicdata> DynamicdataQueryWrapper=new QueryWrapper<>();//id1的全部动态 判断出被点过赞的动态
        DynamicdataQueryWrapper.in("udID",list);
        List<Dynamicdata> statecommend=dynamicdataService.list(DynamicdataQueryWrapper);
        for(int i=0;i<statecommend.size();i++){
            QueryWrapper<Dynamiclike> dynamiclikeQueryWrapper=new QueryWrapper<>();//点过赞的固定id的动态
            dynamiclikeQueryWrapper.eq("udID",statecommend.get(i).getUdID()).eq("status",1).orderByDesc("createTime");
            List<Dynamiclike> mapList=dynamiclikeService.list(dynamiclikeQueryWrapper);
            Integer[] ints=new Integer[mapList.size()];
            for (int is=0;is<mapList.size();is++){
                ints[is]=mapList.get(is).getuID();
            }
            List<Users> users=memberService.users(ints,id).getData();//点赞对应id动态的用户
            LocalDateTime da=fuitem.get(i).getUpdateTime();
            Date datt = Date.from(da.atZone(ZoneId.systemDefault()).toInstant());
            Items items=new Items(statecommend.get(i).getUdID(),"dynamic",
                    mapintuser.get(statecommend.get(i).getUdID()).getContent(),
                    "","","",datt);
            IikesItems ii=new IikesItems(id,users,items,statecommend.get(i).getUdCommentNum().toString(),
                    mapList.get(0).getCreateTime());
            iikesItems.add(ii);

        }
        return new ResponseData<List<IikesItems>>(0,"",1,iikesItems);
    }
    public List<Map<String,Object>> handlerLikeitems(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        List<Map<String,Object>> listmap=new ArrayList<>();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        listmap.add(map);
        return listmap;
    }

}

