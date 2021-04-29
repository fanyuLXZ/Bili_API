package com.dreamwolf.comment.controller;


import com.dreamwolf.comment.pojo.Comment;
import com.dreamwolf.comment.pojo.Commentdata;
import com.dreamwolf.comment.pojo.Commentlike;
import com.dreamwolf.comment.service.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户评论表
 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
@RestController
public class CommentController {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

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

    /**
     *
     * 评论回复信息
     *  rpid 评论id int
     *  pn 页码 int
     *  ps 每页总条数 int
     * @param rpid
     * @return
     */
    @GetMapping("/reply")
    public Map selectrepd(Integer rpid,Integer pn,Integer ps){
        Map map = new HashMap();
        if(map!=null && rpid!=null && pn!=null && ps !=null){
            List<Comment> list = commentService.selectrpid(rpid,pn,ps);  //根据评论id查询子评论数并分页处理
            if(list !=null) {
                Map remap = new HashMap();
                List comlist = new ArrayList();
                for (Comment com : list) {
                    Map maplist = new HashMap();
                    maplist.put("id", com.getCID());
                    maplist.put("rpid", com.getCIDreply()); //回复评论id
                    Integer count = commentService.commcountcIDreply(com.getCIDreply());    //子评论总数
                    Map mappage = new HashMap();
                    mappage.put("count",count);  //子评论总数
                    mappage.put("num",pn);  //页码
                    mappage.put("size",ps); //每页总条数
                    maplist.put("page",mappage);
                        Map usermap = usermapService.membe(com.getUID());   //用户对象
                    maplist.put("member",usermap);     //评论的用户id调接口返回对象
                    maplist.put("like", com.getCommentdata().getCLikeNum()); //评论点赞数
                    maplist.put("dislike", com.getCommentdata().getCUnLikeNum()); //评论点踩数
                    maplist.put("ctime", com.getCreateTime()); //评论时间
                        Map cmap = new HashMap();
                        cmap.put("message",com.getCText());
                    maplist.put("content", cmap); //评论内容
                    comlist.add(maplist);
                }
                map.put("code",0);
                map.put("message",null);
                remap.put("replies",comlist);
                map.put("data",remap);
            }else{
                map.put("replies","list为空");
            }
        }else{
            map.put("code",400);
            map.put("message","map为空或rpid参数为空或页码为空或每页总条数为空");
            map.put("replies",null);
        }

        return map;
    }

    /***
     * 根据评论id数组查询评论总数
     * @param arr
     * @return
     */
    @GetMapping("/commcidcount")
    public Integer selebvidint(Integer[] arr){
//        Integer[] a = new Integer[]{1,2,3};
        return commentService.selectbvidint(arr);
    }

    /**
     * 根据当前用户id查询用户下的评论id
     * @param uid
     * @return
     */
        @GetMapping("/commuidlist")
    public List<Integer> selectuidlisst(Integer uid){
        return commentService.selectuidlist(uid);
    }

    /**
     * 根据点赞评论id数组查询评论id基本数据
     * @param array
     * @return
     */
    @GetMapping("/commcidarray")
    public List<Comment> commentsarrlist(Integer[] array){
        return commentService.commentuidlist(array);
    }


    /**
     * 根据评论id查询评论数据
     * @param cid
     * @return
     */
    @GetMapping("/commcidlistmap")
    public Map<String,Map<String,Object>> commcidlist(Integer cid){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map map = new HashMap();
        Comment comment = commentService.commcidlist(cid);
        if(cid != null && comment != null){
            Map commap  = new HashMap();
            commap.put("source_content",comment.getCText());
            commap.put("reply_time",simpleDateFormat.format(comment.getCreateTime()));
            commap.put("uid",comment.getUID());
            map.put("data",commap);

        }else{
            map.put("data",null);
            map.put("code",400);
            map.put("message","参数cid为空或者map为空或者查询的数据为空");
        }
        return map;
    }

    /**
     * 根据cid数组查询并且cIDreply=0的则是动态或者视频
     * @param array
     * @return
     */
    @GetMapping("/comseletcilll")
    public List<Comment> comseletcidli(Integer[] array){
        return commentService.commselectarrcidlist(array);
    }


    /**
     * 根据uid用户查询评论表发布的评论数据
     * @return
     */
    @GetMapping("/selecomuid")
    public List<Comment> selecomuid(Integer uid){
        return commentService.commselectlistuid(uid);
    }

    /**
     * 根据评论主键cid查询数据
     * @param cid
     * @return
     */
    @GetMapping("/selecomcid")
    public List<Comment> selecomcid(Integer cid){
        return commentService.commselectlistcid(cid);
    }


    /**
     * 根据cid评论数组并且回复的评论id,cIDreply=0则是视频或者动态 id ：1 为热度排序  2 为时间排序
     * @return
     */
    @GetMapping("/commselectcarrlist")
    public List<Map<String,Object>> commselecarlist(Integer id,Integer[] array){
//        Integer[] array = new Integer[]{1,2,3};
        //  id 1 为热度排序  2 为时间排序
        List<Map<String,Object>> maplist = new ArrayList();
        if(id !=null && array!=null && array.length>0) {
            //热度排序
            if (id == 1) {
                List<Comment> list = commentService.commentuidlist(array); //通过评论id数组查询cIDreply=0的数据
                List<Integer> comarrlistss = new ArrayList<>();    //评论id数组
                for (Comment comment : list) {
                    comarrlistss.add(comment.getCID());   //评论id
                }
                //根据评论id数组拿到前10条的热度 评论点赞表
                List<Commentdata> comdatlist = commentdataService.commdatalistarr(comarrlistss.toArray(new Integer[0]));

                //拿到评论点赞表热度前10的id
                List<Integer> clist = new ArrayList<>();
                for (Commentdata comdatacom : comdatlist) {
                    clist.add(comdatacom.getCID());
                }

                //根据热度前10 cid数组查询评论表数据
                List<Comment> comlistlist = commentService.comarrlist(clist.toArray(new Integer[0]));
                for (Comment comarrlistlist : comlistlist) {
                    //根据评论id查询评论点赞数据和点踩数据
                    Commentdata commentdata = commentdataService.selectcID(comarrlistlist.getCID());
                    Map commap = new HashMap();
                    Integer comcount = commentService.commcountcIDreply(comarrlistlist.getCID());  //子评论数量
                    //获得被点赞的评论数据
                    Commentlike comlike = commentlikeService.commlikecidlist(comarrlistlist.getCID(), comarrlistlist.getUID());
                    if (comlike != null) {
                        commap.put("action", comlike.getStatus());
                    } else {
                        commap.put("action", null);
                    }
                    Map comtent = new HashMap();
                    comtent.put("message", comarrlistlist.getCText());
                    commap.put("content", comtent);     //评论内容对象
                    commap.put("count", comcount);       //子评论数量
                    commap.put("ctime", simpleDateFormat.format(comarrlistlist.getCreateTime()));    //评论时间
                    commap.put("like", commentdata.getCLikeNum());   //点赞数
                    Map menmmap = dvnamicService.memberid(comarrlistlist.getUID());
                    commap.put("member", menmmap);   //发表人评论id 对象

                    List<Comment> comlist = commentService.selelistcIDreply(comarrlistlist.getCID()); //获取当前评论id下面的子评论
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
                        Map comdatamap = new HashMap();
                        if (commentdataarr != null) {
                            Map conmap = new HashMap();
                            conmap.put("message", commentarr.getCText());
                            comdatamap.put("content", conmap);   //回复内容
                            comdatamap.put("ctime",simpleDateFormat.format( commentarr.getCreateTime()));     //回复评论时间
                            comdatamap.put("like", commentdataarr.getCLikeNum());    //点赞数量
                            Map dvmap=dvnamicService.memberid(commentarr.getUID());
                            comdatamap.put("member", dvmap);       //用户对象
                        } else {
                            comdatamap.put("为空", null);
                        }
                        comarrlist_repl.add(comdatamap);
                    }
                    commap.put("replies", comarrlist_repl); //子评论集合
                    maplist.add(commap);
                }
            } else {
                List<Comment> list = commentService.comdatalisttime(array); //通过评论id数组查询cIDreply=0的数据
//            List<Integer> comarrlistss = new ArrayList<>();    //评论id数组
                for (Comment comment : list) {
                    //根据评论id查询评论点赞数据和点踩数据
                    Commentdata commentdata = commentdataService.selectcID(comment.getCID());
                    Map commap = new HashMap();
                    Integer comcount = commentService.commcountcIDreply(comment.getCID());  //子评论数量
                    //获得被点赞的评论数据
                    Commentlike comlike = commentlikeService.commlikecidlist(comment.getCID(), comment.getUID());
                    if (comlike != null) {
                        commap.put("action", comlike.getStatus());
                    } else {
                        commap.put("action", null);
                    }
                    Map comtent = new HashMap();
                    comtent.put("message", comment.getCText());
                    commap.put("content", comtent);     //评论内容对象
                    commap.put("count", comcount);       //子评论数量
                    commap.put("ctime",simpleDateFormat.format( comment.getCreateTime()));    //评论时间
                    commap.put("like", commentdata.getCLikeNum());   //点赞数
                    Map dvmap=dvnamicService.memberid(comment.getUID());
                    commap.put("member", dvmap);   //发表人评论id 对象

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
                        Map comdatamap = new HashMap();
                        if (commentdataarr != null) {
                            Map conmap = new HashMap();
                            conmap.put("message", commentarr.getCText());
                            comdatamap.put("content", conmap);   //回复内容
                            comdatamap.put("ctime", simpleDateFormat.format(commentarr.getCreateTime()));     //回复评论时间
                            comdatamap.put("like", commentdataarr.getCLikeNum());    //点赞数量
                            Map dvmaps=dvnamicService.memberid(commentarr.getUID());
                            comdatamap.put("member", dvmaps);       //用户id
                        } else {
                            comdatamap.put("为空", null);
                        }
                        comarrlist_repl.add(comdatamap);
                    }
                    commap.put("replies", comarrlist_repl); //子评论集合
                    maplist.add(commap);
                }
            }
        }else{
            Map map = new HashMap();
            map.put("code",400);
            map.put("message","id为空或者array为空没数据");
            maplist.add(map);
        }
        return maplist;
    }





    /**
     * 根据cid评论数组并且回复的评论id,cIDreply=0则是视频或者动态 id ：1 为热度排序  2 为时间排序 并分页处理
     * next 页码 int
     * @return
     */
    @GetMapping("/commselectcarrlistpage")
    public List<Map<String,Object>> commselecarlistpage(Integer id,Integer[] array,Integer next){
//        Integer[] array = new Integer[]{1};
        //  id 1 为热度排序  2 为时间排序
        List<Map<String,Object>> maplist = new ArrayList();
        if(id !=null && array!=null && array.length>0 && next !=null) {
            //热度排序
            if (id == 1) {
                //通过评论id数组查询cIDreply=0的数据
                List<Comment> list = commentService.commentuidlist(array);
                List<Integer> comarrlistss = new ArrayList<>();    //评论id数组
                for (Comment comment : list) {
                    comarrlistss.add(comment.getCID());   //评论id
                }
                //根据评论id数组拿到前10条的热度 评论点赞表 分页
                List<Commentdata> comdatlist = commentdataService.commdatalistpage(comarrlistss.toArray(new Integer[0]),next);
                List<Integer> clist = new ArrayList<>();
//                if(comdatlist!=null){
                    //拿到评论点赞表热度前10的id数组
                    for (Commentdata comdatacom : comdatlist) {
                        clist.add(comdatacom.getCID());
                    }
//                }else {
//                    clist.add(1);
//                }


                //根据热度前10 cid数组查询评论表数据
                List<Comment> comlistlist = commentService.comarrlist(clist.toArray(new Integer[0]));
                for (Comment comarrlistlist : comlistlist) {
                    //根据评论id查询评论点赞数据和点踩数据
                    Commentdata commentdata = commentdataService.selectcID(comarrlistlist.getCID());
                    Map commap = new HashMap();
                    Integer comcount = commentService.commcountcIDreply(comarrlistlist.getCID());  //子评论数量
                    //获得被点赞的评论数据
                    Commentlike comlike = commentlikeService.commlikecidlist(comarrlistlist.getCID(), comarrlistlist.getUID());
                    if (comlike != null) {
                        commap.put("action", comlike.getStatus());  //点赞状态
                    } else {
                        commap.put("action", null);
                    }

                    Map comtent = new HashMap();
                    comtent.put("message", comarrlistlist.getCText());
                    commap.put("content", comtent);     //评论内容对象
                    commap.put("count", comcount);       //子评论数量
                    commap.put("ctime", simpleDateFormat.format(comarrlistlist.getCreateTime()));    //评论时间
                    if(commentdata !=null){
                        commap.put("like", commentdata.getCLikeNum());   //点赞数
                    }else {
                        commap.put("like", null);   //点赞数
                    }

                    Map menmmap = dvnamicService.memberid(comarrlistlist.getUID());
                    commap.put("member", menmmap);   //发表人评论id 对象

                    List<Comment> comlist = commentService.selelistcIDreply(comarrlistlist.getCID()); //获取当前评论id下面的子评论
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
                        Map comdatamap = new HashMap();
                        if (commentdataarr != null) {
                            Map conmap = new HashMap();
                            conmap.put("message", commentarr.getCText());
                            comdatamap.put("content", conmap);   //回复内容
                            comdatamap.put("ctime",simpleDateFormat.format( commentarr.getCreateTime()));     //回复评论时间
                            if(commentdataarr !=null){
                                comdatamap.put("like", commentdataarr.getCLikeNum());    //点赞数量
                            }else {
                                comdatamap.put("like", null);    //点赞数量
                            }

                            Map dvmap=dvnamicService.memberid(commentarr.getUID());
                            comdatamap.put("member", dvmap);       //用户id
                        } else {
                            comdatamap.put("为空", null);
                        }
                        comarrlist_repl.add(comdatamap);
                    }
                    commap.put("replies", comarrlist_repl); //子评论集合
                    maplist.add(commap);
                }
            } else {
                List<Comment> list = commentService.comdatalisttimepage(array,next); //通过评论id数组查询cIDreply=0的数据
                if(list!=null) {
//            List<Integer> comarrlistss = new ArrayList<>();    //评论id数组
                    for (Comment comment : list) {
                        //根据评论id查询评论点赞数据和点踩数据
                        Commentdata commentdata = commentdataService.selectcID(comment.getCID());
                        Map commap = new HashMap();
                        Integer comcount = commentService.commcountcIDreply(comment.getCID());  //子评论数量
                        //获得被点赞的评论数据
                        Commentlike comlike = commentlikeService.commlikecidlist(comment.getCID(), comment.getUID());
                        if (comlike != null) {
                            commap.put("action", comlike.getStatus());  //点赞状态
                        } else {
                            commap.put("action", null);
                        }

                        Map comtent = new HashMap();
                        comtent.put("message", comment.getCText());
                        commap.put("content", comtent);     //评论内容对象
                        commap.put("count", comcount);       //子评论数量
                        commap.put("ctime", simpleDateFormat.format(comment.getCreateTime()));    //评论时间
                        if (commentdata != null) {
                            commap.put("like", commentdata.getCLikeNum());   //点赞数
                        } else {
                            commap.put("like", null);   //点赞数
                        }
                        Map dvmap = dvnamicService.memberid(comment.getUID());
                        commap.put("member", dvmap);   //发表人评论id 对象

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
                            Map comdatamap = new HashMap();
                            if (commentdataarr != null) {
                                Map conmap = new HashMap();
                                conmap.put("message", commentarr.getCText());
                                comdatamap.put("content", conmap);   //回复内容
                                comdatamap.put("ctime", simpleDateFormat.format(commentarr.getCreateTime()));     //回复评论时间
                                if (commentdataarr != null) {
                                    commap.put("like", commentdataarr.getCLikeNum());   //点赞数
                                } else {
                                    commap.put("like", null);   //点赞数
                                }
                                Map dvmaps = dvnamicService.memberid(commentarr.getUID());
                                comdatamap.put("member", dvmaps);       //用户id
                            } else {
                                comdatamap.put("为空", null);
                            }
                            comarrlist_repl.add(comdatamap);
                        }
                        commap.put("replies", comarrlist_repl); //子评论集合
                        maplist.add(commap);
                    }
                }else {
                    Map map = new HashMap();
                    map.put("message", "list为空");
                    maplist.add(map);
                }
            }
        }else{
            Map map = new HashMap();
            map.put("message","id为空或者array或next为空");
            maplist.add(map);
        }
        return maplist;
    }




}

