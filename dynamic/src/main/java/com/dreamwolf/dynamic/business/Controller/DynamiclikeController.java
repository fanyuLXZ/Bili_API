package com.dreamwolf.dynamic.business.Controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.dynamic.business.service.DynamicdataService;
import com.dreamwolf.dynamic.business.service.DynamiclikeService;
import com.dreamwolf.dynamic.business.service.MemberService;
import com.dreamwolf.dynamic.business.service.UserdynamicService;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.dynamic.Dynamicdata;
import com.dreamwolf.entity.dynamic.Dynamiclike;
import com.dreamwolf.entity.dynamic.Userdynamic;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
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

    //通过动态id 查看谁点赞和点赞时间
    @SentinelResource(value = "info",fallback="handlerInfo")
    @RequestMapping("/dynamiclike")
    public ResponseData<List<Dynamiclike>> info(){
        QueryWrapper<Dynamiclike> wrapper = new QueryWrapper<>();
        wrapper.eq("udID","2");
        List<Dynamiclike> dynamiclike=dynamiclikeService.list(wrapper);
        return new ResponseData<List<Dynamiclike>>(0,"",1,dynamiclike);
    }
    public Map handlerInfo(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        return map;
    }

    //收到的点赞
    @SentinelResource(value = "likeitems",fallback="handlerLikeitems")
    @RequestMapping("/likesitems")
    public List<Map<String,Object>> likeitems(Integer id){
        /*Integer id=1;
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message","");
        map.put("ttl",1);user/info
        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> total=new HashMap<String, Object>();*/
        List<Map<String,Object>> items=new ArrayList<>();//点赞数组
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
        List<Map<String,Object>> statecommend=dynamicdataService.listMaps(DynamicdataQueryWrapper);
        for(int i=1;i<statecommend.size();i++){
            Map<String,Object> listmap=new HashMap<>();
            Map<String,Object> item=new HashMap<>();//被点赞对应id的动态
            item.put("item_id",statecommend.get(i).get("udID"));
            int z=(Integer) statecommend.get(i).get("udID");
            item.put("type","dynamic");
            item.put("title",mapintuser.get(z).getContent());
            item.put("desc","");
            item.put("image","");
            item.put("url","");

            QueryWrapper<Dynamiclike> dynamiclikeQueryWrapper=new QueryWrapper<>();//点过赞的固定id的动态
            dynamiclikeQueryWrapper.eq("udID",statecommend.get(i).get("udID")).eq("status",1).orderByDesc("createTime");
            List<Map<String,Object>> mapList=dynamiclikeService.listMaps(dynamiclikeQueryWrapper);
            Integer[] ints=new Integer[mapList.size()];
            for (int is=0;is<mapList.size();is++){
                ints[is]= (Integer)mapList.get(is).get("uID");
            }
            List<Map<String,Object>> users=memberService.users(ints,id);//点赞对应id动态的用户

            listmap.put("users",users);
            listmap.put("item",item);
            listmap.put("counts",statecommend.get(i).get("udCommentNum"));
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            listmap.put("like_time",df.format((Date)mapList.get(0).get("createTime")));
            items.add(listmap);
        }
        //total.put("statecommend",statecommend);
        //total.put("items",items);
        /*data.put("total",total);
        map.put("data",data);*/
        return items;
    }
    public List<Map<String,Object>> handlerLikeitems(@PathVariable Integer id, Throwable e) {
        Map map=new HashMap();
        List<Map<String,Object>> listmap=new ArrayList<>();
        map.put(444,"[业务异常兜底降级方法],exception内容:"+e.getMessage());
        listmap.add(map);
        return listmap;
    }

}

