package com.dreamwolf.fav.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.video.web_interface.VideoCount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 接口调接口，从video获取收藏夹下面的视频数
 */
@FeignClient(name = "video-service")
public interface Videofav {

    /**
     * 根据收藏夹id查询收藏夹下的视频
     * @param favListID
     * @return
     */
    @PostMapping("/videocount")
    public ResponseData<VideoCount> selectint(@RequestParam Integer favListID);

}
