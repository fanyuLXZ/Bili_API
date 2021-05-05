package com.dreamwolf.entity.member;

import lombok.Data;

@Data
public class ReplyUser {
    private Integer mid;//评论对象的id
    private String nickname;//评论对象打的名字
    private String avatar;//对象头像
    private boolean follow;//是否关注

    public ReplyUser(Integer mid, String nickname, String avatar, boolean follow) {
        this.mid = mid;
        this.nickname = nickname;
        this.avatar = avatar;
        this.follow = follow;
    }
}
