package com.dreamwolf.message.controller;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.comment.Comment;
import com.dreamwolf.entity.comment.Commentlike;
import com.dreamwolf.entity.comment.web_interface.Commcidmap;
import com.dreamwolf.entity.message.Usermsgs;
import com.dreamwolf.entity.message.web_interface.*;
import com.dreamwolf.entity.video.Video;
import com.dreamwolf.entity.video.Videocomment;
import com.dreamwolf.entity.video.Videolike;
import com.dreamwolf.entity.video.web_interface.VideoList;
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
    public ResponseData<List<Usermsgs>> selectlistfid(Integer fid){
        List<Usermsgs> usermsgsList = usermsgsService.selectusmsgsfid(fid);
        return new ResponseData(0,"",1,usermsgsList);
    }

    @GetMapping("/usermsguid")
    public ResponseData<List<Usermsgs>> selectlistuid(Integer uid){
        List<Usermsgs> usermsgsList = usermsgsService.selectusmsgsuid(uid);
        return new ResponseData(0,"",1,usermsgsList);
    }


    //收到的点赞
    @GetMapping("/like")
    public ResponseData<Msgstotal> selectmeslist(){
            Integer uid = 1; //当前用户id
            ResponseData<VideoList>  bv_ids = videoService.videouID(uid);
            ResponseData<List<Videolike>> video_like_list = videoService.sellist((Integer[]) bv_ids.getData().list.toArray(new Integer[0]));  //根据视频id数组查询点赞表
            //根据视频id数组批量查询视频数据
            ResponseData<List<Video>> videos = videoService.selectbvidlist((Integer[]) bv_ids.getData().list.toArray(new Integer[0]));

            ResponseData<List<Integer>> comarr = commentService.selectuidlisst(uid); //根据用户id拿到用户下的评论
            ResponseData<List<Commentlike>> commentlikelist = commentService.selectarrlist(comarr.getData().toArray(new Integer[0])); //根据评论id数组拿到评论点赞的数据
            ResponseData<List<Comment>> commentscidarr = commentService.commentsarrlist(comarr.getData().toArray(new Integer[0]));//拿到评论id的数据
            List itemlist = new ArrayList();
            int i =1;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date last_like_time = null;
            for(Comment comm : commentscidarr.getData()){
                MsgsVideoItem commap = new MsgsVideoItem();
                commap.setId(i++);
                List<Integer> user_ids2 = new ArrayList<>(); //用户id数组
                for (Commentlike commentslike:commentlikelist.getData()){ //遍历视频点赞表数据
                    if (commentslike.getCID().equals(comm.getCID())) {
                        last_like_time=commentslike.getCreateTime();
                        user_ids2.add(commentslike.getUID());
                    }
                }
                if (user_ids2.size()<=0){
                    continue;
                }
                //少个用户
//                List uselist =  memberService.users(user_ids2.toArray(new Integer[0]),uid);
//
//                commap.put("users",uselist);//用户对象
                Items mlist = new Items();
                mlist.setItem_id(comm.getCID());    //评论id
                if(comm.getCID() ==comm.getCID()){
                    mlist.setType("reply");   //类型video代表视频，dynamic代表动态 reply代表文字 String
                }
                mlist.setTitle(comm.getCText());  //正文
                mlist.setDesc(null);     //描述
                mlist.setImage(null);    //封面图
                mlist.setUri(null);     //链接
                mlist.setCtime(comm.getCreateTime());
                commap.setItem(mlist);
                commap.setCounts(user_ids2.size());
                commap.setLike_time(last_like_time);    //最新点赞的时间
                itemlist.add(commap);

            }
            for(Video video:videos.getData()){
                MsgsVideoItem itemObject = new MsgsVideoItem();
                itemObject.setId(i++);

                List<Integer> user_ids = new ArrayList<>(); //用户id数组
                for (Videolike videolike:video_like_list.getData()){ //遍历视频点赞表数据
                    if (videolike.getBvID().equals(video.getBvID())) {
                        last_like_time=videolike.getCreateTime();
                        user_ids.add(videolike.getUID());
                    }
                }
                //少个用户
//                List uselist2 =  memberService.users(user_ids.toArray(new Integer[0]),uid);
//                if (uselist2.size()<=0){
//                    continue;
//                }
//                itemObject.put("users",uselist2);//用户对象
                Items mlist = new Items();
                mlist.setItem_id(video.getBvID());    //视频id
                if(video.getBvID() ==video.getBvID()){
                    mlist.setType("video");   //类型video代表视频，dynamic代表动态 reply代表文字 String
                }
                mlist.setTitle(video.getBvTitle());  //标题
                mlist.setDesc(video.getBvDesc());     //描述
                mlist.setImage(video.getBvCoverImgPath());    //封面图
                mlist.setUri( video.getBvVideoPath());     //链接
                mlist.setCtime(video.getBvPostTime());   //发表视频时间
                itemObject.setItem(mlist);
                itemObject.setCounts(user_ids.size());      //此评论/视频/动态的总人数
                itemObject.setLike_time(last_like_time);    //最新点赞的时间
                itemlist.add(itemObject);
            }
            //动态
//            List<Map<String,Object>> dylist  =dynamicService.likeitems(uid);
//            for(Map<String,Object> limap :dylist ){
//                Map slp = new HashMap();
//                slp.put("id",i++);
//                slp.put("users",limap.get("users"));
//                slp.put("item",limap.get("item"));
//                slp.put("counts",limap.get("counts"));
//                slp.put("like_time",limap.get("like_time"));
//                itemlist.add(slp);
//            }
            //把itemlist按照最大时间在前排序
//            Collections.sort(itemlist, new Comparator<Map<String,Object>>(){
//                public int compare(Map<String,Object> o1, Map<String,Object> o2) {
//                    String name1 =o1.get("like_time").toString();//name1是从你list里面拿出来的一个
//                    String name2= o2.get("like_time").toString(); //name1是从你list里面拿出来的第二个name
//                    return name2.compareTo(name1);
//                }
//            });

            Msgsitems msgsitems = new Msgsitems(itemlist);
            Msgstotal msgstotal = new Msgstotal(msgsitems);
        return new ResponseData(0,"",1,msgstotal);
    }

//有问题
    //回复我的
    @GetMapping("/reply")
    public ResponseData<Msgsreply> usermsglist(){
            Integer uid = 1;
            ResponseData<VideoList>  videoarr = videoService.videouID(uid);    //根据用户id拿到所有视频 视频id数组
            //根据视频id数组拿到视频下面的评论id
//            ResponseData<List<Integer>> videocommmarr = videoService.selectarr((Integer[]) videoarr.getData().list.toArray(new Integer[0]));
            //根据视频id数组拿到视频评论表所有数据
            ResponseData<List<Videocomment>> bvidcomm =videoService.videocombvidlist((Integer[]) videoarr.getData().list.toArray(new Integer[0]));
            ResponseData<List<Comment>> comments = commentService.selecomuid(uid);    //根据uid查询发布的评论
            List listitem = new ArrayList();
            for(Comment comme_nlist : comments.getData()){
                MMres itemmaps = new MMres();
                MMItems comm_map = new MMItems();
                ResponseData<List<Comment>> comlsit = commentService.selecomcid(comme_nlist.getCIDreply()); //根据comments的回复id来查
                Integer userid=0;
                for(Comment clist : comlsit.getData()){
                    comm_map.setSource_content(clist.getCText());   //被回复的评论内容
                    if (comme_nlist.getCID() == comme_nlist.getCID()) {
                        comm_map.setType("reply");  //video代表视频，dynamic代表动态 reply代表文字
                    }
                    comm_map.setBusiness("评论");
                    comm_map.setTitle(comme_nlist.getCText());        //被回复的评论 视频则为空
                    comm_map.setReply_time(clist.getCreateTime());    //当前时间
                    comm_map.setUri(null);
                    comm_map.setImage(null);   //当前 视频，动态的封面
                    comm_map.setNative_uri(null);
                    userid=clist.getUID();
                }
                //少个用户
//                Map<String,Object> usemap = memberService.replyuserb(userid,uid);
//                itemmaps.put("user",usemap);       //回复的用户对象
                itemmaps.setItem(comm_map);   //回复我的评论对象
                listitem.add(itemmaps);
            }


            //视频
            for(Videocomment vcom : bvidcomm.getData()){
                MMres itemmap = new MMres();
                MMItems videoitem = new MMItems();
                ResponseData<Video> videomap = videoService.videobvidlists(vcom.getBvID());  //根据视频id查询视频数据
                ResponseData<Commcidmap> commlistmap=commentService.commcidlist(vcom.getCID());
                    videoitem.setSource_content(commlistmap.getData().getSource_content());   //被回复的评论内容
                    if (vcom.getBvID() == vcom.getBvID()) {
                        videoitem.setType("video");  //video代表视频，dynamic代表动态 reply代表文字
                    }
                    videoitem.setBusiness("视频");    //视频，动态，文字三个参数
                    videoitem.setTitle( null);        //被回复的评论 视频则为空
                    videoitem.setReply_time(commlistmap.getData().getReply_time());    //当前时间
                    videoitem.setUri(null);
                    videoitem.setBvid(videomap.getData().getBvID());
                    videoitem.setImage(videomap.getData().getBvCoverImgPath());   //当前 视频，动态的封面
                    videoitem.setNative_uri(null);
                    Integer id= (Integer) commlistmap.getData().getUid();
                    //少个用户
//                Map<String,Object> usemap = memberService.replyuserb(id,uid);
//                itemmap.put("user",usemap);       //回复的用户对象
                itemmap.setItem(videoitem);   //回复我的评论对象
                listitem.add(itemmap);
            }
            //动态
//            List<Map<String,Object>> menmap = memberService.list(uid);
//            for(Map<String,Object> umap : menmap){
//                Map m = new HashMap();
//                m.put("user",umap.get("user"));
//                m.put("item",umap.get("item"));
//                listitem.add(m);
//            }


            //把itemlist按照最大时间在前排序
//            Collections.sort(listitem, new Comparator<Map<String, Map<String,Object>>>(){
//                public int compare(Map<String,Map<String,Object>> o1, Map<String,Map<String,Object>> o2) {
//                    String name1 =o1.get("item").get("reply_time").toString();//name1是从你list里面拿出来的一个
//                    String name2= o2.get("item").get("reply_time").toString(); //name1是从你list里面拿出来的第二个name
//                    return name2.compareTo(name1);
//                }
//            });
        Msgsreply mmdata = new Msgsreply(listitem);
        return new ResponseData(0,"",1,mmdata);
    }


    //我的消息
    @GetMapping("/get_sessions")
    public ResponseData<UserMsgsList> usermsguid(){
        Integer uid = 1;    //当前用户id，默认为1
        UserMsgsList userMsgsList=null;
        if(uid !=null) {
            List<Usermsgs> session_list = usermsgsService.selectusermsgs(uid);
            List usmsgslist = new ArrayList();
            for (Usermsgs usermsgs : session_list) {
                MsgsDatamap msgsDatamap = new MsgsDatamap();
                if(usermsgs.getUserID()==uid){
                    msgsDatamap.setTalker_id(usermsgs.getFriendID());        //对话的id
                }else {
                    msgsDatamap.setTalker_id(usermsgs.getFriendID());           //对话的id
                }
                msgsDatamap.setAck_seqno(session_list.size());  //此对话id的长度
                if (usermsgs.getUserID() == uid) {
                    msgsDatamap.setTalker_id(usermsgs.getFriendID());          //对话id
                } else {
                    msgsDatamap.setTalker_id(usermsgs.getFriendID());          //对话id
                }
                msgsDatamap.setAck_seqno(session_list.size());  //此对话id的长度
                Msgslastmsg msgslastmsg = new Msgslastmsg();    //最新聊天对象
                msgslastmsg.setSender_uid(usermsgs.getUserID()); //最新聊天数据的发送者
                msgslastmsg.setReceiver_id(usermsgs.getFriendID());    //最新聊天数据的接收这者
                msgslastmsg.setContent(usermsgs.getContent());    //最新聊天的内容
                msgsDatamap.setMsgslastmsg(msgslastmsg);
                usmsgslist.add(msgsDatamap);
            }
            userMsgsList= new UserMsgsList(usmsgslist);
        }else {
            userMsgsList= new UserMsgsList(null);
        }

        return new ResponseData(0,"",1,userMsgsList);

    }

    /**
     * 聊天记录
     * @return
     */
    @GetMapping("/fetch_session_msgs")
    public ResponseData<Messlist> usermsuidfidlist(Integer fid){
        Integer uid = 1;
        List<Usermsgs> usermsgsList = usermsgsService.usermsgslistuidfid(uid,fid);
        List list = new ArrayList();
        for(Usermsgs usermsgs : usermsgsList){
            Msgslastmsg msgslastmsg = new Msgslastmsg();
            msgslastmsg.setSender_uid(usermsgs.getUserID());       //数据的发送者
            msgslastmsg.setReceiver_id(usermsgs.getFriendID());       //数据的接受者
            msgslastmsg.setContent(usermsgs.getContent());      //传一条数据的内容
            msgslastmsg.setTimestamp(usermsgs.getUpdateTime()); //发送这一条数据的时间
            list.add(msgslastmsg);
        }
        Messlist messlist = new Messlist(list);
        return new ResponseData(0,"",1,messlist);
    }





}

