package com.dreamwolf.member.business.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.message.web_interface.MMItems;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name ="dynamic-service")
public interface Userdynamic {

    @GetMapping("/replyitem")
    public ResponseData<List<MMItems>> replyitem(@RequestParam Integer userdy);
}
