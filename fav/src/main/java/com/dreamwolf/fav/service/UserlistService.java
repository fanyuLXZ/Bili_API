package com.dreamwolf.fav.service;

import com.dreamwolf.fav.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *接口调接口，调用user
 */
@FeignClient(name = "member-service")
public interface UserlistService {

    @PostMapping("/User/id")
    public User selectuid(@RequestParam Integer id);

}
