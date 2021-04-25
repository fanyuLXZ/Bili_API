package com.dreamwolf.comment.controller;


import com.dreamwolf.comment.pojo.Comment;
import com.dreamwolf.comment.service.CommentService;
import com.dreamwolf.comment.service.UsermapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @Resource
    private CommentService commentService;

    @Resource
    private UsermapService usermapService;

    @GetMapping("/commentlist")
    public Map selectrepd(Integer rpid){
        Map map = new HashMap();
        if(map!=null && rpid!=null){
            List<Comment> list = commentService.selectrpid(rpid);
            if(list !=null) {
                List comlist = new ArrayList();
                for (Comment com : list) {
                    Map maplist = new HashMap();
                    maplist.put("id", com.getCID());
                    maplist.put("rpid", com.getCIDreply()); //回复评论id
                    Map usermap = usermapService.membe(com.getUID());
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
                map.put("replies",comlist);
            }else{
                map.put("replies","list为空");
            }
        }else{
            map.put("code",400);
            map.put("message","map为空或rpid参数为空");
            map.put("replies",null);
        }

        return map;
    }

    @GetMapping("/commcidcount")
    public Integer selebvidint(Integer[] arr){
//        Integer[] a = new Integer[]{1,2,3};
        return commentService.selectbvidint(arr);
    }

    /**
     * 根据当前用户id查询用户下的评论
     * @param uid
     * @return
     */
    @GetMapping("/commuidlist")
    public List<Integer> selectuidlisst(Integer uid){
        return commentService.selectuidlist(uid);
    }

    /**
     * 跟你点赞评论id数组查询评论id数据
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
    public Map<String,Comment> commcidlist(Integer cid){
        Map map = new HashMap();
        Comment comment = commentService.commcidlist(cid);
        map.put("data",comment);
        return map;
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

}

