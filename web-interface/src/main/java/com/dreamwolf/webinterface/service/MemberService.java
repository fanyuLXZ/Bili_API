package com.dreamwolf.webinterface.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="member-service")
public interface MemberService {
    @GetMapping("/card/info/{aid}")
    ResponseData<Member> cardInfoById(@PathVariable Integer aid);
}
