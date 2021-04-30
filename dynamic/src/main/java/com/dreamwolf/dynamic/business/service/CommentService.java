package com.dreamwolf.dynamic.business.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "comment-service")

public interface CommentService {
    @GetMapping("/commselectcarrlist")
    public List<Map<String,Object>> commselecarlist(@RequestParam Integer id, @RequestParam Integer[] array);

    @GetMapping("/commcidlistmap")
    public Map<String,Map<String,Object>> commcidlist(@RequestParam Integer cid);

    @GetMapping("/commselectcarrlistpage")
    public List<Map<String,Object>> commselecarlistpage(@RequestParam Integer id,@RequestParam Integer[] array,@RequestParam Integer next);
    }
