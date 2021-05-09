package com.dreamwolf.message.service;


import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.dynamic.IikesItems;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "dynamic-service")
public interface DynamicService {

//    @RequestMapping("/likesitems")
//    public List<Map<String,Object>> likeitems(@RequestParam Integer id);

    @RequestMapping("/likesitems")
    public ResponseData<List<IikesItems>> likeitems(@RequestParam Integer id);



}
