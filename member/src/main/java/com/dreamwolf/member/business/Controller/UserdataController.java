package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.User;
import com.dreamwolf.entity.member.Userdata;
import com.dreamwolf.entity.member.Vip;
import com.dreamwolf.entity.member.Label;
import com.dreamwolf.entity.member.Level_info;
import com.dreamwolf.entity.member.Member;
import com.dreamwolf.entity.member.VipStatus;
import com.dreamwolf.member.business.service.*;
import com.dreamwolf.member.business.util.Jisuan;
import com.dreamwolf.safety.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

/**
 * <p>
 * 用户个人数据 前端控制器
 * </p>
 * @author zhaolin
 * @since 2021-04-12
 */
@RestController
public class UserdataController {

    @Autowired
    UserService userService;
    @Autowired
    UserdataService userdataService;
    @Autowired
    VipService vipService;
    @Autowired
    RelationsService relationsService;
    @Resource
    SafetyService safetyService;

    //用户信息
    @RequestMapping("/all-info")
    public ResponseData<Member> info(HttpServletRequest request){
        Jisuan jisuan=new Jisuan();
        ResponseData<Integer> logon_uid_result = safetyService.logon_uid(TokenUtil.getToken(request));
        if (logon_uid_result.getCode()==0) {
            Integer id = logon_uid_result.getData();
            Userdata userdata = userdataService.select(id);
            if(userdata!=null){
                Level_info level_info = new Level_info(userdata.getLevel(), jisuan.residue(userdata.getLevel()), userdata.getExp(), jisuan.residue(userdata.getLevel() + 1));//jisuan.mincurrent(userdata.getLevel(),userdata.getExp().intValue()) 经验计算
                //会员相关
                Vip ivip = vipService.vipselect(id);
                //判断会员类型 4.1号为小会员
                Calendar cal = Calendar.getInstance();
                int month = (cal.get(Calendar.MONTH)) + 1;//月
                int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
                VipStatus vipStatus = new VipStatus((month + "/" + day_of_month).equals("4/1") ? 0 : 1, ivip != null, ivip.getExpirationTime(), new Label("", "大会员", "vip", "#FFFFFF", "1", "#FB7299", ""), "http://i0.hdslb.com/bfs/vip/icon_Certification_big_member_22_3x.png");
                User user = userService.getById(id);
                Member member = new Member(true, user.getuID(), user.getUserName(), user.getHeadImgPath(), level_info, vipStatus, userdata.getCoinsNum().intValue(), userdata.getBCoinsNum().intValue(), user.getBoundEmail() != null, user.getBoundPhone() != null);
                return new ResponseData<>(0, "", 1, member);
            }else {
                return new ResponseData<>(3, "用户错误", 1, null);
            }
        }else {
            return new ResponseData<>(logon_uid_result.getCode(), logon_uid_result.getMessage(), 1, null);
        }

    }

    //通过用户id 返回Userdata表所有信息 用户等级经验等.通过id返回Userdata表所有对应id信息
    @RequestMapping("/Userdata")
    public ResponseData<Userdata> userdata(Integer uid){
        QueryWrapper<Userdata> wrapper = new QueryWrapper<>();
        wrapper.eq("uID",uid);
        Userdata userdata=userdataService.getOne(wrapper);
        return new ResponseData<Userdata>(0,"",1,userdata);
    }

}

