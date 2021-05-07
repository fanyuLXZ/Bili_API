package com.dreamwolf.video.controller;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.User;
import com.dreamwolf.entity.member.web_interface.OwnerInfo;
import com.dreamwolf.entity.video.Video;
import com.dreamwolf.entity.video.Videodata;
import com.dreamwolf.entity.video.Videorating;
import com.dreamwolf.entity.video.web_interface.*;
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
    public ResponseData videobvID(Integer bvID){
        Video video = videoService.videobvIDlist(bvID);
        String name= userpageService.elementby(video.getBvChildZoning());
        VideoMap videoMap = new VideoMap(video.getBvID(),name,video.getBvTitle(), video.getBvDesc(),video.getBvCoverImgPath(),video.getBvVideoPath(),video.getBvPostTime());
        return new ResponseData(0,"",0,videoMap);
    }

    //通过作者id查视频，返回list
    @GetMapping("/videouID")
    public ResponseData<VideoList> videouID(Integer uID){
        List<Integer> listint = videoService.videouIDlist(uID);
        VideoList videoList = new VideoList(listint);
        return new ResponseData(0,"",0,videoList);
    }

    //查video的所有数据
    @GetMapping("/videolist")
    public ResponseData videoselectlist(){
        List<Video> list = videoService.selectlist();
        VideoList videoList = new VideoList(list);
        return new ResponseData(0,"",0,videoList);
    }

    //根据子分区集合查询视频数据并分页处理  参数：
//    bvChildZoninglist 子分区集合
//    pageSize 从第几页开始
//    pagecount 一页显示多少条
    @GetMapping("/videopage")
    public ResponseData<List<VideoMaplist>> selectpage(Integer[] list,Integer ps,Integer count){
            //子分区集合查询视频数据 返回的分页集合
            List<Video> videolist = videoService.videoPagebvzoing(list,ps,count);
            List<VideoMaplist> listma = new ArrayList<>();
                for (Video vid : videolist) {
                    User usermap = usermapService.userid(vid.getUID()).getData();
                    OwnerInfo owner = new OwnerInfo(usermap.getuID(),usermap.getUserName(),null);
//                    根据bvid查询的视频基本数据（硬币，点赞数等）
                    Videodata videodata = videodataService.selectbvID(vid.getBvID());
                    StatMap stat=null;
                    if(videodata != null){
                        stat = new StatMap(videodata.getBvID(),videodata.getBvCoinNum(),videodata.getBvPopupsNum(),
                                videodata.getBvLikeNum());
                    }else {
                        stat = new StatMap(null,null,null,null);
                    }

                    VideoMaplist videoMaplist = new VideoMaplist(vid.getBvID(),vid.getBvCoverImgPath(),null,null,null,
                            vid.getBvTitle(),owner,stat,vid.getDuration(),"bv" + vid.getBvID(),0,null,null);
                    listma.add(videoMaplist);
                }
//        VideoList videoList = new VideoList(listma);
        return  new ResponseData(0,"",0,listma);
    }

    /**
     * 返回根据子分区查找的视频总数
     * @param
     * @return
     */
    @GetMapping("/videocount")
    public ResponseData selcount(Integer[] list){
        Integer count = videoService.videocount(list);
        VideoCount videoCount = new VideoCount(count);
        return new ResponseData(0,"",0,videoCount);
    }


    /**
     *  视频集合对象
     * @param bvChildZoning 子分区id
     * @return
     */
    @GetMapping("/videodeorating")
    public ResponseData<List> selectdeorating(Integer[] bvChildZoning, Integer datetime){

        Calendar  cr = Calendar.getInstance();
        int year=cr.get(Calendar.YEAR); //年
        int month = (cr.get(Calendar.MONTH)) + 1;//月
        int day = cr.get(Calendar.DAY_OF_MONTH);//天
        int a = day-datetime; //减去传过来的时间
        int b =day+1;
        String qian=year+"-"+month+"-"+a;
        String hou=year+"-"+month+"-"+b;
            List<Video> list = videoService.videoZoningIdlist(bvChildZoning,qian,hou);
                List listmap = new ArrayList();
                VideoMaplist videoMaplist =null;
                if(list!=null){
                    for(Video video : list){
                        videoMaplist = new VideoMaplist();
                        videoMaplist.setAid(video.getBvID());
                        videoMaplist.setPic(video.getBvCoverImgPath());
                        videoMaplist.setBvid("bv"+video.getBvID());
                        videoMaplist.setTitle(video.getBvTitle());
                        String cname=userpageService.elementby(video.getBvChildZoning());
                        videoMaplist.setTypename(cname);
                        Videorating videorating = videoratingService.selectbvid(video.getBvID());
                        if(videorating!=null){
                            videoMaplist.setPts(videorating.getOverallRating());
                        }else {
                            videoMaplist.setPts(0);
                        }

                        listmap.add(videoMaplist);
                    }
                }else {
                    videoMaplist = new VideoMaplist();
                    listmap.add(videoMaplist);
                }
//                VideoList videoList = new VideoList(listmap);

        return new ResponseData(0,"",0,listmap);
    }

    /**
     * 根据时间查找子分区id和子分区id的总数
     * @param str
     * @return
     */
    @GetMapping("/videoseldate")
    public ResponseData selmap(String str){
        List listmap = new ArrayList();
//        String str="2021-04-21";
        List<Video> list = videoService.selectcoutbvid(str);
        Map<String, Object> kele=new HashMap<String, Object>();
        for (Video st : list) {
            kele.put(st.getBvChild().toString(),st.getCountbv());
        }

        return new ResponseData(0,"",0,kele);
    }


    @GetMapping("/videoBvidlist")
    public ResponseData<List<VideoMaplist>> selectbbid(Integer[] bvidlist){
        List<VideoMaplist> listmap = new ArrayList();
        List<Video> list = videoService.selectlistBvid(bvidlist);
            for(Video video : list){
                VideoMaplist videoMaplist = new VideoMaplist(null,null,video.getBvCoverImgPath(),
                        video.getUID(),video.getBvVideoPath(),video.getBvTitle(),null,null,video.getDuration(),
                        null,0,null,null
                );
                listmap.add(videoMaplist);
            }
//        List list1 = new ArrayList(listmap);
        return new ResponseData(0,"",0,listmap);

    }


    /**
     * 根据视频id数组批量查询视频数据
     * @param array
     * @return
     */
    @GetMapping("/videobvidli")
    public ResponseData<List<Video>> selectbvidlist(Integer[] array){
        List<Video> lis = videoService.selectlistBvid(array);
//        VideoList videoList = new VideoList(lis);
        return new ResponseData(0,"",0,lis);
    }

    /**
     * 通过bvid查询对象
     * @return
     */
    @GetMapping("/videobvidlists")
    public ResponseData<Video> videobvidlists(Integer bvid){
        Video video = videoService.videobvIDlist(bvid);
        return new ResponseData(0,"",0,video);
    }

    /**
     * 根据子分区id查询最新的4条数据
     * @return
     */
    @GetMapping("/videoridlists")
    public ResponseData<List<ArchivesInfo>> selectvideorid(Integer rid, Integer pn, Integer ps){
//        Integer rid = 310;
        List<ArchivesInfo> list = new ArrayList();
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
            ResponseData<OwnerInfo> ownerInfo = usermapService.OwnerInfo(video.getUID());
            archivesInfo.setOwner(ownerInfo.getData());   //用户信息
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
//        VideoList videoList1 = new VideoList(list);

        return new ResponseData(0,"",0,list);
    }


    /**
     * 根据子分区id查询最新的数据并分页处理
     * @return
     */
    @GetMapping("/selectlistvieopage")
    public ResponseData<List<ArchivesInfo>> selectlistvieopage(Integer rid,Integer pn,Integer ps){
//        Integer rid = 310;
//        Integer pn=0;
//        Integer ps=20;
        List<ArchivesInfo> list = new ArrayList();
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
            ResponseData<OwnerInfo> ownerInfo = usermapService.OwnerInfo(video.getUID());
            archivesInfo.setOwner(ownerInfo.getData());   //用户信息
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

        return new ResponseData(0,"",0,list);
    }



    /**
     * 视频对象集合按热度查询排序
     * @param rid
     * @return
     */
    @GetMapping("/selectbvidlistpagerid")
    public ResponseData<List<Result>> selectbvidlistpage(Integer rid,Integer pn,Integer ps){
//        Integer pn=0;
//        Integer ps= 20;
        List<Integer> array = videoService.videoselezoingid(rid); //根据子分区查询视频id
        List<Videodata> arraydata = videodataService.videodatabvid(array.toArray(new Integer[0]));//按热度查
        Result result = null;
        List list = new ArrayList();
        if(arraydata.size()>0){
            List<Integer> arrayvideo = new ArrayList();  //按热度排序后的视频id
            for(Videodata videodata : arraydata){
                arrayvideo.add(videodata.getBvID());
            }
            List<Video> videoList =null;
            if(arrayvideo!=null){
                videoList= videoService.selectvideovlidlsit(arrayvideo.toArray(new Integer[0]),pn,ps);
            }
            for(Video video : videoList){
                result = new Result();
                ResponseData<OwnerInfo> ownerInfo = usermapService.OwnerInfo(video.getUID()); //用户对象
                String uname = ownerInfo.getData().getName();
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
        }else{
            list.add(null);
        }

//        VideoList videoList1 = new VideoList(list);

        return new ResponseData(0,"",0,list);
    }




    /**
     * 视频对象集合按热度查询排序查询前10条
     * @param rid
     * @return
     */
    @GetMapping("/selectbvidlistpagelist")
    public ResponseData<List<Region>> selectbvidlistpagelist(Integer rid){
        List<Integer> array = videoService.videoselezoingid(rid); //根据子分区查询视频id
        List<Videodata> arraydata = videodataService.videodatabvid(array.toArray(new Integer[0]));//按热度查
        List<Region> list = new ArrayList();
        Region result= null;
        if(arraydata.size()>0){
            List<Integer> arrayvideo = new ArrayList();  //按热度排序后的视频id
            for(Videodata videodata : arraydata){
                arrayvideo.add(videodata.getBvID());
            }
            //查询前10条
            List<Video> videoList = videoService.selectlispagelsit(arrayvideo.toArray(new Integer[0]));

            for(Video video : videoList){
                Videodata videodata = videodataService.selectbvID(video.getBvID()); //根据视频id查询视频数据
                result= new Region();
                ResponseData<OwnerInfo> ownerInfo = usermapService.OwnerInfo(video.getUID());
                String uname = ownerInfo.getData().getName();
                result.setAuthor(uname); //作者名称
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
                String name= userpageService.elementby(video.getBvChildZoning());
                result.setTypename(name);   //视频类型
                list.add(result);
            }
        }else {
            list.add(null);
        }

//        VideoList videoList1 = new VideoList(list);
        return new ResponseData(0,"",0,list);
    }

    /**
     * 根据子分区id查询视频总数
     * @param rid
     * @return
     */
    @GetMapping("/selectidcoutn")
    public Integer selectidcoutn(Integer rid){
        Integer count =videoService.selectridcount(rid);
//        VideoCount videoCount = new VideoCount(count);
        return count;
    }





}

