package com.dreamwolf.entity.member;

import lombok.Data;

//点赞对象用户信息
@Data
public class ReplyUser {
    private Integer mid;//评论对象的id
    private String nickname;//评论对象打的名字
    private String avatar;//对象头像
    private boolean follow;//是否关注
    private String native_uri;//个人中心的地址

    public ReplyUser() {
    }

    public ReplyUser(Integer mid, String nickname, String avatar, boolean follow, String native_uri) {
        this.mid = mid;
        this.nickname = nickname;
        this.avatar = avatar;
        this.follow = follow;
        this.native_uri = native_uri;
    }

    public ReplyUser(Integer mid, String nickname, String avatar, boolean follow) {
        this.mid = mid;
        this.nickname = nickname;
        this.avatar = avatar;
        this.follow = follow;
    }
}
