package com.dreamwolf.video.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.fav.Userfavoritelist;
import com.dreamwolf.entity.member.web_interface.OwnerInfo;
import com.dreamwolf.entity.member.web_interface.VideoinfoOwnerInfo;
import com.dreamwolf.entity.video.*;
import com.dreamwolf.entity.video.web_interface.*;
import com.dreamwolf.entity.zoning.web_interface.Deputydivision;
import com.dreamwolf.entity.zoning.web_interface.Mainpartition;
import com.dreamwolf.safety.util.TokenUtil;
import com.dreamwolf.video.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    private UsermapService usermapService;

    @Resource
    private UserpageService userpageService;

    @Resource
    SafetyService safetyService;

    @Resource
    private FavService favService;

    @Resource
    private VideolikeService videolikeService;

    @Resource
    private VideofavoriteService videofavoriteService;

    @Resource
    private WathHisService wathHisService;

    /**
     * 观看数+1
     * @param bvid
     * @param request
     * @return
     */
    @GetMapping("/insetuidbvid")
    public ResponseData insetuidbvid(Integer bvid,HttpServletRequest request){

        ResponseData<Integer> logon_uid_result = safetyService.logon_uid(TokenUtil.getToken(request));
        if(logon_uid_result.getCode()==0){
            Integer result = videodataService.insertuidbvidlotime(bvid); //播放书+1
            ResponseData<Integer> result2=wathHisService.insertupdate(logon_uid_result.getData(),bvid); //存在就修改，不存在就添加
           int i=0;
            if(result>0 && result2.getData()>0){
                i=1;
            }else {
                i=0;
            }
            return new ResponseData(0,"",0,i);
        }else {

        return new ResponseData<>(logon_uid_result.getCode(), logon_uid_result.getMessage(), 1, null);

        }
    }

    @GetMapping(value = "/videodatabvID")
    @SentinelResource(value = "fallback", fallback = "handlerFallback")
    public ResponseData selectdvid(Integer bvID){
        Videodata videodata = videodataService.selectbvID(bvID); //根据bvID查询的视频基本数据
        return new ResponseData(0,"",0,videodata);
    }

    public ResponseData handlerFallback(Integer bvID, Throwable e) {
        Map map = new HashMap();
        map.put("444","[业务异常兜底降级方法],exception内容:  " + e.getMessage());
        return new ResponseData(0,"",0,map);
    }

    @GetMapping(value = "/videodatalist")
    public ResponseData selectlistt(){
        Map map = new HashMap();
        List<Videodata> videodata = new ArrayList<>();
        if(map !=null) {
            map.put("code",0);
            map.put("message","0");
            videodata = videodataService.selectlist();//查询视频数据表所有数据
        }else{
            map.put("code",400);
            map.put("message","数据为空");
            map.put("data",null);
        }

        return new ResponseData(0,"",0,videodata);
    }


    /**
     * 视频显示
     * @param bvid
     * @return
     */
    @GetMapping("/info")
    public ResponseData<VideoDataMap> videodatabvidobject(Integer bvid, HttpServletRequest request){
        ResponseData<Integer> logon_uid_result = safetyService.logon_uid(TokenUtil.getToken(request));
        if(logon_uid_result.getCode()==0){

//        Integer uid=1; //当前用户id
//        Videodatainfo videodatainfo = null;
//        Videodata videodata = videodataService.selectbvID(bvid);    //视频显示对象
        Videodatainfo  videodatainfo = new Videodatainfo();
            videodatainfo.setAid(bvid);  //视频id
            Statinfo statinfo = new Statinfo();
            Videolike vlike = videolikeService.selestatusuid(bvid,logon_uid_result.getData());
            boolean isliked = false;
            if(vlike!=null){
                isliked=true;
            }else{
                isliked = false;
            }
            videodatainfo.setLiked(isliked); //是否点赞

            boolean isCollected=false;
            ResponseData<List<Userfavoritelist>> favlist = favService.selecrfavuid(logon_uid_result.getData());
            Videofavorite vfav = videofavoriteService.selectfavbvid(bvid);
            for(Userfavoritelist f : favlist.getData()){
                if(f.getFavListID() == vfav.getFavListID()){
                    isCollected=true;
                    break;
                }
            }
            videodatainfo.setCollected(isCollected); //是否收藏
            ResponseData<Boolean> coined=usermapService.whethercoin(logon_uid_result.getData(),bvid);
            videodatainfo.setCoined(coined.getData());//是否投币
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
                ResponseData<OwnerInfo> ownerInfo = usermapService.OwnerInfo(vide.getUID());
                    relatedinfo.setOwner(ownerInfo.getData()); //用户对象
                if(videodata2!=null){
                    statinfo.setView(videodata2.getBvPlayNum());
                    relatedinfo.setStat(statinfo);  //播放量
                }else{
                    relatedinfo.setStat(new Statinfo());  //播放量
                }
                relatedinfoList.add(relatedinfo);

            }
            videodatainfo.setRelated(relatedinfoList);         //视频推荐数组
            Videodata videodata1 = videodataService.selectbvID(bvid);   //根据视频id查询视频显示数据
            if(videodata1!=null){
            statinfo.setView(videodata1.getBvPlayNum()); //播放量
            statinfo.setFavorite(videodata1.getBvFavoriteNum()); //收藏数
            statinfo.setCoin(videodata1.getBvCoinNum()); //投币数
            statinfo.setLike(videodata1.getBvLikeNum()); //点赞数
            videodatainfo.setStat(statinfo);        //对象集合

            }else {
                Statinfo statinfo2 = new Statinfo();
                videodatainfo.setStat(statinfo2);
            }
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
            videoinfo.setPath(video.getBvVideoPath());//路径
            videoinfo.setRank(i);       //排名
        videodatainfo.setVideo(videoinfo);       //视频对象
        ResponseData<VideoinfoOwnerInfo> videoinfoOwnerInfo= usermapService.video_info(logon_uid_result.getData(),video.getUID());  //当前用户id,用户id
        videodatainfo.setOwner(videoinfoOwnerInfo.getData());   //用户对象
        ResponseData<Mainpartition> mainpartition =userpageService.mainpartition(video.getBvChildZoning());
        videodatainfo.setMainpartition(mainpartition.getData());
        ResponseData<Deputydivision> deputydivision =userpageService.deputydivision(video.getBvChildZoning());
        videodatainfo.setDeputydivision(deputydivision.getData());

        VideoDataMap videoDataMap = new VideoDataMap(videodatainfo);


        return new ResponseData(0,"",0,videoDataMap);

        }else {
            return new ResponseData<>(logon_uid_result.getCode(), logon_uid_result.getMessage(), 1, null);
        }
    }

    /**
     * 修改硬币数 硬币数=硬币数+CoinNum
     * @param bvid
     * @param CoinNum 传过来的硬币数
     * @return
     */
    @PostMapping("/updatebvCoinNum")
    public ResponseData updatebvCoinNum(Integer bvid,Integer CoinNum){
        int result = videodataService.updatebvCoinNum(bvid,CoinNum);
        return new ResponseData(0,"",1,result);
    }

    /**
     * 修改视频数据表的点赞数据+1
     * @param bvid
     * @return
     */
    @PostMapping("/updateinse")
    public ResponseData updateinse(Integer bvid){
        int result = videodataService.updateinse(bvid);
        return new ResponseData(0,"",1,result);
    }




}

