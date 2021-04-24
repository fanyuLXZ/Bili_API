package com.dreamwolf.comment.controller;


import com.dreamwolf.comment.pojo.Comment;
import com.dreamwolf.comment.pojo.Commentdata;
import com.dreamwolf.comment.service.CommentService;
import com.dreamwolf.comment.service.CommentdataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 评论数据表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
@RestController
public class CommentdataController {


    @Resource
    private CommentdataService commentdataService;

    @GetMapping("/commdatacid")
    public Map selectucid(Integer cID){
        Map map = new HashMap();
        Commentdata commentdate = commentdataService.selectcID(cID);
        if(map!=null && commentdate !=null && cID!=null){
            map.put("data",commentdate);
        }else {
            map.put("code",400);
            map.put("message","commentdate对象为空或参数cid异常");
            map.put("data",null);
        }
        return map;
    }

}

