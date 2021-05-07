package com.dreamwolf.dynamic.business.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.comment.web_interface.CommListMap;
import com.dreamwolf.entity.comment.web_interface.Commcidmap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "comment-service")

public interface CommentService {
    @GetMapping("/commselectcarrlist")
    public ResponseData<List<CommListMap>> commselecarlist(@RequestParam Integer id, @RequestParam Integer[] array);

    @GetMapping("/commcidlistmap")
    public ResponseData<Commcidmap> commcidlist(@RequestParam Integer cid);

    @GetMapping("/commselectcarrlistpage")
    public ResponseData<List<CommListMap>> commselecarlistpage(@RequestParam Integer id, @RequestParam Integer[] array, @RequestParam Integer next);
    }
