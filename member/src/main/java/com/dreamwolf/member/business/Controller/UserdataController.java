package com.dreamwolf.member.business.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dreamwolf.entity.ResponseData;
import com.dreamwolf.entity.member.*;
import com.dreamwolf.member.business.service.*;
import com.dreamwolf.member.business.util.Jisuan;
import com.dreamwolf.safety.util.TimeUtil;
import com.dreamwolf.safety.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundKeyOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

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
            if (userdata==null){
                userdata=new Userdata();
            }
            Level_info level_info = new Level_info(userdata.getLevel(), jisuan.residue(userdata.getLevel()), userdata.getExp(), jisuan.residue(userdata.getLevel() + 1));//jisuan.mincurrent(userdata.getLevel(),userdata.getExp().intValue()) 经验计算
            //会员相关
            Vip ivip = vipService.vipselect(id);
            if (ivip==null){
                ivip=new Vip();
            }
            //判断会员类型 4.1号为小会员
            Calendar cal = Calendar.getInstance();
            int month = (cal.get(Calendar.MONTH)) + 1;//月
            int day_of_month = cal.get(Calendar.DAY_OF_MONTH);//日
            VipStatus vipStatus = new VipStatus((month + "/" + day_of_month).equals("4/1") ? 0 : 1, ivip != null, ivip.getExpirationTime(), new Label("", "大会员", "vip", "#FFFFFF", "1", "#FB7299", ""), "http://i0.hdslb.com/bfs/vip/icon_Certification_big_member_22_3x.png");
            User user = userService.getById(id);
            if (user==null){
                return new ResponseData<>(3, "用户错误", 1, null);
            }
            Member member = new Member(true, user.getuID(), user.getUserName(), user.getHeadImgPath(), level_info, vipStatus, userdata.getCoinsNum().intValue(), userdata.getBCoinsNum().intValue(), user.getBoundEmail() != null, user.getBoundPhone() != null);
            return new ResponseData<>(0, "", 1, member);
        }else {
            return new ResponseData<>(-101, "账号未登录", 1, new Member(false));
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


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 硬币增加接口
     * @param uid uid
     * @return
     * code可能性
     * 0 成功
     * 1 重复提交
     * 2 redis异常
     * 3 mysql异常
     * 4 空参数
     */
    @PostMapping("/add_coin")
    public ResponseData<Boolean> addCoin(Integer uid){
        int code = 0;
        String message = "";
        boolean data = false;
        if (uid==null){
            code=4;
            message = "uid不能为空！";
        }else {
            Boolean isLogin = stringRedisTemplate.hasKey("coin_logon_"+uid);
            // 判断今天是否登录过
            if (isLogin==null){
                code=2;
                message = "服务器异常！";
            }else if (isLogin){
                code=1;
                message = "今天已经登录过啦！";
            }else {
                // 硬币增加
                if (userdataService.coin_add(uid)){
                    stringRedisTemplate.opsForValue().set("coin_logon_"+uid, "0", TimeUtil.getTomorrowSeconds(), TimeUnit.MINUTES);
                    data=true;
                }else {
                    code=3;
                    message = "服务器异常！";
                }
            }
        }
        return new ResponseData<>(code,message,1,data);
    }

    /**
     * 关注接口 我关注别人
     * 修改
     */
    @Transactional
    @PostMapping("/attention")
    public ResponseData<Boolean> attention(Integer uID){//关注对象id
        int code = 0;
        String message="";
        boolean bool = false;
        Integer id=1;
        if(uID!=null){
            //判断是否已经关注过
            QueryWrapper<Relations> relationsQueryWrapper=new QueryWrapper<>();
            relationsQueryWrapper.eq("uID",uID).eq("followUID",id);
            boolean whether=relationsService.getOne(relationsQueryWrapper)==null;//true表示，表示没有关注过
            if(whether){
                //没有关注则关注
                //添加用户和粉丝关系表
                Relations relations=new Relations(uID,id);
                boolean addrela=relationsService.save(relations);
                if (!addrela){
                    code = 1;
                    message="添加用户和粉丝关系失败";
                    return new ResponseData<Boolean>(code,message,1,bool);
                }
                UpdateWrapper<Userdata> updateWrapper=new UpdateWrapper<>();//粉丝数+1
                updateWrapper.eq("uID",uID).set("CoinsNum","CoinsNum+1");
                boolean updatauserdata=userdataService.update(null,updateWrapper);//修改成功、
                if (!updatauserdata){
                    code = 1;
                    message="用户和粉丝添加失败";
                    return new ResponseData<Boolean>(code,message,1,bool);
                }
                bool=true;
            }else{
                code = 1;
                message="该用户已关注";
            }
        }else{
            code = 1;
            message="uID不能为空";
        }
        return new ResponseData<Boolean>(code,message,1,bool);
    }
}

