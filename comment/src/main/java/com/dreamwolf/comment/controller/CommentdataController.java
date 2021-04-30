package com.dreamwolf.comment.controller;


import com.dreamwolf.comment.pojo.Comment;
import com.dreamwolf.comment.pojo.Commentdata;
import com.dreamwolf.comment.service.CommentService;
import com.dreamwolf.comment.service.CommentdataService;
import com.dreamwolf.gateway.entity.ResponseData;
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
    public ResponseData<Commentdata> selectucid(Integer cID){
        ResponseData<Commentdata> responseData = new ResponseData<>();
        Commentdata commentdate = commentdataService.selectcID(cID);
        if(commentdate !=null && cID!=null){
            responseData.setData(commentdate);
        }else {
            responseData.setCode(400);
            responseData.setMessage("commentdate对象为空或参数cid异常");
            responseData.setData(null);
        }
        return responseData;
    }

}

