package com.dreamwolf.entity.dynamic;

import com.dreamwolf.entity.member.Level_info;
import com.dreamwolf.entity.member.Member;
import com.dreamwolf.entity.member.VipStatus;
import lombok.Data;

@Data
public class UserProfile {
    private Member info;//用户基本信息
    private VipStatus vip;//用户会员
    private Level_info level_info;//等级对象

    public UserProfile() {
    }

    public UserProfile(Member info, VipStatus vip, Level_info level_info) {
        this.info = info;
        this.vip = vip;
        this.level_info = level_info;
    }
}
