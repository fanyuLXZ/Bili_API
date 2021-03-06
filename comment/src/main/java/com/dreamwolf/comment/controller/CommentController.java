package com.dreamwolf.comment.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.comment.service.*;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.comment.Comment;
import com.dreamwolf.entity.comment.Commentdata;
import com.dreamwolf.entity.comment.Commentlike;
import com.dreamwolf.entity.comment.web_interface.*;
import com.dreamwolf.entity.member.Member;
import com.dreamwolf.entity.video.Videocomment;
import com.dreamwolf.entity.video.Videodata;
import com.dreamwolf.entity.video.web_interface.ReplyCursor;
import com.dreamwolf.entity.video.web_interface.VideoReply;
import com.dreamwolf.safety.util.TokenUtil;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 用户评论表
 * 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
@RestController
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private UsermapService usermapService;

    @Resource
    private CommentlikeService commentlikeService;

    @Resource
    private CommentdataService commentdataService;

    @Resource
    private DvnamicService dvnamicService;

    @Resource
    private SafetyService safetyService;

    /**
     * 评论回复信息
     * rpid 评论id int
     * pn 页码 int
     * ps 每页总条数 int
     *
     * @param rpid
     * @return
     */
    @GetMapping("/reply")
    public ResponseData<List<Commentinfo>> selectrepd(Integer rpid, Integer pn, Integer ps) {
        List<Comment> list = commentService.selectrpid(rpid, (pn - 1)*ps, ps);  //根据评论id查询子评论数并分页处理
        List<Commentinfo> comlist = new ArrayList();
        Integer count = commentService.commcountcIDreply(rpid);    //子评论总数
        CommentPage commentPage = new CommentPage(count, pn, ps);
        for (Comment com : list) {
            Messagecontext maplist = new Messagecontext(com.getCText());
            ResponseData<Member> usermap = usermapService.membe(com.getUID());   //用户对象
            Commentinfo commentinfo = new Commentinfo(com.getCID(), com.getCIDreply(),
                     com.getCommentdata().getCLikeNum(), com.getCommentdata().getCUnLikeNum(),
                    com.getCreateTime(), maplist, usermap.getData());

            comlist.add(commentinfo);
        }
        Replies commentList = new Replies(commentPage,comlist);
        return new ResponseData(0, "", 0, commentList);
    }

    /***
     * 根据评论id数组查询评论总数
     * @param arr
     * @return
     */
    @GetMapping("/commcidcount")
    public ResponseData selebvidint(Integer[] arr) {
//        Integer[] arr = new Integer[]{1,2,3};
        Integer count = commentService.selectbvidint(arr);
        CommentInteger commentList = new CommentInteger(count);
        return new ResponseData(0, "", 0, commentList);
    }

    /**
     * 根据当前用户id查询用户下的评论id
     *
     * @param uid
     * @return
     */
    @GetMapping("/commuidlist")
    public ResponseData<List<Integer>> selectuidlisst(Integer uid) {
        List<Integer> integers = commentService.selectuidlist(uid);
//        CommentList commentList = new CommentList(integers);
        return new ResponseData(0, "", 0, integers);
    }

    /**
     * 根据点赞评论id数组查询评论id基本数据
     *
     * @param array
     * @return
     */
    @GetMapping("/commcidarray")
    public ResponseData<List<Comment>> commentsarrlist(Integer[] array) {
        List<Comment> comments = commentService.commentuidlist(array);
//        CommentList commentList = new CommentList(comments);
        return new ResponseData(0, "", 0, comments);
    }


    /**
     * 根据评论id查询评论数据
     *
     * @param cid
     * @return
     */
    @GetMapping("/commcidlistmap")
    public ResponseData<Commcidmap> commcidlist(Integer cid) {
        Comment comment = commentService.commcidlist(cid);
        Commcidmap commap = new Commcidmap(comment.getUID(), comment.getCText(), comment.getCreateTime());
        return new ResponseData(0, "", 0, commap);
    }

    /**
     * 根据cid数组查询并且cIDreply=0的则是动态或者视频
     *
     * @param array
     * @return
     */
    @GetMapping("/comseletcilll")
    public ResponseData comseletcidli(Integer[] array) {
        List<Comment> commentList = commentService.commselectarrcidlist(array);
        CommentList commap = new CommentList(commentList);
        return new ResponseData(0, "", 0, commap);
    }


    /**
     * 根据uid用户查询评论表发布的评论数据
     *
     * @return
     */
    @GetMapping("/selecomuid")
    public ResponseData<List<Comment>> selecomuid(Integer uid) {
        List<Comment> commentList = commentService.commselectlistuid(uid);
        return new ResponseData(0, "", 0, commentList);
    }

    /**
     * 根据评论主键cid查询数据
     *
     * @param cid
     * @return
     */
    @GetMapping("/selecomcid")
    public ResponseData<List<Comment>> selecomcid(Integer cid) {
        List<Comment> commentList = commentService.commselectlistcid(cid);
        return new ResponseData(0, "", 0, commentList);
    }


    /**
     * 根据cid评论数组并且回复的评论id,cIDreply=0则是视频或者动态 id ：1 为热度排序  2 为时间排序
     *
     * @return
     */
    @GetMapping("/commselectcarrlist")
    public ResponseData<List<CommListMap>> commselecarlist(Integer id, Integer[] array) {
//        Integer[] array = new Integer[]{1,2,3};
        //  id 1 为热度排序  2 为时间排序
        List<CommListMap> maplist = new ArrayList();
        //热度排序
        if (id == 1) {
            List<Comment> list = commentService.commentuidlist(array); //通过评论id数组查询cIDreply=0的数据
            List<Integer> comarrlistss = new ArrayList<>();    //评论id数组
            for (Comment comment : list) {
                comarrlistss.add(comment.getCID());   //评论id
            }
            //根据评论id数组拿到前10条的热度 评论点赞表
            List<Commentdata> comdatlistNoDefault = commentdataService.commdatalistarr(comarrlistss.toArray(new Integer[0]));
            List<Commentdata> arraydata = new ArrayList<>();//按热度查
            // 将没有评论数据的评论加为默认数据
            if (comdatlistNoDefault.size() != 0) {
                int arraydataNoDefaultIndex = 0;
                int firstIndexId = comdatlistNoDefault.get(arraydataNoDefaultIndex).getCID();
                for (Integer i : array) {
                    if (!i.equals(firstIndexId)) {
                        arraydata.add(new Commentdata(i));
                    } else {
                        firstIndexId = comdatlistNoDefault.get(arraydataNoDefaultIndex).getCID();
                        arraydataNoDefaultIndex += 1;
                    }
                }
            } else {
                for (Integer i : array) {
                    arraydata.add(new Commentdata(i));
                }
            }
            //拿到评论点赞表热度前10的id
            List<Integer> clist = new ArrayList<>();
            for (Commentdata comdatacom : arraydata) {
                clist.add(comdatacom.getCID());
            }

            //根据热度前10 cid数组查询评论表数据
            List<Comment> comlistlist = commentService.comarrlist(clist.toArray(new Integer[0]));
            if (comlistlist.size() > 0) {

                for (Comment comarrlistlist : comlistlist) {
                    //根据评论赞数据和点踩数据
                    Commentdata commentdata = commentdataService.selectcID(comarrlistlist.getCID());
                    if (commentdata == null) {
                        commentdata = new Commentdata();
                    }
                    CommListMap commaplistmap = new CommListMap();
                    commaplistmap.setRpid(comarrlistlist.getCID());//评论id
                    Integer comcount = commentService.commcountcIDreply(comarrlistlist.getCID());  //子评论数量
                    //获得被点赞的评论数据
                    Commentlike comlike = commentlikeService.commlikecidlist(comarrlistlist.getCID(), comarrlistlist.getUID());
                    if (comlike != null) {
                        commaplistmap.setAction(comlike.getStatus());  //点赞状态
                    } else {
                        commaplistmap.setAction(null);
                    }
                    Messagecontext comtent = new Messagecontext(comarrlistlist.getCText());
                    commaplistmap.setContent(comtent);     //评论内容对象
                    commaplistmap.setCount(comcount);     //子评论数量
                    commaplistmap.setCtime(comarrlistlist.getCreateTime());    //评论时间
                    commaplistmap.setLike(commentdata.getCLikeNum());  //点赞数

                    ResponseData<Member> menmmap = dvnamicService.memberid(comarrlistlist.getUID());
                    commaplistmap.setMember(menmmap.getData());   //发表人评论id 对象

                    List<Comment> comlist = commentService.selelistcIDreply(comarrlistlist.getCID()); //获取当前评论id下面的子评论
                    if (comlist.size() > 0) {

                        List<Integer> cidarr = new ArrayList(); //子评论下面的cid集合
                        for (Comment comlist2 : comlist) {
                            cidarr.add(comlist2.getCID());
                        }
                        //通过子评论集合去查询子评论里面热度最高的数据取前三条
                        List<Commentdata> comdata = commentdataService.commdatalist(cidarr.toArray(new Integer[0]));
                        List<Integer> comlistcida = new ArrayList<>();  //热度最高前三条的cid集合
                        for (Commentdata commentdatalist : comdata) {
                            comlistcida.add(commentdatalist.getCID());
                        }
                        List<CommReplies> comarrlist_repl = new ArrayList();
                        List<Comment> comarrlist = commentService.comarrlist(comlistcida.toArray(new Integer[0]));
                        for (Comment commentarr : comarrlist) {
                            //根据cid去拿子评论的点赞对象
                            Commentdata commentdataarr = commentdataService.selectcID(commentarr.getCID());
                            CommReplies commReplies = null;
                            if (commentdataarr != null) {
                                ResponseData<Member> dvmaps = dvnamicService.memberid(commentarr.getUID());
                                Messagecontext mm = new Messagecontext(commentarr.getCText());
                                commReplies = new CommReplies(mm, commentarr.getCreateTime(),
                                        commentdataarr.getCLikeNum(), dvmaps.getData());

                                //                            comdatamap.put("member", dvmaps);       //用户id
                            } else {
                                commReplies = new CommReplies(null, null, null, null);
                            }
                            comarrlist_repl.add(commReplies);
                        }

                        commaplistmap.setReplies(comarrlist_repl); //子评论集合

                    } else {
                        List<CommReplies> a = new ArrayList();
                        a.add(new CommReplies());
                        commaplistmap.setReplies(a);
                    }
                    maplist.add(commaplistmap);

                }
            } else {
                maplist.add(new CommListMap());
            }

        } else {
            List<Comment> list = commentService.comdatalisttime(array); //通过评论id数组查询cIDreply=0的数据
//            List<Integer> comarrlistss = new ArrayList<>();    //评论id数组
            if (list.size() > 0) {

                for (Comment comment : list) {
                    //根据评论id查询评论点赞数据和点踩数据
                    Commentdata commentdata = commentdataService.selectcID(comment.getCID());
                    CommListMap commaplistmap = new CommListMap();
                    commaplistmap.setRpid(comment.getCID());//评论id
                    Integer comcount = commentService.commcountcIDreply(comment.getCID());  //子评论数量
                    //获得被点赞的评论数据
                    Commentlike comlike = commentlikeService.commlikecidlist(comment.getCID(), comment.getUID());
                    if (comlike != null) {
                        commaplistmap.setAction(comlike.getStatus());
                    } else {
                        commaplistmap.setAction(null);
                    }
                    Messagecontext mm2 = new Messagecontext(comment.getCText());
                    commaplistmap.setContent(mm2);  //评论内容对象
                    commaplistmap.setCount(comcount);       //子评论数量
                    commaplistmap.setCtime(comment.getCreateTime());    //评论时间
                    commaplistmap.setLike(commentdata.getCLikeNum());   //点赞数
                    ResponseData<Member> dvmap = dvnamicService.memberid(comment.getUID());
                    commaplistmap.setMember(dvmap.getData()); //用户对象

                    //                   Map dvmap=dvnamicService.memberid(commentarr.getUID());
                    //                  comdatamap.put("member", dvmap);       //用户对象

                    List<Comment> comlist = commentService.selelistcIDreply(comment.getCID()); //获取当前评论id下面的子评论
                    List<Integer> cidarr = new ArrayList(); //子评论下面的cid集合
                    for (Comment comlist2 : comlist) {
                        cidarr.add(comlist2.getCID());
                    }
                    //通过子评论集合去查询子评论里面热度最高的数据取前三条
                    List<Commentdata> comdata = commentdataService.commdatalist(cidarr.toArray(new Integer[0]));
                    List<Integer> comlistcida = new ArrayList<>();  //热度最高前三条的cid集合
                    for (Commentdata commentdatalist : comdata) {
                        comlistcida.add(commentdatalist.getCID());
                    }
                    List comarrlist_repl = new ArrayList();
                    List<Comment> comarrlist = commentService.comarrlist(comlistcida.toArray(new Integer[0]));
                    for (Comment commentarr : comarrlist) {
                        //根据cid去拿子评论的点赞对象
                        Commentdata commentdataarr = commentdataService.selectcID(commentarr.getCID());
                        CommReplies commReplies = null;
                        if (commentdataarr != null) {
                            ResponseData<Member> dvmaps = dvnamicService.memberid(commentarr.getUID());

                            Messagecontext mm3 = new Messagecontext(commentarr.getCText());
                            commReplies = new CommReplies(mm3, commentarr.getCreateTime(),
                                    commentdataarr.getCLikeNum(), dvmaps.getData());

                            //                            comdatamap.put("member", dvmaps);       //用户id
                        } else {
                            commReplies = new CommReplies(null, null, null, null);
                        }
                        comarrlist_repl.add(commReplies);
                    }
                    commaplistmap.setReplies(comarrlist_repl); //子评论集合
                    maplist.add(commaplistmap);
                }

            } else {
                maplist.add(new CommListMap());
            }
        }
        return new ResponseData(0, "", 0, maplist);
    }

    /**
     * 根据cid评论数组并且回复的评论id,cIDreply=0则是视频或者动态 id ：1 为热度排序  2 为时间排序 并分页处理
     * next 页码 int
     *
     * @return
     */
    @GetMapping("/commselectcarrlistpage")
    public ResponseData<List<CommListMap>> commselecarlistpage(Integer id, Integer next, Integer[] array, Integer ps) {
        //  id 1 为热度排序  2 为时间排序
        List<CommListMap> maplist = new ArrayList();
        //热度排序
        if (id == 1) {
            //通过评论id数组查询cIDreply=0的数据
//            List<Comment> list = commentService.commentuidlist(array);
//            List<Integer> comarrlistss = new ArrayList<>();    //评论id数组
//            for (Comment comment : list) {
//                comarrlistss.add(comment.getCID());   //评论id
//            }
//            //根据评论id数组拿到前10条的热度 评论点赞表 分页
//            List<Commentdata> comdatlist = commentdataService.commdatalistpage(comarrlistss.toArray(new Integer[0]),next,ps);
//            List<Integer> clist = new ArrayList<>();
//            //拿到评论点赞表热度前10的id数组
//            for (Commentdata comdatacom : comdatlist) {
//                clist.add(comdatacom.getCID());
//            }

            //联表查根据热度查询评论并分页
            List<Comment> comlistlist = commentService.comselectlistdata(array, (next - 1) * ps, ps);
            for (Comment comarrlistlist : comlistlist) {
                //根据评论id查询评论点赞数据和点踩数据
                Commentdata commentdata = commentdataService.selectcID(comarrlistlist.getCID());
                CommListMap commaplistmap = new CommListMap();
                commaplistmap.setRpid(comarrlistlist.getCID()); //评论id
                Integer comcount = commentService.commcountcIDreply(comarrlistlist.getCID());  //子评论数量
                //获得被点赞的评论数据
                Commentlike comlike = commentlikeService.commlikecidlist(comarrlistlist.getCID(), comarrlistlist.getUID());
                if (comlike != null) {
                    commaplistmap.setAction(comlike.getStatus());  //点赞状态
                } else {
                    commaplistmap.setAction(null);
                }
                Messagecontext messagecontext = new Messagecontext(comarrlistlist.getCText());
                commaplistmap.setContent(messagecontext);     //评论内容对象
                commaplistmap.setCount(comcount);       //子评论数量
                commaplistmap.setCtime(comarrlistlist.getCreateTime());    //评论时间
                if (commentdata != null) {
                    commaplistmap.setLike(commentdata.getCLikeNum());   //点赞数
                } else {
                    commaplistmap.setLike(null);   //点赞数
                }
                ResponseData<Member> menmmap = dvnamicService.memberid(comarrlistlist.getUID());
                commaplistmap.setMember(menmmap.getData());//发表人评论id 对象
//
//                    commap.put("member", menmmap);

                List<Comment> comlist = commentService.selelistcIDreply(comarrlistlist.getCID()); //获取当前评论id下面的子评论
                List<Integer> cidarr = new ArrayList(); //子评论下面的cid集合
                for (Comment comlist2 : comlist) {
                    cidarr.add(comlist2.getCID());
                }
                List comarrlist_repl = new ArrayList();
                if (cidarr != null && cidarr.size() != 0) {
                    //通过子评论集合去查询子评论里面热度最高的数据取前三条
                    List<Commentdata> comdata = commentdataService.commdatalist(cidarr.toArray(new Integer[0]));
                    List<Integer> comlistcida = new ArrayList<>();  //热度最高前三条的cid集合
                    for (Commentdata commentdatalist : comdata) {
                        comlistcida.add(commentdatalist.getCID());
                    }
                    List<Comment> comarrlist = commentService.comarrlist(comlistcida.toArray(new Integer[0]));
                    for (Comment commentarr : comarrlist) {
                        //根据cid去拿子评论的点赞对象
                        Commentdata commentdataarr = commentdataService.selectcID(commentarr.getCID());
                        CommReplies commReplies = null;
                        if (commentdataarr != null) {
                            ResponseData<Member> dvmaps = dvnamicService.memberid(commentarr.getUID());
                            Messagecontext mm4 = new Messagecontext(commentarr.getCText());
                            commReplies = new CommReplies(mm4, commentarr.getCreateTime(),
                                    commentdataarr.getCLikeNum(), dvmaps.getData());
                        } else {
                            commReplies = new CommReplies(null, null, null, null);
                        }
                        comarrlist_repl.add(commReplies);
                    }
                }
                commaplistmap.setReplies(comarrlist_repl); //子评论集合
                maplist.add(commaplistmap);
            }
        } else {
            List<Comment> list = commentService.comdatalisttimepage(array, next - 1, ps); //通过评论id数组查询cIDreply=0的数据
            for (Comment comment : list) {
                //根据评论id查询评论点赞数据和点踩数据
                Commentdata commentdata = commentdataService.selectcID(comment.getCID());
                CommListMap commaps = new CommListMap();
                commaps.setRpid(comment.getCID()); //评论id
                Integer comcount = commentService.commcountcIDreply(comment.getCID());  //子评论数量
                //获得被点赞的评论数据
                Commentlike comlike = commentlikeService.commlikecidlist(comment.getCID(), comment.getUID());
                if (comlike != null) {
                    commaps.setAction(comlike.getStatus());  //点赞状态
                } else {
                    commaps.setAction(null);
                }
                Messagecontext mes = new Messagecontext(comment.getCText());
                commaps.setContent(mes);     //评论内容对象
                commaps.setCount(comcount);       //子评论数量
                commaps.setCtime(comment.getCreateTime());    //评论时间
                if (commentdata != null) {
                    commaps.setLike(commentdata.getCLikeNum());   //点赞数
                } else {
                    commaps.setLike(null);   //点赞数
                }
                ResponseData<Member> dvmap = dvnamicService.memberid(comment.getUID());
                commaps.setMember(dvmap.getData());//发表人评论id 对象

                List<Comment> comlist = commentService.selelistcIDreply(comment.getCID()); //获取当前评论id下面的子评论
                List<Integer> cidarr = new ArrayList(); //子评论下面的cid集合
                for (Comment comlist2 : comlist) {
                    cidarr.add(comlist2.getCID());
                }
                //通过子评论集合去查询子评论里面热度最高的数据取前三条
                List<Commentdata> comdata = commentdataService.commdatalist(cidarr.toArray(new Integer[0]));
                List<Integer> comlistcida = new ArrayList<>();  //热度最高前三条的cid集合
                for (Commentdata commentdatalist : comdata) {
                    comlistcida.add(commentdatalist.getCID());
                }
                List comarrlist_repl = new ArrayList();
                List<Comment> comarrlist = commentService.comarrlist(comlistcida.toArray(new Integer[0]));
                for (Comment commentarr : comarrlist) {
                    //根据cid去拿子评论的点赞对象
                    Commentdata commentdataarr = commentdataService.selectcID(commentarr.getCID());
                    CommReplies commReplies = null;
                    if (commentdataarr != null) {
                        ResponseData<Member> dvmaps = dvnamicService.memberid(commentarr.getUID()); //用户
                        Messagecontext mmn = new Messagecontext(commentarr.getCText());
                        commReplies = new CommReplies(mmn, commentarr.getCreateTime(),
                                commentdataarr.getCLikeNum(), dvmaps.getData());
//                            Map dvmap=dvnamicService.memberid(commentarr.getUID());
//                            comdatamap.put("member", dvmap);       //用户id//用户id
                    } else {
                        commReplies = new CommReplies(null, null, null, null);
                    }
                    comarrlist_repl.add(commReplies);
                }
                commaps.setReplies(comarrlist_repl); //子评论集合
                maplist.add(commaps);
            }
        }
        return new ResponseData(0, "", 0, maplist);
    }


    /**
     * 发表评论
     *
     * @param message 内容
     * @return error code
     * 1 空值
     * 2 数据插入未成功
     */
    @PostMapping("/reply/add")
    public ResponseData<Comment> replyAdd(String message, Integer uid) {
        int code = 0;
        String mess = "";
        Comment data = null;
        if (message != null && !message.equals("")) {
            Comment comment = new Comment(uid, 0, new Date(), message);
            if (commentService.save(comment)) {
                mess = "发送成功";
                data = comment;
            } else {
                code = 2;
                mess = "内部服务器错误";
            }
        } else {
            code = 1;
            mess = "空值异常";
        }

        return new ResponseData<>(code, mess, 1, data);
    }

    /**
     * 添加子评论
     *
     * @param cidrely 回复的评论id(父评论)
     * @param cText   评论正文
     * @return
     */
    @PostMapping("/insetcid")
    public ResponseData insetcid(Integer cidrely, String cText,HttpServletRequest request) {
        int code = 0;
        String mess = "";
        CommListMap data = null;
        ResponseData<Integer> logon_uid_result = safetyService.logon_uid(TokenUtil.getToken(request));
        if (cidrely != null && cText!=null && !cText.equals("")) {
            if (logon_uid_result.getCode() == 0) {
                Comment comment = new Comment(logon_uid_result.getData(), cidrely, new Date(), cText);
                if (commentService.save(comment)){
                    // 获取用户信息
                    ResponseData<Member> memberResponseData = usermapService.cardInfoById(logon_uid_result.getData());
                    Member member = null;
                    if (memberResponseData.getCode()==0){
                        member = memberResponseData.getData();
                    }else {
                        member = new Member();
                    }
                    data = new CommListMap(comment.getCID(),0,new Messagecontext(comment.getCText()),0,comment.getCreateTime(),0L,member,new ArrayList<>());
                }
            }else {
                code = 2;
                mess = "请先登录";
            }
        } else {
            code = 1;
            mess = "空值异常";
        }
        return new ResponseData(code, mess, 0, data);
    }
}

