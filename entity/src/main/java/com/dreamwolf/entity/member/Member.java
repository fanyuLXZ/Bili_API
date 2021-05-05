package com.dreamwolf.entity.member;

import lombok.Data;
//用户对象
@Data
public class Member {
    private Integer mid;//用户唯一id
    private String uname;//用户昵称
    private String sex;//性别
    private String face;//头像
    private String avatar;//头像
    private String name;//用户昵称
    private Long fans;//粉丝数
    private Long friend;//关注数
    private Level_info level_info;//等级对象
    private VipStatus vip;//vip对象
    private boolean following;//当前登录人是否关注查询的用户
    private boolean isLogin;//是否登录
    private Integer money;//硬币数
    private Integer bcoin_balance;//b币余额
    private boolean email_verified;//是否绑定邮箱
    private boolean mobile_verified;//是否绑定手机


    public Member(boolean isLogin, Integer mid, String uname, String face, Level_info level_info, VipStatus vip, Integer money, Integer bcoin_balance, boolean email_verified, boolean mobile_verified) {
        this.bcoin_balance=bcoin_balance;
        this.mid = mid;
        this.uname = uname;
        this.face = face;
        this.level_info = level_info;
        this.vip = vip;
        this.isLogin = isLogin;
        this.money = money;
        this.email_verified = email_verified;
        this.mobile_verified = mobile_verified;
    }

    public Member(Integer mid, String name, String face, Long fans, Long friend) {
        this.mid = mid;
        this.face = face;
        this.name = name;
        this.fans = fans;
        this.friend = friend;
    }

    public Member(Integer mid, String uname, String face) {
        this.mid = mid;
        this.uname = uname;
        this.face = face;
    }

    public Member(Integer mid, String uname, String sex, String face, Level_info level_info, VipStatus vipStatus) {
        this.mid = mid;
        this.uname = uname;
        this.sex = sex;
        this.face = face;
        this.level_info = level_info;
        this.vip = vipStatus;
    }

    public Member(Integer mid,String name,String face,String sex, Long fans, Long friend,Level_info level_info,VipStatus vipStatus, boolean following) {
        this.mid = mid;
        this.sex = sex;
        this.face = face;
        this.name = name;
        this.fans = fans;
        this.friend = friend;
        this.level_info = level_info;
        this.vip = vipStatus;
        this.following = following;
    }

}
