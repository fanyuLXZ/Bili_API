package com.dreamwolf.dynamic.business.Controller;


import com.dreamwolf.dynamic.business.entity.Dynamiclike;
import com.dreamwolf.dynamic.business.service.*;
import com.dreamwolf.member.business.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class DynamiccommentController {
    @Autowired
    DynamiccommentService dynamiccommentService;
    @Autowired
    DynamicdataService dynamicdataService;
    @Autowired
    DynamiclikeService dynamiclikeService;
    @Autowired
    UserdynamicService userdynamicService;
    /*@Resource
    UserService userService;*/

    @RequestMapping("/entrance")
    public Map entrance() {
        Integer id=1;
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("code",0);//"code":0,"message":"0","ttl":1,
        map.put("message",0);
        map.put("ttl",1);
        Map<String, Object> data=new HashMap<String, Object>();
        Map<String, Object> entrance=new HashMap<String, Object>();
        //User user=userService.ById(id);
        /*entrance.put("icon",user.getHeadImgPath());
        entrance.put("mid",user.getuID());//id*/
        entrance.put("type","up");
        data.put("entrance",entrance);
        map.put("data",data);
        return map;
    }
}

