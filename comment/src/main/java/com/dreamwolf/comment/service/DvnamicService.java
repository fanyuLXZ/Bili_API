package com.dreamwolf.comment.service;


import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "dynamic-service")
public interface DvnamicService {

    //member发表评论人对象
    @GetMapping("/memberid")
    public ResponseData<Member> memberid(@RequestParam Integer id);

}
