package com.dreamwolf.safety.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.User;
import com.dreamwolf.entity.member.UserVerify;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "member-service")
public interface MemberService {
    @PostMapping("/user/verify")
    ResponseData<UserVerify> verify(@RequestParam String username, @RequestParam String password);

    @PostMapping("/register")
    ResponseData<User> register(@RequestParam String nickname,@RequestParam String password,@RequestParam String phone);

    @PostMapping("/add_coin")
    ResponseData<Boolean> addCoin(@RequestParam Integer uid);
}