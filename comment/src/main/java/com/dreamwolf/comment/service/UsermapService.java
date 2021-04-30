package com.dreamwolf.comment.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "member-service")
public interface UsermapService {

    @GetMapping("/membe")
    public Map membe(@RequestParam Integer uID);




}
