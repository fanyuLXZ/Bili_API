package com.dreamwolf.zoning.business.service;

import com.dreamwolf.entity.video.web_interface.ArchivesInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "video-service")
public interface VideoCount {
    /**
     * 根据子分区id查询视频总数
     * @param rid
     * @return
     */
    @GetMapping("lectidcoutn")
    public Integer selectidcoutn(@RequestParam Integer rid);

    @GetMapping("ideoridlists")
    public List<ArchivesInfo> selectvideorid(@RequestParam Integer rid,@RequestParam Integer pn,@RequestParam Integer ps);

    @GetMapping("/videocount")
    public Integer vcount(@RequestParam Integer[] list);

    @GetMapping("/videopage")
    public Map<String, Object> videopage(@RequestParam Integer[] list,@RequestParam Integer count,@RequestParam Integer ps);

    @GetMapping("/videodeorating")
    public Map<String, Object> selectdeorating(@RequestParam Integer[] bvChildZoning,@RequestParam Integer datetime);

    @GetMapping("/videoseldate")
    public Map<String, Object> selmap(@RequestParam String str);

    //提前写好
    @GetMapping("/videomap")
    public List<Map<String,Object>> videolistmap(@RequestParam Integer rid);

}
