package com.dreamwolf.message.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "member-service")
public interface MemberService {

    @GetMapping("/users")
    public List<Map<String,Object>> users(@RequestParam Integer[] list, @RequestParam Integer uid);

    @GetMapping("/data")
    public List<Map<String,Object>> list(@RequestParam Integer id);

    @GetMapping("/replyuserb")
    public Map<String,Object> replyuserb(@RequestParam Integer uid,@RequestParam Integer id);//回复人  和原评论

}
