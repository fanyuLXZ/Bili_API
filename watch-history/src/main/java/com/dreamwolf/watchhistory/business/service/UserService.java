package com.dreamwolf.watchhistory.business.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "member-service")
public interface UserService {
    @RequestMapping("/User")
    public Map<String,Object> user(@RequestParam Integer uid);
}
