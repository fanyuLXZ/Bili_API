package com.dreamwolf.fav.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.User;
import com.dreamwolf.entity.member.web_interface.OwnerInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *接口调接口，调用user
 */
@FeignClient(name = "member-service")
public interface UserlistService {

    @GetMapping("/ownerinfo")
    public ResponseData<OwnerInfo> OwnerInfo(@RequestParam Integer uID);

}
