package com.dreamwolf.video.service;

import com.dreamwolf.zoning.business.entity.web_interface.Deputydivision;
import com.dreamwolf.zoning.business.entity.web_interface.Mainpartition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Map;

/**
 * 接口调接口。从分区模块接收子分区
 */
@FeignClient(name = "zoning-service")
public interface UserpageService {

    @GetMapping("/selisy")
    public Map selectlistid(@RequestParam ArrayList<Integer> list, @RequestParam Integer count, @RequestParam Integer ps);

    @GetMapping("elementby")
    public String elementby(@RequestParam Integer zID); //根据分区id拿到分区名

    @GetMapping("/mainpartition")
    public Mainpartition mainpartition(@RequestParam Integer bvChildZoning);

    @GetMapping("/deputydivision")
    public Deputydivision deputydivision(@RequestParam Integer bvChildZoning);





}
