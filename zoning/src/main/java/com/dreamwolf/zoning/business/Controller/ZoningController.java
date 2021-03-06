package com.dreamwolf.zoning.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.video.web_interface.*;
import com.dreamwolf.entity.zoning.web_interface.Deputydivision;
import com.dreamwolf.entity.zoning.web_interface.DynamicRegion;
import com.dreamwolf.entity.zoning.web_interface.Mainpartition;
import com.dreamwolf.entity.zoning.Zoning;
import com.dreamwolf.entity.zoning.web_interface.Page;
import com.dreamwolf.zoning.business.entity.NewList;
import com.dreamwolf.zoning.business.entity.RegionCount;
import com.dreamwolf.zoning.business.service.IZoningService;
import com.dreamwolf.zoning.business.service.VideoCount;
import com.dreamwolf.zoning.business.util.Count;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

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


    @GetMapping("/online/all")
    public ResponseData<RegionCount> online(){
        //SELECT zID FROM `zoning` WHERE `zFatherID` IS NULL
        QueryWrapper<Zoning> zoningQueryWrapper=new QueryWrapper<>();
        zoningQueryWrapper.isNull("zFatherID");
        List<Zoning> zID=iZoningService.list(zoningQueryWrapper);
        Integer[] arr = new Integer[zID.size()];//方式三
        String datetime = "2021-05-06";
        Map map=new HashMap();
        for(int i=0;i<zID.size();i++){
            arr[i]=zID.get(i).getzID();
            QueryWrapper<Zoning> zoning=new QueryWrapper<>();
            zoning.eq("zFatherID",zID.get(i).getzID());
            List<Zoning> zFatherID=iZoningService.list(zoning);//子分区
            List<Integer> li = new ArrayList();
            for(Zoning zz : zFatherID){
                li.add(zz.getzID());
            }
            Kele kele = videoCount.videoridcountselec(arr[i],li.toArray(new Integer[0]),datetime).getData();
            map.put(kele.getCountbv(),kele.getBvChild());
        }
        return new ResponseData<RegionCount>(0,"",1,new RegionCount(map));
    }

    //分区楼层视频卡片数据
    @GetMapping("/dynamic")
    public Map dynamic(Integer ps,Integer rid){
        Map<String, Object> map=new HashMap<String, Object>();
        if(ps != null && rid != null) {
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
                Integer count=videoCount.selcount(list).getData();//返回总子分区数
                if(count!=null){
                    Count cot=new Count();//随机数
                    Integer suiji=cot.count(ps,count);
                    page.put("count",suiji);//当前页 随即页
                    ResponseData<List<VideoMaplist>> archives=videoCount.videopage(list,suiji,ps);
                    if(archives!=null){
                        if (archives.getCode()==0){
                            data.put("page",page);
                            data.put("archives",archives.getData());
                            map.put("data",data);
                        }else {
                            map.put("code",archives.getCode());
                            map.put("message",archives.getMessage());
                        }
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
    @GetMapping("/ranking")
    public ResponseData<List<VideoMaplist>> region(Integer rid,Integer day){
        int code = 0;
        String message="";
        List<VideoMaplist> maplist=null;
        if(rid!=null && day!=null){
            QueryWrapper<Zoning> wrapper = new QueryWrapper<>();
            wrapper.eq("zFatherID", rid); //查找条件
            List<Zoning> zoning = iZoningService.list(wrapper);//获取对应父分区的子分区
            if (zoning!=null){
                Integer[] list = new Integer[zoning.size()];
                for (int i=0;i<zoning.size();i++) {
                    list[i]=zoning.get(i).getzID();
                }
                 maplist=videoCount.selectdeorating(list,day).getData();
            }
        }else{
            code = 1;
            message="rid和day不能为空";
        }
        return new ResponseData<List<VideoMaplist>>(code,message,1,maplist);
    }

    //Zong 通过组件id返回组件名称
    @GetMapping("/elementby")
    public String elementby(Integer zID){
        String zName=null;
        if (zID!=null && !zID.equals("")){
            Zoning zoning=iZoningService.getById(zID);
            zName=zoning.getzName();
        }
        return zName;
    }

    //按子分区id查找对应父分区信息
    @GetMapping("/mainpartition")
    public ResponseData<Mainpartition> mainpartition(Integer bvChildZoning){
        QueryWrapper<Zoning> zoningQueryWrapperfu=new QueryWrapper<>();
        zoningQueryWrapperfu.eq("zID",bvChildZoning);
        Zoning zoning=iZoningService.getById(bvChildZoning);//子信息
        Zoning zoningfu=iZoningService.getById(zoning.getzFatherID());//父信息
        return new ResponseData<Mainpartition>(0,"",1,new Mainpartition(zoningfu));
    }
    //id查找对应子分区和父分区信息
    @GetMapping("/deputydivision")
    public ResponseData<Deputydivision> deputydivision(Integer bvChildZoning){
        QueryWrapper<Zoning> zoningQueryWrapperfu=new QueryWrapper<>();
        zoningQueryWrapperfu.eq("zID",bvChildZoning);
        Zoning zoning=iZoningService.getById(bvChildZoning);//子信息
        return new ResponseData<Deputydivision>(0,"",1, new Deputydivision(zoning));
    }

    //子分区最新动态
    @GetMapping("/dynamic/child")
    public ResponseData<DynamicRegion> childRegionDynamic(Integer rid, Integer pn, Integer ps){//子分区 页码 没页数
        Integer count= videoCount.selectidcoutn(rid);
        // 如果没有传入页码则随机页码
        if (pn==null){
            int pc = count/ps;
            pn = pc>0?new Random().nextInt(pc):0;
        }
        Page page=new Page(count,pn,ps);
        List<ArchivesInfo> archivesInfo=videoCount.selectvideorid(rid,pn,ps).getData();
        return new ResponseData<>(0,"",1,new DynamicRegion(page,archivesInfo));
    }

    //子分区视频按投稿时间排序（二十个）
    @GetMapping("/cate/search")
    public ResponseData<DynamicRegion> catesearch(Integer rid, Integer pn, Integer ps){//子分区 页码 没页数
        Integer count=  videoCount.selectidcoutn(rid);
        Page page=new Page(count,pn,ps);
        List<ArchivesInfo> archivesInfo=videoCount.selectvideorid(rid,pn,ps).getData();
        return new ResponseData<DynamicRegion>(0,"",1,new DynamicRegion(page,archivesInfo));
    }

    //子分区视频按视频热度排序(二十个)
    @GetMapping("/newlist")
    public  ResponseData<NewList> newlist(Integer rid, Integer pn, Integer ps){

        List<Result> result=videoCount.selectbvidlistpage(rid,pn,ps).getData();
        NewList newList=new NewList(result.size(),result.size()/ps,pn,ps,result);
        return  new ResponseData<NewList>(0,"",1,newList);
    }

    //排行榜（十个）ranking/child
    @GetMapping("/ranking/child")
    public ResponseData<List<Region>> region(Integer rid){
        List<Region> listResponseData=videoCount.selectbvidlistpagelist(rid).getData();
        return  new ResponseData<List<Region>>(0,"",1,listResponseData);
    }
}

