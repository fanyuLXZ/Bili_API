package com.dreamwolf.entity.member.web_interface;

import com.dreamwolf.entity.member.User;
import com.dreamwolf.entity.member.Userdata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
public class VideoinfoOwnerInfo {
    private Integer mid;//作者id
    private String upname;//姓名
    private Integer fans;//关注数
    private boolean attention;//是否关注
    private String sign;//个人简历
    private String face;//头像

    public VideoinfoOwnerInfo(Integer mid, String upname, Integer fans, boolean attention, String sign, String face) {
        this.mid = mid;
        this.upname = upname;
        this.fans = fans;
        this.attention = attention;
        this.sign = sign;
        this.face = face;
    }
    public VideoinfoOwnerInfo(User user, Userdata userdata, boolean relations) {
        this.mid=user.getuID();
        this.upname=user.getNickName();
        this.face=user.getHeadImgPath();
        this.fans= Math.toIntExact(userdata.gettFollowNum());
        this.sign=userdata.getuDescription();
        this.attention=relations;
    }
}
