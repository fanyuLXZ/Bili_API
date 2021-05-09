package com.dreamwolf.message.service;

import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.ReplyUser;
import com.dreamwolf.entity.member.Users;
import com.dreamwolf.entity.message.web_interface.MMres;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "member-service")
public interface MemberService {

//    @GetMapping("/users")
//    public List<Map<String,Object>> users(@RequestParam Integer[] list, @RequestParam Integer uid);
    @GetMapping("/users")
    public ResponseData<List<Users>> users(@RequestParam Integer[] list,@RequestParam Integer uid);

//    @GetMapping("/data")
//    public List<Map<String,Object>> list(@RequestParam Integer id);

    @GetMapping("/data")
    public ResponseData<List<MMres>> list(@RequestParam Integer id);

//    @GetMapping("/replyuserb")
//    public Map<String,Object> replyuserb(@RequestParam Integer uid,@RequestParam Integer id);//回复人  和原评论

    @GetMapping("/replyuserb")
    public ResponseData<ReplyUser> replyuserb(@RequestParam Integer uid, @RequestParam Integer id);

}
