package com.dreamwolf.dynamic.business.service;

import com.dreamwolf.entity.dynamic.User;
import com.dreamwolf.entity.dynamic.Userdata;
import com.dreamwolf.entity.dynamic.Vip;
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
    public User useruid(@RequestParam Integer uid);

    @PostMapping("/bang")
    Map verify(@RequestParam Integer id);

    @RequestMapping("/intuid")
    public List<Map<String, Object>> intuid(@RequestParam Integer followUID);

    @RequestMapping("/User")
    public Map<String,Object> user(@RequestParam Integer uid);

    @RequestMapping("/Vip")
    public Map<String,Object> vip(@RequestParam Integer uID);

    @RequestMapping("/Userdata")
    public Map<String,Object> userdata(@RequestParam Integer id);

    @RequestMapping("/Userdataid")
    public Userdata Userdataid(@RequestParam Integer uid);

    @RequestMapping("/vipid")
    public Vip vipid(@RequestParam Integer uid);

    @GetMapping("/users")
    public List<Map<String,Object>> users(@RequestParam Integer[] list,@RequestParam Integer uid);
}
