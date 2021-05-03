package com.dreamwolf.entity.member.web_interface;

import com.dreamwolf.entity.member.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//作者信息
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerInfo {
    private Integer mid;//作者id
    private String name;//姓名
    private String face;//头像

    public OwnerInfo(User user) {
        this.mid = user.getuID();
        this.name = user.getNickName();
        this.face = user.getHeadImgPath();
    }
}
