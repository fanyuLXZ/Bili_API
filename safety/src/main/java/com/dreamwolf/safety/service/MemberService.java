package com.dreamwolf.safety.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "member-service")
public interface MemberService {
    @PostMapping("/user/verify")
    Map verify(@RequestParam String username,@RequestParam String password);

}
