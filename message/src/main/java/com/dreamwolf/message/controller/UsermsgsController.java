package com.dreamwolf.message.controller;

import com.dreamwolf.entity.comment.Comment;
import com.dreamwolf.entity.comment.Commentlike;
import com.dreamwolf.entity.message.Usermsgs;
import com.dreamwolf.entity.video.Video;
import com.dreamwolf.entity.video.Videocomment;
import com.dreamwolf.entity.video.Videolike;
import com.dreamwolf.message.service.*;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户消息表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-23
 */
@RestController
public class UsermsgsController {

    @Resource
    private UsermsgsService usermsgsService;

    @Resource
    private VideoService videoService;

    @Resource
    private CommentService commentService;

    @Resource
    private MemberService memberService;

    @Resource
    private DynamicService dynamicService;

    @GetMapping("/usermsgsfid")
    public Map<String, Object> selectlistfid(Integer fid){
        Map<String, Object> map = new HashMap<>();
        List<Usermsgs> usermsgsList = usermsgsService.selectusmsgsfid(fid);
        if(map !=null && usermsgsList !=null && fid !=null) {
            map.put("data", usermsgsList);
        }else {
            map.put("code",400);
            map.put("message","map为空或list为空或fid为空");
            map.put("data", null);
        }
        return map;
    }

    @GetMapping("/usermsguid")
    public Map<String, Object> selectlistuid(Integer uid){
        Map<String, Object> map = new HashMap<>();
        List<Usermsgs> usermsgsList = usermsgsService.selectusmsgsuid(uid);
        if(map !=null && usermsgsList !=null && uid !=null) {
            map.put("data", usermsgsList);
        }else {
            map.put("code",400);
            map.put("message","map为空或list为空或uid为空");
            map.put("data", null);
        }
        return map;
    }


    //收到的点赞
    @GetMapping("/like")
    public Map selectmeslist(){
        Map datamap = new HashMap();
        if(datamap!=null){
            Map map = new HashMap();
            Map total = new HashMap();
            Integer uid = 1; //当前用户id
            List<Integer> bv_ids = videoService.videouID(uid);
            List<Videolike> video_like_list = videoService.sellist(bv_ids.toArray(new Integer[0]));  //根据视频id数组查询点赞表
            //根据视频id数组批量查询视频数据
            List<Video> videos = videoService.selectbvidlist(bv_ids.toArray(new Integer[0]));
            List<Integer> comarr = commentService.selectuidlist(uid); //根据用户id拿到用户下的评论
            List<Commentlike> commentlikelist = commentService.selectarrlist(comarr.toArray(new Integer[0])); //根据评论id数组拿到评论点赞的数据
            List<Comment> commentscidarr = commentService.commentsarrlist(comarr.toArray(new Integer[0]));//拿到评论id的数据
            List itemlist = new ArrayList();
            int i =1;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date last_like_time = null;
            for(Comment comm : commentscidarr){
                Map commap = new HashMap();
                commap.put("id",i++);
                List<Integer> user_ids2 = new ArrayList<>(); //用户id数组
                for (Commentlike commentslike:commentlikelist){ //遍历视频点赞表数据
                    if (commentslike.getCID().equals(comm.getCID())) {
                        last_like_time=commentslike.getCreateTime();
                        user_ids2.add(commentslike.getUID());
                    }
                }
                if (user_ids2.size()<=0){
                    continue;
                }
                List uselist =  memberService.users(user_ids2.toArray(new Integer[0]),uid);

                commap.put("users",uselist);//用户对象
                Map mlist = new HashMap();
                mlist.put("item_id", comm.getCID());    //评论id
                if(comm.getCID() ==comm.getCID()){
                    mlist.put("type","reply");   //类型video代表视频，dynamic代表动态 reply代表文字 String
                }
                mlist.put("title",comm.getCText());  //正文
                mlist.put("desc", null);     //描述
                mlist.put("image",null);    //封面图
                mlist.put("uri", null);     //链接
                mlist.put("ctime", simpleDateFormat.format(comm.getCreateTime()));
                commap.put("item",mlist);
                commap.put("counts", user_ids2.size());
                commap.put("like_time", simpleDateFormat.format(last_like_time));    //最新点赞的时间
                itemlist.add(commap);

            }
            for(Video video:videos){
                Map itemObject = new HashMap();
                itemObject.put("id",i++);

                List<Integer> user_ids = new ArrayList<>(); //用户id数组
                for (Videolike videolike:video_like_list){ //遍历视频点赞表数据
                    if (videolike.getBvID().equals(video.getBvID())) {
                        last_like_time=videolike.getCreateTime();
                        user_ids.add(videolike.getUID());
                    }
                }
                List uselist2 =  memberService.users(user_ids.toArray(new Integer[0]),uid);
                if (uselist2.size()<=0){
                    continue;
                }
                itemObject.put("users",uselist2);//用户对象
                Map mlist = new HashMap();
                mlist.put("item_id", video.getBvID());    //视频id
                if(video.getBvID() ==video.getBvID()){
                    mlist.put("type","video");   //类型video代表视频，dynamic代表动态 reply代表文字 String
                }
                mlist.put("title",video.getBvTitle());  //标题
                mlist.put("desc", video.getBvDesc());     //描述
                mlist.put("image",video.getBvCoverImgPath());    //封面图
                mlist.put("uri", video.getBvVideoPath());     //链接
                mlist.put("ctime", simpleDateFormat.format(video.getBvPostTime()));   //发表视频时间
                itemObject.put("item",mlist);
                itemObject.put("counts", user_ids.size());      //此评论/视频/动态的总人数
                itemObject.put("like_time", simpleDateFormat.format(last_like_time));    //最新点赞的时间
                itemlist.add(itemObject);
            }
            //动态
            List<Map<String,Object>> dylist  =dynamicService.likeitems(uid);
            for(Map<String,Object> limap :dylist ){
                Map slp = new HashMap();
                slp.put("id",i++);
                slp.put("users",limap.get("users"));
                slp.put("item",limap.get("item"));
                slp.put("counts",limap.get("counts"));
                slp.put("like_time",limap.get("like_time"));
                itemlist.add(slp);
            }
            //把itemlist按照最大时间在前排序
//            Collections.sort(itemlist, new Comparator<Map<String,Object>>(){
//                public int compare(Map<String,Object> o1, Map<String,Object> o2) {
//                    String name1 =o1.get("like_time").toString();//name1是从你list里面拿出来的一个
//                    String name2= o2.get("like_time").toString(); //name1是从你list里面拿出来的第二个name
//                    return name2.compareTo(name1);
//                }
//            });

            total.put("items",itemlist);
            map.put("total",total);
            datamap.put("data",map);
            datamap.put("code",0);
            datamap.put("message",null);
        }else {
            datamap.put("data",null);
            datamap.put("code",400);
            datamap.put("message","data为空");
        }
        return datamap;
    }


    //回复我的
    @GetMapping("/reply")
    public Map usermsglist(){
        Map mmdata = new HashMap();
        if(mmdata!=null){
            Map map = new HashMap();
            Integer uid = 1;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            List<Integer> videoarr = videoService.videouID(uid);    //根据用户id拿到所有视频 视频id数组
            //根据视频id数组拿到视频下面的评论id
            List<Integer> videocommmarr = videoService.selectarr(videoarr.toArray(new Integer[0]));
            //根据视频id数组拿到视频评论表所有数据
            List<Videocomment> bvidcomm =videoService.videocombvidlist(videoarr.toArray(new Integer[0]));
            List<Comment> comments = commentService.selecomuid(uid);    //根据uid查询发布的评论
            List listitem = new ArrayList();
            for(Comment comme_nlist : comments){
                Map itemmaps = new HashMap();
                Map comm_map = new HashMap();
                List<Comment> comlsit = commentService.selecomcid(comme_nlist.getCIDreply()); //根据comments的回复id来查
                Integer userid=0;
                for(Comment clist : comlsit){
                    comm_map.put("source_content", clist.getCText());   //被回复的评论内容
                    if (comme_nlist.getCID() == comme_nlist.getCID()) {
                        comm_map.put("type", "reply");  //video代表视频，dynamic代表动态 reply代表文字
                    }
                    comm_map.put("business", "评论");
                    comm_map.put("title", comme_nlist.getCText());        //被回复的评论 视频则为空
                    comm_map.put("reply_time",simpleDateFormat.format(clist.getCreateTime()));    //当前时间
                    comm_map.put("uri", null);
                    comm_map.put("image", null);   //当前 视频，动态的封面
                    comm_map.put("native_uri", null);
                    userid=clist.getUID();
                }
                Map<String,Object> usemap = memberService.replyuserb(userid,uid);
                itemmaps.put("user",usemap);       //回复的用户对象
                itemmaps.put("item",comm_map);   //回复我的评论对象
                listitem.add(itemmaps);
            }


            //视频
            for(Videocomment vcom : bvidcomm){
                Map itemmap = new HashMap();
                Map videoitem = new HashMap();
                Map<String,Video> videomap = videoService.videobvidlists(vcom.getBvID());  //根据视频id查询视频数据
                Map<String,Map<String,Object>> commlistmap=commentService.commcidlist(vcom.getCID());
                    videoitem.put("source_content", commlistmap.get("data").get("source_content"));   //被回复的评论内容
                    if (vcom.getBvID() == vcom.getBvID()) {
                        videoitem.put("type", "video");  //video代表视频，dynamic代表动态 reply代表文字
                    }
                    videoitem.put("business", "视频");    //视频，动态，文字三个参数
                    videoitem.put("title", null);        //被回复的评论 视频则为空
                    videoitem.put("reply_time", commlistmap.get("data").get("reply_time"));    //当前时间
                    videoitem.put("uri", null);
                    videoitem.put("bvid", videomap.get("data").getBvID());
                    videoitem.put("image", videomap.get("data").getBvCoverImgPath());   //当前 视频，动态的封面
                    videoitem.put("native_uri", null);
                    Integer id= (Integer) commlistmap.get("data").get("uid");
                Map<String,Object> usemap = memberService.replyuserb(id,uid);
                itemmap.put("user",usemap);       //回复的用户对象
                itemmap.put("item",videoitem);   //回复我的评论对象
                listitem.add(itemmap);
            }
            //动态
            List<Map<String,Object>> menmap = memberService.list(uid);
            for(Map<String,Object> umap : menmap){
                Map m = new HashMap();
                m.put("user",umap.get("user"));
                m.put("item",umap.get("item"));
                listitem.add(m);
            }


            //把itemlist按照最大时间在前排序
            Collections.sort(listitem, new Comparator<Map<String, Map<String,Object>>>(){
                public int compare(Map<String,Map<String,Object>> o1, Map<String,Map<String,Object>> o2) {
                    String name1 =o1.get("item").get("reply_time").toString();//name1是从你list里面拿出来的一个
                    String name2= o2.get("item").get("reply_time").toString(); //name1是从你list里面拿出来的第二个name
                    return name2.compareTo(name1);
                }
            });
            map.put("reply",listitem);
            mmdata.put("data",map);
            mmdata.put("code",0);
            mmdata.put("message",null);

        }else{
            mmdata.put("data",null);
            mmdata.put("code",400);
            mmdata.put("message","data为空");
        }
        return mmdata;
    }


    //我的消息
    @GetMapping("/get_sessions")
    public Map usermsguid(){
        Integer uid = 1;    //当前用户id，默认为1
        Map usemap = new HashMap();
        if(usemap!= null && uid !=null) {
            Map map = new HashMap();
            List<Usermsgs> session_list = usermsgsService.selectusermsgs(uid);
            List usmsgslist = new ArrayList();
            for (Usermsgs usermsgs : session_list) {
                Map usmap = new HashMap();
                if(usermsgs.getUserID()==uid){
                    usmap.put("talker_id", usermsgs.getFriendID());        //对话的id
                }else {
                    usmap.put("talker_id", usermsgs.getUserID());        //对话的id
                }
                usmap.put("ack_seqno", session_list.size());  //此对话id的长度
                if (usermsgs.getUserID() == uid) {
                    usmap.put("talker_id", usermsgs.getFriendID());        //对话id
                } else {
                    usmap.put("talker_id", usermsgs.getUserID());        //对话id
                }
                usmap.put("ack_seqno", session_list.size());        //ack_seqno
                Map usermap = new HashMap();    //最新聊天对象
                usermap.put("sender_uid", usermsgs.getUserID()); //最新聊天数据的发送者
                usermap.put("receiver_id", usermsgs.getFriendID());    //最新聊天数据的接收这者
                usermap.put("content", usermsgs.getContent());    //最新聊天的内容
                usmap.put("last_msg", usermap);
                usmsgslist.add(usmap);
            }
            map.put("session_list", usmsgslist);
            usemap.put("code",0);
            usemap.put("data",map);
            usemap.put("message",null);
        }else {
            usemap.put("code",400);
            usemap.put("data",null);
            usemap.put("message","data为空或者参数为空");
        }

        return usemap;

    }

    /**
     * 聊天记录
     * @return
     */
    @GetMapping("/fetch_session_msgs")
    public Map usermsuidfidlist(Integer fid){
        Integer uid = 1;
//         = 3;
        Map map = new HashMap();
        if(map!=null && uid!=null && fid!=null){
            map.put("code",0);
                Map messagesmap = new HashMap();
                List<Usermsgs> usermsgsList = usermsgsService.usermsgslistuidfid(uid,fid);
                List list = new ArrayList();
                for(Usermsgs usermsgs : usermsgsList){
                    Map listmess = new HashMap();
                    listmess.put("sender_uid",usermsgs.getUserID());       //数据的发送者
                    listmess.put("receiver_id",usermsgs.getFriendID());       //数据的接受者
                    listmess.put("content",usermsgs.getContent());      //传一条数据的内容
                    listmess.put("timestamp",usermsgs.getUpdateTime()); //发送这一条数据的时间
                    list.add(listmess);
                }

                messagesmap.put("messages",list);
            map.put("data",messagesmap);
            map.put("message",null);
        }else {
            map.put("code",400);
            map.put("data",null);
            map.put("message","data为空或者参数为空");
        }
        return map;
    }





}

