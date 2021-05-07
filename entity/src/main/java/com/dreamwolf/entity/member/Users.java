package com.dreamwolf.entity.member;

import lombok.Data;

/**
 * 点赞用户信息
 */
@Data
public class Users {
    private Integer mid;//点赞对象的id
    private String nickname;//点赞对象的名字
    private String avatar;//对象头像
    private boolean follow;//是否关注
    private String native_uri;//个人中心的地址

    public Users(Integer mid, String nickname, String avatar, boolean follow, String native_uri) {
        this.mid = mid;
        this.nickname = nickname;
        this.avatar = avatar;
        this.follow = follow;
        this.native_uri = native_uri;
    }
}
