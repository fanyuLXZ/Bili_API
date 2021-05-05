package com.dreamwolf.entity.member;

import lombok.Data;

@Data
public class UserVerify {
    private boolean succeed;//是否成功
    private Integer uid;//id
    private String message;//信息

    public UserVerify(boolean succeed, Integer uid, String message) {
        this.succeed = succeed;
        this.uid = uid;
        this.message = message;
    }
}
