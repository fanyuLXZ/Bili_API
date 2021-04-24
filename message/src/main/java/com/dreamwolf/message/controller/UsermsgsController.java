package com.dreamwolf.message.controller;


import com.dreamwolf.message.pojo.Usermsgs;
import com.dreamwolf.message.service.UsermsgsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户消息表 前端控制器
 * </p>
 *
 * @author 老徐
 * @since 2021-04-23
 */
@RestController
public class UsermsgsController {

    @Resource
    private UsermsgsService usermsgsService;

    @GetMapping("/usermsgsfid")
    public Map selectlistfid(Integer fid){
        Map map = new HashMap();
        List<Usermsgs> usermsgsList = usermsgsService.selectusmsgsfid(fid);
        if(map !=null && usermsgsList !=null && fid !=null) {
            map.put("data", usermsgsList);
        }else {
            map.put("code",400);
            map.put("message","map为空或list为空或fid为空");
            map.put("data", null);
        }
        return map;
    }

    @GetMapping("/usermsguid")
    public Map selectlistuid(Integer uid){
        Map map = new HashMap();
        List<Usermsgs> usermsgsList = usermsgsService.selectusmsgsuid(uid);
        if(map !=null && usermsgsList !=null && uid !=null) {
            map.put("data", usermsgsList);
        }else {
            map.put("code",400);
            map.put("message","map为空或list为空或uid为空");
            map.put("data", null);
        }
        return map;
    }

}

