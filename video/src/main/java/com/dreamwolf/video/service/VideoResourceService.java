package com.dreamwolf.video.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.videoresource.web_inerface.VideoInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "video-resource-service")
public interface VideoResourceService {
    @GetMapping("/verify")
    ResponseData<VideoInfo> verify(@RequestParam String videoPath);
}
