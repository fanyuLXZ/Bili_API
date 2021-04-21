package com.dreamwolf.fav.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 接口调接口，从video获取收藏夹下面的视频数
 */
//@FeignClient(name = "video-service")
public interface Videofav {

//    @PostMapping("/video/videocount")
//    public int selectfavListID(@RequestParam Integer favListID);
}
