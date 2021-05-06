package com.dreamwolf.comment.controller;

import com.dreamwolf.comment.service.CommentdataService;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.comment.Commentdata;
import org.springframework.web.bind.annotation.GetMapping;

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
        Commentdata commentdate = commentdataService.selectcID(cID);
        return new ResponseData(0,"",0,commentdate);
    }

}

