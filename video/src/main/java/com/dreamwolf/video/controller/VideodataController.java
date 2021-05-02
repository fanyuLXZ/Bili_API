package com.dreamwolf.video.controller;


import com.dreamwolf.video.entity.web_interface.Relatedinfo;
import com.dreamwolf.video.entity.web_interface.Statinfo;
import com.dreamwolf.video.entity.web_interface.Videodatainfo;
import com.dreamwolf.video.entity.web_interface.Videoinfo;
import com.dreamwolf.video.pojo.Video;
import com.dreamwolf.video.pojo.Videodata;
import com.dreamwolf.video.pojo.Videorating;
import com.dreamwolf.video.service.VideoService;
import com.dreamwolf.video.service.VideodataService;
import com.dreamwolf.video.service.VideoratingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 视频数据表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
public class VideodataController {

    @Resource
    private VideodataService videodataService;

    @Resource
    private VideoratingService videoratingService;

    @Resource
    private VideoService videoService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @GetMapping(value = "/videodatabvID")
    public Map selectdvid(Integer bvID){
        Map map = new HashMap();
        if(bvID !=null) {
            map.put("code",0);
            map.put("message","0");
            Videodata videodata = videodataService.selectbvID(bvID); //根据bvID查询的视频基本数据
            map.put("data",videodata);
        }else{
            map.put("code",400);
            map.put("message","传入的参数(bvID)不能为空");
            map.put("data",null);
        }

        return map;
    }

    @GetMapping(value = "/videodatalist")
    public Map selectlistt(){
        Map map = new HashMap();
        if(map !=null) {
            map.put("code",0);
            map.put("message","0");
            List<Videodata> videodata = videodataService.selectlist();//查询视频数据表所有数据
            map.put("data",videodata);
        }else{
            map.put("code",400);
            map.put("message","数据为空");
            map.put("data",null);
        }

        return map;
    }

//    @GetMapping("/aaa")
//    public List lis(){
//        Integer bvid=1;
//        //随机数
//        Random random = new Random();
//        int pagesum = random.nextInt(100);
////        List<Video> videolist = videoService.selecvideolistpa(bvid,pagesum);   //随机查询20条不等于当前视频id的数据
//        List list=  new ArrayList();
//        list.add(pagesum);
//        return list;
//    }

    /**
     * 视频显示
     * @param bvid
     * @return
     */
    @GetMapping("/video/info")
    public Videodatainfo videodatabvidobject(Integer bvid){
        Integer uid=1; //当前用户id
        Videodatainfo videodatainfo = new Videodatainfo();
        Videodata videodata = videodataService.selectbvID(bvid);    //视频显示对象
        videodatainfo.setAid(videodata.getBvID());  //视频id
        Statinfo statinfo = new Statinfo();
            //随机数
            Random random = new Random();
            int pagesum = random.nextInt(100);
            List<Video> videolist = videoService.selecvideolistpa(bvid,pagesum);   //随机查询20条不等于当前视频id的数据
            List<Relatedinfo> relatedinfoList = new ArrayList<>();
            for(Video vide : videolist){
                Relatedinfo relatedinfo = new Relatedinfo();
                Videodata videodata2 = videodataService.selectbvID(vide.getBvID());   //根据视频id查询视频显示数据

                    relatedinfo.setAid(vide.getBvID());   //视频id
                    relatedinfo.setPic(vide.getBvCoverImgPath());   //图片
                    relatedinfo.setTitle(vide.getBvTitle()); //标题
                if(videodata2!=null){
                    statinfo.setView(videodata2.getBvPlayNum());
                    relatedinfo.setStatinfo(statinfo);  //播放量
                }else{
                    relatedinfo.setStatinfo(null);  //播放量
                }
                relatedinfoList.add(relatedinfo);

            }
        videodatainfo.setRelatedinfo(relatedinfoList);         //视频推荐数组
            Videodata videodata1 = videodataService.selectbvID(bvid);   //根据视频id查询视频显示数据

            statinfo.setView(videodata1.getBvPlayNum()); //播放量
            statinfo.setFavorite(videodata1.getBvFavoriteNum()); //收藏数
            statinfo.setCoin(videodata1.getBvCoinNum()); //投币数
            statinfo.setLike(videodata1.getBvLikeNum()); //点赞数
        videodatainfo.setStatinfo(statinfo);        //对象集合
            Video video = videoService.videobvIDlist(bvid);
            List<Videorating> videoratings = videoratingService.selectvideolist();  //查询所有视频评分数据按评分排序
            int a=0;
            int i=0;
            for(Videorating  videorating : videoratings){
                a+=1;
                if(videorating.getBvID()==bvid){
                     i=a;
                    break;
                }
            }
            Videoinfo videoinfo = new Videoinfo();
            videoinfo.setId(video.getBvID());       //视频id
            videoinfo.setTitle(video.getBvTitle()); //标题
            videoinfo.setDesc(video.getBvDesc());      //简介
            videoinfo.setCtime(video.getBvPostTime());     //发表时间
            videoinfo.setRank(i);       //排名
        videodatainfo.setVideoinfo(videoinfo);       //视频对象

        return videodatainfo;
    }





}

