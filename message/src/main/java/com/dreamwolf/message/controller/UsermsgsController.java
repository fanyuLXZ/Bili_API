package com.dreamwolf.message.controller;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.comment.Comment;
import com.dreamwolf.entity.comment.Commentlike;
import com.dreamwolf.entity.comment.web_interface.Commcidmap;
import com.dreamwolf.entity.dynamic.IikesItems;
import com.dreamwolf.entity.member.Member;
import com.dreamwolf.entity.member.ReplyUser;
import com.dreamwolf.entity.member.Users;
import com.dreamwolf.entity.message.Usermsgs;
import com.dreamwolf.entity.message.web_interface.*;
import com.dreamwolf.entity.video.Video;
import com.dreamwolf.entity.video.Videocomment;
import com.dreamwolf.entity.video.Videolike;
import com.dreamwolf.entity.video.web_interface.VideoList;
import com.dreamwolf.message.service.*;
import com.dreamwolf.safety.util.TokenUtil;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.util.*;

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

    @Resource
    SafetyService safetyService;

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
    public ResponseData<Msgstotal> selectmeslist(HttpServletRequest request){
        ResponseData<Integer> logon_uid_result = safetyService.logon_uid(TokenUtil.getToken(request));
        if (logon_uid_result.getCode()==0) {
//            Integer uid = 1; //当前用户id
            ResponseData<VideoList> bv_ids = videoService.videouID(logon_uid_result.getData());
            ResponseData<List<Videolike>> video_like_list = videoService.sellist((Integer[]) bv_ids.getData().list.toArray(new Integer[0]));  //根据视频id数组查询点赞表
            //根据视频id数组批量查询视频数据
            ResponseData<List<Video>> videos = videoService.selectbvidlist((Integer[]) bv_ids.getData().list.toArray(new Integer[0]));

            ResponseData<List<Integer>> comarr = commentService.selectuidlisst(logon_uid_result.getData()); //根据用户id拿到用户下的评论
            ResponseData<List<Commentlike>> commentlikelist = commentService.selectarrlist(comarr.getData().toArray(new Integer[0])); //根据评论id数组拿到评论点赞的数据
            ResponseData<List<Comment>> commentscidarr = commentService.commentsarrlist(comarr.getData().toArray(new Integer[0]));//拿到评论id的数据
            List itemlist = new ArrayList();
            int i = 1;
            Date last_like_time = null;
            for (Comment comm : commentscidarr.getData()) {
                MsgsVideoItem commap = new MsgsVideoItem();
                commap.setId(i++);
                List<Integer> user_ids2 = new ArrayList<>(); //用户id数组
                for (Commentlike commentslike : commentlikelist.getData()) { //遍历视频点赞表数据
                    if (commentslike.getCID().equals(comm.getCID())) {
                        last_like_time = commentslike.getCreateTime();
                        user_ids2.add(commentslike.getUID());
                    }
                }
                if (user_ids2.size() <= 0) {
                    continue;
                }
                ResponseData<List<Users>> uselist = memberService.users(user_ids2.toArray(new Integer[0]), logon_uid_result.getData());
                commap.setUsers(uselist.getData());//用户对象
                Items mlist = new Items();
                mlist.setItem_id(comm.getCID());    //评论id
                if (comm.getCID() == comm.getCID()) {
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
            for (Video video : videos.getData()) {
                MsgsVideoItem itemObject = new MsgsVideoItem();
                itemObject.setId(i++);

                List<Integer> user_ids = new ArrayList<>(); //用户id数组
                for (Videolike videolike : video_like_list.getData()) { //遍历视频点赞表数据
                    if (videolike.getBvID().equals(video.getBvID())) {
                        last_like_time = videolike.getCreateTime();
                        user_ids.add(videolike.getUID());
                    }
                }
                ResponseData<List<Users>> uselist2 = memberService.users(user_ids.toArray(new Integer[0]), logon_uid_result.getData());
                if (uselist2.getData().size() <= 0) {
                    continue;
                }
                itemObject.setUsers(uselist2.getData());//用户对象
                Items mlist = new Items();
                mlist.setItem_id(video.getBvID());    //视频id
                if (video.getBvID() == video.getBvID()) {
                    mlist.setType("video");   //类型video代表视频，dynamic代表动态 reply代表文字 String
                }
                mlist.setTitle(video.getBvTitle());  //标题
                mlist.setDesc(video.getBvDesc());     //描述
                mlist.setImage(video.getBvCoverImgPath());    //封面图
                mlist.setUri(video.getBvVideoPath());     //链接
                mlist.setCtime(video.getBvPostTime());   //发表视频时间
                itemObject.setItem(mlist);
                itemObject.setCounts(user_ids.size());      //此评论/视频/动态的总人数
                itemObject.setLike_time(last_like_time);    //最新点赞的时间
                itemlist.add(itemObject);
            }

            //动态
            ResponseData<List<IikesItems>> dylist = dynamicService.likeitems(logon_uid_result.getData());
            for (IikesItems limap : dylist.getData()) {
                MsgsVideoItem slp = new MsgsVideoItem();
                slp.setId(i++);
                slp.setUsers(limap.getUsers());
                slp.setItem(limap.getItem());
                slp.setCounts(Integer.parseInt(limap.getCounts()));
                //把LocalDateTime 转成Date
                Date date = Date.from(limap.getLike_time().atZone(ZoneId.systemDefault()).toInstant());
                slp.setLike_time(date);
                itemlist.add(slp);
            }

            //把itemlist按照最大时间在前排序
            Collections.sort(itemlist, new Comparator<MsgsVideoItem>() {
                public int compare(MsgsVideoItem o1, MsgsVideoItem o2) {
                    try {
                        if (o1.getLike_time() == null || o2.getLike_time() == null) {
                            return 1;
                        }
                        Date dt1 = o1.getLike_time();
                        Date dt2 = o2.getLike_time();

                        if (dt1.getTime() > dt2.getTime()) {
                            return -1;
                        } else if (dt1.getTime() < dt2.getTime()) {
                            return 1;
                        } else {
                            return 0;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });

            Msgsitems msgsitems = new Msgsitems(itemlist);
            Msgstotal msgstotal = new Msgstotal(msgsitems);
            return new ResponseData(0, "", 1, msgstotal);
        }else {
            return new ResponseData<>(logon_uid_result.getCode(), logon_uid_result.getMessage(), 1, null);
        }
    }


    //回复我的
    @GetMapping("/reply")
    public ResponseData<Msgsreply> usermsglist(HttpServletRequest request){
        ResponseData<Integer> logon_uid_result = safetyService.logon_uid(TokenUtil.getToken(request));
        if(logon_uid_result.getCode()==0){

//            Integer uid = 1;
            ResponseData<VideoList>  videoarr = videoService.videouID(logon_uid_result.getData());    //根据用户id拿到所有视频 视频id数组
            //根据视频id数组拿到视频下面的评论id
//            ResponseData<List<Integer>> videocommmarr = videoService.selectarr((Integer[]) videoarr.getData().list.toArray(new Integer[0]));
            //根据视频id数组拿到视频评论表所有数据
            ResponseData<List<Videocomment>> bvidcomm =videoService.videocombvidlist((Integer[]) videoarr.getData().list.toArray(new Integer[0]));
            ResponseData<List<Comment>> comments = commentService.selecomuid(logon_uid_result.getData());    //根据uid查询发布的评论
            List<MMres> listitem = new ArrayList();
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
                ResponseData<ReplyUser> usemap = memberService.replyuserb(userid,logon_uid_result.getData());
                itemmaps.setUser(usemap.getData());       //回复的用户对象
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
                    Integer id=commlistmap.getData().getUid();

                ResponseData<ReplyUser> usemap = memberService.replyuserb(id,logon_uid_result.getData());
                itemmap.setUser(usemap.getData());//回复的用户对象
//                Map<String,Object> usemap = memberService.replyuserb(id,uid);
                itemmap.setItem(videoitem);   //回复我的评论对象
                listitem.add(itemmap);
            }
//        listitem.sort(Comparator.comparing(MMres::g));
//            listitem.sort(Comparator.comparing(MMres::getItem).reversed());
            //动态
            ResponseData<List<MMres>> menmap = memberService.list(logon_uid_result.getData());
            for(MMres umap : menmap.getData()){
                MMres m = new MMres();
                m.setUser(umap.getUser());
                m.setItem(umap.getItem());
                listitem.add(m);
            }

            //按最新时间排序
        Collections.sort(listitem, new Comparator<MMres>(){
            public int compare(MMres o1, MMres o2) {
                try {
                    if(o1.getItem().getReply_time() == null || o2.getItem().getReply_time() == null){
                        return 1;
                    }
                    Date dt1 = o1.getItem().getReply_time();
                    Date dt2 = o2.getItem().getReply_time();

                    if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });

        Msgsreply mmdata = new Msgsreply(listitem);
        return new ResponseData(0,"",1,mmdata);

        }else {
            return new ResponseData<>(logon_uid_result.getCode(), logon_uid_result.getMessage(), 1, null);
        }
    }


    //我的消息
    @GetMapping("/get_sessions")
    public ResponseData<UserMsgsList> usermsguid(HttpServletRequest request){
        ResponseData<Integer> logon_uid_result = safetyService.logon_uid(TokenUtil.getToken(request));
        if(logon_uid_result.getCode()==0){

//        Integer uid = 1;    //当前用户id，默认为1
        UserMsgsList userMsgsList=null;
        if(logon_uid_result.getData() !=null) {
            List<Usermsgs> session_list = usermsgsService.selectusermsgs(logon_uid_result.getData());
            List usmsgslist = new ArrayList();
            for (Usermsgs usermsgs : session_list) {
                MsgsDatamap msgsDatamap = new MsgsDatamap();
                if(usermsgs.getUserID()==logon_uid_result.getData()){
                    msgsDatamap.setTalker_id(usermsgs.getFriendID());        //对话的id
                }else {
                    msgsDatamap.setTalker_id(usermsgs.getFriendID());           //对话的id
                }
                msgsDatamap.setAck_seqno(session_list.size());  //此对话id的长度
                if (usermsgs.getUserID() == logon_uid_result.getData()) {
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

        }else {
            return new ResponseData<>(logon_uid_result.getCode(), logon_uid_result.getMessage(), 1, null);
        }

    }

    /**
     * 聊天记录
     * @return
     */
    @GetMapping("/fetch_session_msgs")
    public ResponseData<Messlist> usermsuidfidlist(Integer fid,HttpServletRequest request){
        ResponseData<Integer> logon_uid_result = safetyService.logon_uid(TokenUtil.getToken(request));
        if(logon_uid_result.getCode()==0){

//        Integer uid = 1;
        List<Usermsgs> usermsgsList = usermsgsService.usermsgslistuidfid(logon_uid_result.getData(),fid);
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

        }else {
            return new ResponseData<>(logon_uid_result.getCode(), logon_uid_result.getMessage(), 1, null);
        }
    }





}

