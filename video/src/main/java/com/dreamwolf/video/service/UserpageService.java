package com.dreamwolf.video.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 接口调接口。从分区模块接收子分区
 */
@FeignClient(name = "zoning-service")
public interface UserpageService {

    @GetMapping("/selisy")
    public Map selectlistid(@RequestParam ArrayList<Integer> list, @RequestParam Integer count, @RequestParam Integer ps);

    @GetMapping("elementby")
    public String elementby(@RequestParam Integer zID);




}
