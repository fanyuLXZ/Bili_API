package com.dreamwolf.zoning.business.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.video.web_interface.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "video-service")
public interface VideoCount {

    @GetMapping("/videoridcountselec")
    public  ResponseData<Kele> videoridcountselec(@RequestParam Integer rid, @RequestParam Integer[] array, @RequestParam String datetime);

    /**
     * 根据子分区id查询视频总数
     * @param rid
     * @return
     */
    @GetMapping("selectidcoutn")
    public Integer selectidcoutn(@RequestParam Integer rid);

    @GetMapping("/videoridlists")
    public ResponseData<List<ArchivesInfo>> selectvideorid(@RequestParam Integer rid,@RequestParam Integer pn,@RequestParam Integer ps);

    @GetMapping("/videocount")
    public ResponseData<Integer> selcount(@RequestParam Integer[] list);

    @GetMapping("/videopage")
    public ResponseData<List<VideoMaplist>> videopage(@RequestParam Integer[] list, @RequestParam Integer count, @RequestParam Integer ps);

    @GetMapping("/videodeorating")
    public ResponseData<List<VideoMaplist>>  selectdeorating(@RequestParam Integer[] bvChildZoning,@RequestParam Integer datetime);

    @GetMapping("/videoseldate")
    public ResponseData selmap(@RequestParam String str);

    //提前写好
    @GetMapping("/videomap")
    public List<Map<String,Object>> videolistmap(@RequestParam Integer rid);

    @GetMapping("/selectbvidlistpagerid")
    public ResponseData<List<Result>> selectbvidlistpage(@RequestParam Integer rid,@RequestParam Integer pn,@RequestParam Integer ps);

    @GetMapping("selectbvidlistpagelist")
    public ResponseData<List<Region>> selectbvidlistpagelist(@RequestParam Integer rid);

}
