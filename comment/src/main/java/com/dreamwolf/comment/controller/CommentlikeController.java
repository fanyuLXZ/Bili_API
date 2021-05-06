package com.dreamwolf.comment.controller;

import com.dreamwolf.comment.service.CommentlikeService;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.comment.Commentlike;
import com.dreamwolf.entity.comment.web_interface.CommentList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CommentlikeController {


    @Resource
    private CommentlikeService commentlikeService;


    /**
     * 根据被点赞评论id查询评论点赞表的数据
     * @param arr
     * @return
     */
    @GetMapping("/selectlikearr")
    public ResponseData<List<Commentlike>> selectarrlist(Integer[] arr)
    {
        List<Commentlike> commentlikes = commentlikeService.selectlikelsit(arr);
//        CommentList commentList = new CommentList(commentlikes);
        return new ResponseData(0,"",0,commentlikes);
    }


}
