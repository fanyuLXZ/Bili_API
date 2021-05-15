package com.dreamwolf.video.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.Member;
import com.dreamwolf.entity.member.User;
import com.dreamwolf.entity.member.web_interface.OwnerInfo;
import com.dreamwolf.entity.member.web_interface.VideoinfoOwnerInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "member-service")
public interface UsermapService {

//    @GetMapping("/User")
//    public Map user(@RequestParam Integer uid);
    @GetMapping("/useruid")
    public ResponseData<User> userid(@RequestParam Integer uid);

    @GetMapping("/video/info")
    public ResponseData<VideoinfoOwnerInfo> video_info(@RequestParam Integer uid, @RequestParam Integer mid);

//    @GetMapping("/ownerinfo")
//    public OwnerInfo OwnerInfo(@RequestParam Integer uID);

    @GetMapping("/ownerinfo")
    public ResponseData<OwnerInfo> OwnerInfo(@RequestParam Integer uID);


    @GetMapping("/card/info/{aid}")
    ResponseData<Member> cardInfoById(@PathVariable Integer aid);


}
