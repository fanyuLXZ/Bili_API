package com.dreamwolf.watchhistory.business.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "video-service")
public interface VideoList {

    @GetMapping("/videoBvidlist")
    public List<Map<String,Object>> selectbbid(@RequestParam Integer[] bvidlist);


}

