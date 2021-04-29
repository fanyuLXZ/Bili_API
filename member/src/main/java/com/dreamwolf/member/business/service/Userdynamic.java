package com.dreamwolf.member.business.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name ="dynamic-service")
public interface Userdynamic {

    @GetMapping("/replyitem")
    public List<Map<String,Object>> replyitem(@RequestParam Integer userdy);
}
