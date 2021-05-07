package com.dreamwolf.dynamic.business.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.dynamic.Userdata;
import com.dreamwolf.entity.member.Relations;
import com.dreamwolf.entity.member.User;
import com.dreamwolf.entity.member.Users;
import com.dreamwolf.entity.member.Vip;
import com.dreamwolf.entity.member.web_interface.Bang;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "member-service")
public interface MemberService {
    @GetMapping("/useruid")
    public ResponseData<User> useruid(@RequestParam Integer uid);

    @PostMapping("/bang")
    public ResponseData<Bang> verify(@RequestParam Integer id);

    @RequestMapping("/intuid")
    public ResponseData<List<Relations>> intuid(@RequestParam Integer followUID);



    @RequestMapping("/Userdata")
    public ResponseData<Userdata> userdata(@RequestParam Integer uid);

    /*@RequestMapping("/Userdata")
    public ResponseData<Userdata> Userdataid(@RequestParam Integer uid);*/

    @RequestMapping("/Vip")
    public ResponseData<Vip> vip(@RequestParam Integer uID);

    @GetMapping("/users")
    public ResponseData<List<Users>> users(@RequestParam Integer[] list, @RequestParam Integer uid);
}
