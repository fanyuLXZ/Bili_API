package com.dreamwolf.watchhistory.business.service;


import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "member-service")
public interface UserService {
    @GetMapping("/useruid")
    public ResponseData<User> userid(@RequestParam Integer uid);
}
