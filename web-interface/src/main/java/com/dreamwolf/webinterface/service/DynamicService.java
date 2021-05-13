package com.dreamwolf.webinterface.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.dynamic.web_interface.ItemsNumObject;
import com.dreamwolf.entity.message.web_interface.MMItems;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name ="dynamic-service")
public interface DynamicService {

    @GetMapping("/dynamic_num")
    public ResponseData<ItemsNumObject> dynamic_num(@RequestParam int[] uids);
}
