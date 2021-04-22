package com.dreamwolf.video.controller;


import com.dreamwolf.video.pojo.Video;
import com.dreamwolf.video.pojo.Videodata;
import com.dreamwolf.video.pojo.Videorating;
import com.dreamwolf.video.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 视频基础信息表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
public class VideoController {

    @Resource
    private VideoService videoService;

    @Resource
    private UserpageService userpageService;

    @Resource
    private UsermapService usermapService;

    @Resource
    private VideodataService videodataService;

    @Resource
    private VideoratingService videoratingService;

    //通过子分区id查视频,返回list
//    @GetMapping("/videobvldZoning")
//    public Map videobvChildZoning(Integer[] bvChildZoning){
//        Map map = new HashMap();
//        if(bvChildZoning !=null) {
//            map.put("code",0);
//            map.put("message","0");
//            List<Video> list = videoService.videoZoningIdlist(bvChildZoning);
//            map.put("data",list);
//        }else{
//            map.put("code",400);
//            map.put("message","传入的参数(bvChildZoning)不能为空");
//            map.put("data",null);
//        }
//        return map;
//    }

    //通过bv号(视频id)查视频，返回对象
    @GetMapping("/videobvID")
    public Map videobvID(Integer bvID){
        Map map = new HashMap();
        if(bvID !=null) {
            map.put("code",0);
            map.put("message","0");
            Video video = videoService.videobvIDlist(bvID);
            map.put("data",video);
        }else{
            map.put("code",400);
            map.put("message","传入的参数(bvID)不能为空");
            map.put("data",null);
        }
        return map;
    }

    //通过作者id查视频，返回list
    @GetMapping("/videouID")
    public Map videouID(Integer uID){
        Map map = new HashMap();
        if(uID !=null) {
            map.put("code",0);
            map.put("message","0");
            List<Video> list = videoService.videouIDlist(uID);
            map.put("data",list);
        }else{
            map.put("code",400);
            map.put("message","传入的参数(uID)不能为空");
            map.put("data",null);
        }

        return map;
    }

    //查video的所有数据
    @GetMapping("/videolist")
    public Map videoselectlist(){
        Map map = new HashMap();
        if(map !=null) {
            map.put("code",0);
            map.put("message","0");
            List<Video> list = videoService.selectlist();
            map.put("data", list);
        }else{
            map.put("code",400);
            map.put("message","数据为空");
            map.put("data",null);
        }

        return map;
    }

    //根据子分区集合查询视频数据并分页处理  参数：
//    bvChildZoninglist 子分区集合
//    pageSize 从第几页开始
//    pagecount 一页显示多少条
    @GetMapping("/videopage")
    public Map selectpage(Integer[] list,Integer count,Integer ps){
        Map map = new HashMap();
        if(map!=null && list!=null) {
            //子分区集合查询视频数据 返回的分页集合
            List<Video> videolist = videoService.videoPagebvzoing(list,count,ps);
            if(videolist!=null) {
                List<Map> listma = new ArrayList<>();
                for (Video vid : videolist) {
                    Map map2 = new HashMap();
                    map2.put("aid", vid.getBvID());
                    map2.put("pic", vid.getBvCoverImgPath());
                    map2.put("title", vid.getBvTitle());
                    //根据uid查询的用户对象
                    Map usermap = usermapService.user(vid.getUID());
                    if (usermap != null) {
                        Map mapu = new HashMap();
                        mapu.put("mid", usermap.get("uID"));
                        mapu.put("name", usermap.get("userName"));
                        map2.put("owner", mapu);
                    } else {
                        map2.put("owner", null);
                    }
                    //根据bvid查询的视频基本数据（硬币，点赞数等）
                    Videodata videodata = videodataService.selectbvID(vid.getBvID());

                        Map map33 = new HashMap();
                        if (videodata != null) {

                            map33.put("aid", videodata.getBvID());
                            map33.put("coin", videodata.getBvCoinNum());
                            map33.put("view", videodata.getBvPopupsNum());
                            map33.put("like", videodata.getBvLikeNum());
                        } else {
                            map33.put("aid", 0);
                            map33.put("coin", 0);
                            map33.put("view", 0);
                            map33.put("like", 0);
                        }
                        map2.put("stat", map33);
                    map2.put("duration", vid.getDuration());
                    map2.put("bvid", "bv" + vid.getBvID());
                    listma.add(map2);
                }
                map.put("data", listma);
            }else{
                map.put("data", null);
            }
        }else {
            map.put("code",400);
            map.put("message","数据为空");
            map.put("data",null);
        }
        return  map;
    }

    /**
     * 返回根据子分区查找的视频总数
     * @param
     * @return
     */
    @GetMapping("/videocount")
    public Integer selcount(Integer[] list){
        return videoService.videocount(list);
    }


    /**
     *  视频集合对象
     * @param bvChildZoning 子分区id
     * @return
     */
    @GetMapping("/videodeorating")
    public Map selectdeorating(Integer[] bvChildZoning, Integer datetime){

        Calendar  cr = Calendar.getInstance();
        int year=cr.get(Calendar.YEAR); //年
        int month = (cr.get(Calendar.MONTH)) + 1;//月
        int day = cr.get(Calendar.DAY_OF_MONTH);//天
        int a = day-datetime; //减去传过来的时间
        int b =day+1;
        String qian=year+"-"+month+"-"+a;
        String hou=year+"-"+month+"-"+b;

        Map map = new HashMap();
        if(map!=null && bvChildZoning!=null && datetime!=null){
            List<Video> list = videoService.videoZoningIdlist(bvChildZoning,qian,hou);
            if(list!=null){
                List listmap = new ArrayList();
                Map mapdata = new HashMap();
//                List list3 = new ArrayList();
                for(Video video : list){
                    mapdata.put("aid",video.getBvID());
                    mapdata.put("bvid","bv"+video.getBvID());
                    mapdata.put("pic",video.getBvCoverImgPath());
                    mapdata.put("title",video.getBvTitle());
                    String cname=userpageService.elementby(video.getBvChildZoning());
                    if(cname !=null) {
                        mapdata.put("typename", cname);
                    }else{
                        mapdata.put("typename", "别看了防报错typename为空");
                    }
                    Videorating videorating = videoratingService.selectbvid(video.getBvID());
                    if(videorating!=null){
                    mapdata.put("pts",videorating.getOverallRating());
                    }else{
                        mapdata.put("pts",0);
                    }
                    listmap.add(mapdata);
                }
                map.put("data",listmap);
            }else {
                map.put("data",null);
            }
        }else{
            map.put("code",400);
            map.put("message","数据为空或者参数有误");
            map.put("data",null);
        }
        return map;
    }





}

