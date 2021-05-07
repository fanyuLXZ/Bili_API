package com.dreamwolf.zoning.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.video.web_interface.ArchivesInfo;
import com.dreamwolf.entity.zoning.web_interface.Deputydivision;
import com.dreamwolf.entity.zoning.web_interface.DynamicRegion;
import com.dreamwolf.entity.zoning.web_interface.Mainpartition;
import com.dreamwolf.entity.zoning.Zoning;
import com.dreamwolf.entity.zoning.web_interface.Page;
import com.dreamwolf.zoning.business.service.IZoningService;
import com.dreamwolf.zoning.business.service.VideoCount;
import com.dreamwolf.zoning.business.util.Count;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
        Map<String, Object> kele=videoCount.selmap("2021-04-21");
        data.put("region_count",kele);
        map.put("data",data);
        return map;
    }

    //分区楼层视频卡片数据
    @GetMapping("/region/dynamic")
    public Map dynamic(Integer ps,Integer rid){
        Map<String, Object> map=new HashMap<String, Object>();
        if(ps!=null && !ps.equals("") && rid!=null) {
            map.put("code", 0);
            map.put("message", "");
            map.put("ttl", 1);
            Map<String, Object> data = new HashMap<String, Object>();
            Map<String, Object> page = new HashMap<String, Object>();
            page.put("num", 1);
            page.put("size", ps);//分页大小
            QueryWrapper<Zoning> wrapper = new QueryWrapper<>();
            wrapper.eq("zFatherID", rid); //查找
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

    //指定分区排行榜 前十二个
    @GetMapping("/region/ranking")
    public Map region(Integer rid,Integer day){
        Map<String, Object> map = new HashMap<String, Object>();
        if(rid!=null && !rid.equals("")){
            QueryWrapper<Zoning> wrapper = new QueryWrapper<>();
            wrapper.eq("zFatherID", rid); //查找条件
            List<Zoning> zoning = iZoningService.list(wrapper);//获取对应父分区的子分区
            if (zoning!=null){
                Integer[] list = new Integer[zoning.size()];
                for (int i=0;i<zoning.size();i++) {
                    list[i]=zoning.get(i).getzID();
                }
                Map maplist=videoCount.selectdeorating(list,day);
                if(maplist!=null){
                    map.put("code", 0);
                    map.put("message", "");
                    map.put("ttl", 1);
                    map.put("data",maplist.get("data"));
                }else{
                    map.put("code",400);
                    map.put("message","返回的集合为空");
                }
            }
        }else{
            map.put("code",400);
            map.put("message","rid不能为空");
        }
        return map;
    }

    //Zong 通过组件id返回组件名称
    @GetMapping("elementby")
    public String elementby(Integer zID){
        String zName=null;
        if (zID!=null && !zID.equals("")){
            Zoning zoning=iZoningService.getById(zID);
            zName=zoning.getzName();
        }
        return zName;
    }

    //子分区最新动态(四个)
    /*@GetMapping("/dynamic/region")
    public MainpardeputyInfo region(Integer rid, Integer pn, Integer ps){//子分区id 页码 每页数

        return map;
    }*/

    //父分区
    @GetMapping("/mainpartition")
    public ResponseData<Mainpartition> mainpartition(Integer bvChildZoning){
        QueryWrapper<Zoning> zoningQueryWrapperfu=new QueryWrapper<>();
        zoningQueryWrapperfu.eq("zID",bvChildZoning);
        Zoning zoning=iZoningService.getById(bvChildZoning);//子信息
        Zoning zoningfu=iZoningService.getById(zoning.getzFatherID());//父信息
        return new ResponseData<Mainpartition>(0,"",1,new Mainpartition(zoningfu));
    }
    //子分区
    @GetMapping("/deputydivision")
    public ResponseData<Deputydivision> deputydivision(Integer bvChildZoning){
        QueryWrapper<Zoning> zoningQueryWrapperfu=new QueryWrapper<>();
        zoningQueryWrapperfu.eq("zID",bvChildZoning);
        Zoning zoning=iZoningService.getById(bvChildZoning);//子信息
        return new ResponseData<Deputydivision>(0,"",1, new Deputydivision(zoning));
    }

    //子分区最新动态（四个）
    @GetMapping("/dynamic/region")
    public ResponseData<DynamicRegion> dynamicRegion(Integer rid, Integer pn, Integer ps){//子分区 页码 没页数
        Integer count=videoCount.selectidcoutn(rid);
        Page page=new Page(count,pn,ps);
        List<ArchivesInfo> archivesInfo=videoCount.selectvideorid(rid,pn,ps);
        return new ResponseData<DynamicRegion>(0,"",1,new DynamicRegion(page,archivesInfo));
    }
}

