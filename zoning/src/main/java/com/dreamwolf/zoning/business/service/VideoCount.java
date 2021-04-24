package com.dreamwolf.zoning.business.service;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@FeignClient(name = "video-service")
public interface VideoCount {

    @GetMapping("/videocount")
    public Integer vcount(@RequestParam Integer[] list);

    @GetMapping("/videopage")
    public Map<String, Object> videopage(@RequestParam Integer[] list,@RequestParam Integer count,@RequestParam Integer ps);

    @GetMapping("/videodeorating")
    public Map<String, Object> selectdeorating(@RequestParam Integer[] bvChildZoning,@RequestParam Integer datetime);

    @GetMapping("/videoseldate")
    public Map<String, Object> selmap(@RequestParam String str);
}
