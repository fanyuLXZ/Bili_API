package com.dreamwolf.video.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.comment.Comment;
import com.dreamwolf.entity.comment.web_interface.CommListMap;
import com.dreamwolf.entity.comment.web_interface.Commcidmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@FeignClient(name = "comment-service")

public interface CommentService {
    @GetMapping("/commselectcarrlistpage")
    public ResponseData<List<CommListMap>> commselecarlistpage(@RequestParam Integer id,@RequestParam Integer[] array,@RequestParam Integer next,@RequestParam Integer ps);

    @PostMapping("/reply/add")
    public ResponseData<Comment> replyAdd(@RequestParam String message,@RequestParam Integer uid);
}
