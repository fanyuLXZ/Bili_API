package com.dreamwolf.message.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.comment.Comment;
import com.dreamwolf.entity.comment.Commentlike;
import com.dreamwolf.entity.comment.web_interface.Commcidmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "comment-service")
public interface CommentService {

    /**
     * 根据cid数组查询并且cIDreply=0的则是动态或者视频
     * @param array
     * @return
     */
    @GetMapping("/comseletcilll")
    public List<Comment> commcidlists(@RequestParam Integer[] array);


    /**
     * 根据uid用户查询评论表发布的评论数据
     * @return
     */
//    @GetMapping("/selecomuid")
//    public List<Comment> selecomuid(@RequestParam Integer uid);

    @GetMapping("/selecomuid")
    public ResponseData<List<Comment>> selecomuid(@RequestParam Integer uid);

    /**
     * 根据评论主键cid查询数据
     * @param cid
     * @return
     */
//    @GetMapping("/selecomcid")
//    public List<Comment> selecomcid(@RequestParam Integer cid);

    @GetMapping("/selecomcid")
    public ResponseData<List<Comment>> selecomcid(@RequestParam Integer cid);

    /**
     * 根据评论id查询评论数据
     * @param cid
     * @return
     */
//    @GetMapping("/commcidlistmap")
//    public Map<String,Map<String,Object>> commcidlist(@RequestParam Integer cid);

    @GetMapping("/commcidlistmap")
    public ResponseData<Commcidmap> commcidlist(@RequestParam Integer cid);

    /**
     * 根据评论id查询评论总数
     * @param arr
     * @return
     */
    @GetMapping("/commcidcount")
    public Integer selebvidint(@RequestParam Integer[] arr);

    /**
     * 根据当前用户id查询用户下的评论
     * @param uid
     * @return
     */
//    @GetMapping("/commuidlist")
//    public List<Integer> selectuidlisst(@RequestParam Integer uid);

    @GetMapping("/commuidlist")
    public ResponseData<List<Integer>> selectuidlisst(@RequestParam Integer uid);

    /**
     * 根据评论id查询评论点赞表的数据
     * @param arr
     * @return
     */
//    @GetMapping("/selectlikearr")
//    public List<Commentlike> selectarrlist(@RequestParam Integer[] arr);

    @GetMapping("/selectlikearr")
    public ResponseData<List<Commentlike>> selectarrlist(@RequestParam Integer[] arr);

    /**
     * 根据评论id表主键数组查询评论id数据
     * @param array
     * @return
     */
//    @GetMapping("/commcidarray")
//    public List<Comment> commentsarrlist(@RequestParam Integer[] array);

    @GetMapping("/commcidarray")
    public ResponseData<List<Comment>> commentsarrlist(@RequestParam Integer[] array);

}
