package com.dreamwolf.comment.service;

import com.dreamwolf.entity.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "safety-service")
public interface SafetyService {
    @GetMapping("/logon-uid")
    ResponseData<Integer> logon_uid(@RequestParam String token);
}
