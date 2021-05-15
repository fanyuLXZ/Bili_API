package com.dreamwolf.video.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.comment.Comment;
import com.dreamwolf.entity.comment.web_interface.CommListMap;
import com.dreamwolf.entity.comment.web_interface.Commentinfo;
import com.dreamwolf.entity.comment.web_interface.Messagecontext;
import com.dreamwolf.entity.member.Member;
import com.dreamwolf.entity.video.Videocomment;
import com.dreamwolf.entity.video.web_interface.ReplyCursor;
import com.dreamwolf.entity.video.web_interface.VideoList;
import com.dreamwolf.entity.video.web_interface.VideoReply;
import com.dreamwolf.safety.util.TokenUtil;
import com.dreamwolf.video.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@RestController
public class VideocommentController {

    @Resource
    private VideocommentService videocommentService;
    @Resource
    private CommentService commselectcarrlist;
    @Resource
    private SafetyService safetyService;
    @Resource
    private UsermapService usermapService;

    //根据视频id数组查询视频评论表数据
    @GetMapping("/viderbvidcommlist")
    public ResponseData<List<Videocomment>> videocombvidlist(Integer[] array) {
        List<Videocomment> videocomments = videocommentService.selectvidercomlist(array);
        return new ResponseData(0, "", 0, videocomments);
    }

    //根据视频id数组查询视频评论id 返回list
    @GetMapping("/videocommmarr")
    public ResponseData<List<Integer>> selectarr(Integer[] arr) {
        List<Integer> integer = videocommentService.selectbvidarray(arr);
//        VideoList videoList = new VideoList(integer);
        return new ResponseData(0, "", 0, integer);
    }

    /**
     * 根据bvid视频id去查找评论id
     *
     * @param bvid
     * @return
     */
    @GetMapping(value = "/videocommbvid")
    public ResponseData selectbvids(Integer bvid) {
        List<Videocomment> videocommentList = videocommentService.selectbvID(bvid);//根据视频id查询评论id
        VideoList videoList = new VideoList(videocommentList);
        return new ResponseData(0, "", 0, videoList);
    }

    @GetMapping(value = "/videocommlist")
    public ResponseData selectlist() {
        List<Videocomment> videocommentList = videocommentService.selectlist();//查询视频评论表所有数据
        VideoList videoList = new VideoList(videocommentList);
        return new ResponseData(0, "", 0, videoList);
    }

    /**
     * 视频评论
     *
     * @param next 下一页
     * @param mode 模式 3为热度 2为时间
     * @param aid  视频id
     * @return 接口失败 code 可能值
     * 1 aid为空
     * 2 模式错误
     */
    @GetMapping("/reply/main")
    public ResponseData<VideoReply> reply(Integer next, Integer mode, Integer aid) {
        int code = 0;
        String message = "";
        VideoReply data = null;
        if (aid != null) {
            if (mode == 2 || mode == 3) {
                if (next == 0) {
                    next = 1;
                }
                // 查询视频评论
                QueryWrapper<Videocomment> videoCommentQueryWrapper = new QueryWrapper<>();
                videoCommentQueryWrapper.eq("bvID", aid);
                List<Videocomment> videoComments = videocommentService.list(videoCommentQueryWrapper);
                List<CommListMap> replies = null;
                if (videoComments != null && videoComments.size() != 0) {
                    List<Integer> videoCommentIds = new ArrayList<>();
                    for (Videocomment videocomment : videoComments) {
                        videoCommentIds.add(videocomment.getCID());
                    }
                    ResponseData<List<CommListMap>> commListMapResponseData = commselectcarrlist.commselecarlistpage(mode == 3 ? 1 : 2, videoCommentIds.toArray(new Integer[0]),next,20);
                    if (commListMapResponseData.getCode() == 0) {
                        replies = commListMapResponseData.getData();
                    } else {
                        code = commListMapResponseData.getCode();
                        message = commListMapResponseData.getMessage();
                    }
                }
                if (code == 0) {
                    if (replies == null)
                        replies = new ArrayList<>();
                    if (videoComments!=null){
                        int all_count = videoComments.size();
                        double totalNext = Math.ceil((double)all_count / 20);
                        data = new VideoReply(new ReplyCursor(all_count, next == 1, next == (totalNext==0?1:totalNext), mode, "", next, next + 1), replies);
                    }else {
                        data = new VideoReply(new ReplyCursor(),replies);
                    }
                }
            } else {
                code = 2;
                message = "未知模式";
            }
        } else {
            code = 1;
            message = "空值异常";
        }
        return new ResponseData<>(code, message, 1, data);
    }

    @PostMapping("/reply/add")
    public ResponseData<CommListMap> replyAdd(Integer oid, String message, HttpServletRequest request) {
        int code = 0;
        String mess = "";
        CommListMap data = null;
        if (oid != null && message != null && !message.equals("")) {
            //验证token
            ResponseData<Integer> response_uid = safetyService.logon_uid(TokenUtil.getToken(request));
            if (response_uid.getCode() == 0) {
                ResponseData<Comment> responseDataReplyAdd = commselectcarrlist.replyAdd(message,response_uid.getData());
                if (responseDataReplyAdd.getCode()==0){
                    Comment comment = responseDataReplyAdd.getData();
                    Videocomment videocomment = new Videocomment(oid,responseDataReplyAdd.getData().getCID());
                    if (videocommentService.save(videocomment)){
                        // 获取用户信息
                        ResponseData<Member> memberResponseData = usermapService.cardInfoById(response_uid.getData());
                        Member member = null;
                        if (memberResponseData.getCode()==0){
                            member = memberResponseData.getData();
                        }else {
                            member = new Member();
                        }
                        data = new CommListMap(comment.getCID(),0,new Messagecontext(comment.getCText()),0,comment.getCreateTime(),0L,member,new ArrayList<>());
                    }else {
                        code=4;
                        mess = "内部服务异常";
                    }
                }else {
                    code = 3;
                    mess = "内部服务器异常";
                }
            } else {
                code = 2;
                mess = "请先登录哦";
            }
        } else {
            code = 1;
            mess = "空值异常";
        }

        return new ResponseData<>(code, mess, 1, data);
    }

    @PostMapping("/reply/add/uid")
    public ResponseData<CommListMap> replyAdd(Integer oid, String message,Integer uid) {
        int code = 0;
        String mess = "";
        CommListMap data = null;
        if (oid != null && message != null && !message.equals("")) {
            //验证token
                ResponseData<Comment> responseDataReplyAdd = commselectcarrlist.replyAdd(message,uid);
                if (responseDataReplyAdd.getCode()==0){
                    Comment comment = responseDataReplyAdd.getData();
                    Videocomment videocomment = new Videocomment(oid,responseDataReplyAdd.getData().getCID());
                    if (videocommentService.save(videocomment)){
                        // 获取用户信息
                        ResponseData<Member> memberResponseData = usermapService.cardInfoById(uid);
                        Member member = null;
                        if (memberResponseData.getCode()==0){
                            member = memberResponseData.getData();
                        }else {
                            member = new Member();
                        }
                        data = new CommListMap(comment.getCID(),0,new Messagecontext(comment.getCText()),0,comment.getCreateTime(),0L,member,new ArrayList<>());
                    }else {
                        code=4;
                        mess = "内部服务异常";
                    }
                }else {
                    code = 3;
                    mess = "内部服务器异常";
                }
            } else {
                code = 2;
                mess = "请先登录哦";
            }

        return new ResponseData<>(code, mess, 1, data);
    }


}

