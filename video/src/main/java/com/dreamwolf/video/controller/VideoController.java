package com.dreamwolf.video.controller;


import com.dreamwolf.member.business.entity.web_interface.OwnerInfo;
import com.dreamwolf.video.entity.web_interface.ArchivesInfo;
import com.dreamwolf.video.entity.web_interface.Region;
import com.dreamwolf.video.entity.web_interface.Result;
import com.dreamwolf.video.entity.web_interface.Statinfo;
import com.dreamwolf.video.pojo.Video;
import com.dreamwolf.video.pojo.Videodata;
import com.dreamwolf.video.pojo.Videorating;
import com.dreamwolf.video.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
            Map vimap = new HashMap();
            vimap.put("item_id",video.getBvID());    //视频id
            vimap.put("type",video.getBvID());   //类型
            vimap.put("title",video.getBvTitle());  //标题
            vimap.put("desc",video.getBvDesc());     //描述
            vimap.put("image",video.getBvCoverImgPath());    //封面图
            vimap.put("uri",video.getBvVideoPath());     //链接
            vimap.put("ctime",video.getBvPostTime());      //发表视频时间
            map.put("data",vimap);
        }else{
            map.put("code",400);
            map.put("message","传入的参数(bvID)不能为空");
            map.put("data",null);
        }
        return map;
    }

    //通过作者id查视频，返回list
    @GetMapping("/videouID")
    public List<Integer> videouID(Integer uID){
        return videoService.videouIDlist(uID);
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

    /**
     * 根据时间查找子分区id和子分区id的总数
     * @param str
     * @return
     */
    @GetMapping("/videoseldate")
    public Map selmap(String str){
        List listmap = new ArrayList();
//        String str="2021-04-21";
        List<Video> list = videoService.selectcoutbvid(str);
        Map<String, Object> kele=new HashMap<String, Object>();
        for (Video st : list) {
            kele.put(st.getBvChild().toString(),st.getCountbv());
        }
        return kele;
    }


    @GetMapping("/videoBvidlist")
    public List<Map<String,Object>> selectbbid(Integer[] bvidlist){
        List listmap = new ArrayList();
        List<Video> list = videoService.selectlistBvid(bvidlist);

            for(Video video : list){
                Map videomap = new HashMap();
                videomap.put("title",video.getBvTitle());   //标题
                videomap.put("long_title","");
                videomap.put("bvid",video.getBvID());
                videomap.put("cover",video.getBvCoverImgPath());  //封面
                videomap.put("uid",video.getUID());   //作者id
                videomap.put("uri",video.getBvVideoPath()); //路径
                videomap.put("duration",video.getDuration());  //总时长
                listmap.add(videomap);
            }

        return listmap;

    }


    /**
     * 根据视频id数组批量查询视频数据
     * @param array
     * @return
     */
    @GetMapping("/videobvidli")
    public List<Video> selectbvidlist(Integer[] array){
        return videoService.selectlistBvid(array);
    }

    /**
     * 通过bvid查询对象
     * @return
     */
    @GetMapping("/videobvidlists")
    public Map<String,Video> videobvidlists(Integer bvid){
        Map map = new HashMap();
        Video video = videoService.videobvIDlist(bvid);
        map.put("data",video);
        return map;
    }

    /**
     * 根据子分区id查询最新的4条数据
     * @return
     */
    @GetMapping("/videoridlists")
    public List<ArchivesInfo> selectvideorid(Integer rid,Integer pn,Integer ps){
//        Integer rid = 310;
        List list = new ArrayList();
        List<Video> videoList = videoService.videoliselectridlist(rid,pn,ps); //查询最新的4条记录
        for(Video video : videoList){
            ArchivesInfo archivesInfo = new ArchivesInfo();
            archivesInfo.setAid(video.getBvID());   //视频id
            archivesInfo.setBvid("bv"+video.getBvID()); //bv号
            archivesInfo.setCtime(video.getBvPostTime());   //发表时间
            archivesInfo.setDesc(video.getBvDesc());    //视频文章
            archivesInfo.setDuration(video.getDuration());  //时长
            archivesInfo.setPic(video.getBvCoverImgPath()); //图片
            archivesInfo.setTitle(video.getBvTitle());  //标题
            OwnerInfo ownerInfo = usermapService.OwnerInfo(video.getUID());
            archivesInfo.setOwner(ownerInfo);   //用户信息
            String name= userpageService.elementby(video.getBvChildZoning());
            archivesInfo.setTname(name);    //分区名
                Statinfo statinfo = new Statinfo();
                Videodata videodata = videodataService.selectbvID(video.getBvID()); //根据视频id查询数据
            if(videodata!=null){
                statinfo.setAid(videodata.getBvID());   //视频id
                statinfo.setCoin(videodata.getBvCoinNum());
                statinfo.setFavorite(videodata.getBvFavoriteNum());
                statinfo.setLike(videodata.getBvLikeNum()); //点赞数
                statinfo.setDislike(videodata.getBvDislike());  //点踩
                statinfo.setReply(videodata.getBvCommentNum());
                statinfo.setShare(videodata.getBvRetweetNum());
                statinfo.setView(videodata.getBvPlayNum());
                List<Videorating> videoratings = videoratingService.selectvideolist();  //查询所有视频评分数据按评分排序
                int a=0;
                int i=0;
                for(Videorating  videorating : videoratings){
                    a+=1;
                    if(videorating.getBvID()==video.getBvID()){
                        i=a;
                        break;
                    }
                }
                statinfo.setHis_rank(i);    //排名
                archivesInfo.setStatinfo(statinfo);
            }else{
                archivesInfo.setStatinfo(null);
            }
            list.add(archivesInfo);

        }

        return list;
    }


    /**
     * 根据子分区id查询最新的数据并分页处理
     * @return
     */
    @GetMapping("/selectlistvieopage")
    public List<ArchivesInfo> selectlistvieopage(Integer rid,Integer pn,Integer ps){
//        Integer rid = 310;
//        Integer pn=0;
//        Integer ps=20;
        List list = new ArrayList();
        List<Video> videoList = videoService.videolistselectpage(rid,pn,ps);
        for(Video video : videoList){
            ArchivesInfo archivesInfo = new ArchivesInfo();
            archivesInfo.setAid(video.getBvID());   //视频id
            archivesInfo.setBvid("bv"+video.getBvID()); //bv号
            archivesInfo.setCtime(video.getBvPostTime());   //发表时间
            archivesInfo.setDesc(video.getBvDesc());    //视频文章
            archivesInfo.setDuration(video.getDuration());  //时长
            archivesInfo.setPic(video.getBvCoverImgPath()); //图片
            archivesInfo.setTitle(video.getBvTitle());  //标题
            OwnerInfo ownerInfo = usermapService.OwnerInfo(video.getUID());
            archivesInfo.setOwner(ownerInfo);   //用户信息
            String name= userpageService.elementby(video.getBvChildZoning());
            archivesInfo.setTname(name);    //分区名
            Statinfo statinfo = new Statinfo();
            Videodata videodata = videodataService.selectbvID(video.getBvID()); //根据视频id查询数据
            if(videodata!=null){
                statinfo.setAid(videodata.getBvID());   //视频id
                statinfo.setCoin(videodata.getBvCoinNum());
                statinfo.setFavorite(videodata.getBvFavoriteNum());
                statinfo.setLike(videodata.getBvLikeNum()); //点赞数
                statinfo.setDislike(videodata.getBvDislike());  //点踩
                statinfo.setReply(videodata.getBvCommentNum());
                statinfo.setShare(videodata.getBvRetweetNum());
                statinfo.setView(videodata.getBvPlayNum());
                List<Videorating> videoratings = videoratingService.selectvideolist();  //查询所有视频评分数据按评分排序
                int a=0;
                int i=0;
                for(Videorating  videorating : videoratings){
                    a+=1;
                    if(videorating.getBvID()==video.getBvID()){
                        i=a;
                        break;
                    }
                }
                statinfo.setHis_rank(i);    //排名
                archivesInfo.setStatinfo(statinfo);
            }else{
                archivesInfo.setStatinfo(null);
            }
            list.add(archivesInfo);

        }

        return list;
    }



    /**
     * 视频对象集合按热度查询排序
     * @param rid
     * @return
     */
    @GetMapping("/selectbvidlistpage")
    public List<Result> selectbvidlistpage(Integer rid){
        Integer pn =0;
        Integer ps= 20;
        List<Integer> array = videoService.videoselezoingid(rid); //根据子分区查询视频id
        List<Videodata> arraydata = videodataService.videodatabvid(array.toArray(new Integer[0]));//按热度查
        List<Integer> arrayvideo = new ArrayList();  //按热度排序后的视频id
        for(Videodata videodata : arraydata){
            arrayvideo.add(videodata.getBvID());
        }
        List<Video> videoList = videoService.selectvideovlidlsit(arrayvideo.toArray(new Integer[0]),pn,ps);
        List list = new ArrayList();
        for(Video video : videoList){
            Result result = new Result();
            OwnerInfo ownerInfo = usermapService.OwnerInfo(video.getUID());
            String uname = ownerInfo.getName();
            result.setAuthor(uname); //作者名称
            result.setBvid(video.getBvID());      //视频id
            result.setDescription(video.getBvDesc());   //文章
            result.setDuration(video.getDuration());    //时长
            Videodata videodata = videodataService.selectbvID(video.getBvID()); //根据视频id查询视频数据
            result.setFavorites(videodata.getBvFavoriteNum());  //收藏数
            result.setMid(video.getUID());  //作者id
            result.setPic(video.getBvCoverImgPath());   //预览图
            result.setPlay(videodata.getBvPlayNum());   //观看数
            result.setPubdate(video.getBvPostTime());   //发表时间
            result.setReview(videodata.getBvCommentNum());  //评论数
            result.setTitle(video.getBvTitle());    //标题
            String name= userpageService.elementby(video.getBvChildZoning());
            result.setType(name);   //视频类型
            list.add(result);
        }
        return list;
    }




    /**
     * 视频对象集合按热度查询排序查询前10条
     * @param rid
     * @return
     */
    @GetMapping("/selectbvidlistpagelist")
    public List<Result> selectbvidlistpagelist(Integer rid){
        List<Integer> array = videoService.videoselezoingid(rid); //根据子分区查询视频id
        List<Videodata> arraydata = videodataService.videodatabvid(array.toArray(new Integer[0]));//按热度查
        List<Integer> arrayvideo = new ArrayList();  //按热度排序后的视频id
        for(Videodata videodata : arraydata){
            arrayvideo.add(videodata.getBvID());
        }
        //查询前10条
        List<Video> videoList = videoService.selectlispagelsit(arrayvideo.toArray(new Integer[0]));
        List list = new ArrayList();
        for(Video video : videoList){
            Videodata videodata = videodataService.selectbvID(video.getBvID()); //根据视频id查询视频数据
            Region result = new Region();
            result.setAuthor(null); //作者名称
            result.setAid(video.getBvID());      //视频id
            result.setBvid("bv"+video.getBvID());   //bv号
            result.setCoins(videodata.getBvCoinNum());  //投币数
            result.setDescription(video.getBvDesc());   //文章
            result.setDuration(video.getDuration());    //时长
            result.setFavorites(videodata.getBvFavoriteNum());  //收藏数
            result.setMid(video.getUID());  //作者id
            result.setPic(video.getBvCoverImgPath());   //预览图
            result.setPlay(videodata.getBvPlayNum());   //观看数
            Videorating videorating = videoratingService.selectbvid(video.getBvID());
            result.setPts(videorating.getOverallRating());
            result.setCreate(video.getBvPostTime());   //发表时间
            result.setReview(videodata.getBvCommentNum());  //评论数
            result.setTitle(video.getBvTitle());    //标题
            result.setTypename(null);   //视频类型
            list.add(result);
        }
        return list;
    }








}

