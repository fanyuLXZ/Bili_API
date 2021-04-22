package com.dreamwolf.zoning.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.zoning.business.entity.Video;
import com.dreamwolf.zoning.business.entity.Zoning;
import com.dreamwolf.zoning.business.service.IZoningService;
import com.dreamwolf.zoning.business.service.VideoCount;
import com.dreamwolf.zoning.business.util.Count;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分区表 前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-20
 */
@RestController
public class ZoningController {
    @Resource
    IZoningService iZoningService;

    @Resource
    VideoCount videoCount;

    //全部分区当日新投稿数量
    @GetMapping("/online/all")
    public Map online(){
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);
        map.put("message","");
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        List<Video> list=iZoningService.listInt("2021-04-20");
        Map<String, Object> kele=new HashMap<String, Object>();
        for (Video str : list) {
            kele.put(str.getBvChild().toString(),str.getCountbv());
        }
        data.put("region_count",kele);
        map.put("data",data);
        return map;
    }

    //分区楼层视频卡片数据
    @GetMapping("/region/dynamic")
    public Map dynamic(Integer ps,Integer id){//
        Map<String, Object> map=new HashMap<String, Object>();
        if(ps!=null && !ps.equals("") && id!=null) {
            map.put("code", 0);
            map.put("message", "");
            map.put("ttl", 1);
            Map<String, Object> data = new HashMap<String, Object>();
            Map<String, Object> page = new HashMap<String, Object>();
            page.put("num", 1);
            page.put("size", ps);//分页大小
            QueryWrapper<Zoning> wrapper = new QueryWrapper<>();
            wrapper.eq("zFatherID", id); //查找
            List<Zoning> zoning = iZoningService.list(wrapper);//子分区
            if (zoning!=null){
                Integer[] list = new Integer[zoning.size()];
                for (int i=0;i<zoning.size();i++) {
                    list[i]=zoning.get(i).getzID();
                }
                Integer count=videoCount.vcount(list);//返回总子分区数
                if(count!=null){
                    Count cot=new Count();//随机数
                    Integer suiji=cot.count(ps,count);
                    page.put("count",suiji);//当前页 随即页
                    Map<String, Object> archives=videoCount.videopage(list,suiji,ps);
                    if(archives!=null){
                        data.put("page",page);
                        data.put("archives",archives.get("data"));
                        map.put("data",data);
                    }else{
                        map.put("code",400);
                        map.put("message","视频集合为空");
                    }
                }else{
                    map.put("code",400);
                    map.put("message","总页数为空");
                }
            }else{
                map.put("code",400);
                map.put("message","子分区为空");
            }
        }else{
            map.put("code",400);
            map.put("message","值不能为空");
        }
        return map;
    }

    //返回父组件
    @GetMapping("/zoning")
    public List zoning(){
        //Map<String, Object> map=new HashMap<String, Object>();
        List<Zoning> zoning=iZoningService.isnull();
        //map.put("zoning",zoning);
        return zoning;
    }

    //返回对应父组件的子组件
    @GetMapping("/zoning/id")
    public List zoningid(Integer id){
        QueryWrapper<Zoning> wrapper = new QueryWrapper<>();
        if(id!=null){
            wrapper.eq("zFatherID",id);
        }else{
            wrapper.isNotNull("zFatherID");
        }
        List<Zoning> zoning=iZoningService.list(wrapper);
        return zoning;
    }

}

