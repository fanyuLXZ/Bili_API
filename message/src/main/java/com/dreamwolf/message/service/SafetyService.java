package com.dreamwolf.message.service;

import com.dreamwolf.entity.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "safety-service")
public interface SafetyService {
    @GetMapping("/logon-uid")
    ResponseData<Integer> logon_uid(String token);
}
