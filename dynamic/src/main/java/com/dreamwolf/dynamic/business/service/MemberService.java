package com.dreamwolf.dynamic.business.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "member-service")
public interface MemberService {
    @PostMapping("/bang")
    Map verify(@RequestParam Integer id);
}
