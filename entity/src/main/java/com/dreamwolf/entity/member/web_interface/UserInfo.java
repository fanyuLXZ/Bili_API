package com.dreamwolf.entity.member.web_interface;

import lombok.Data;

@Data
public class UserInfo {
    private String hide_tel;//隐藏过得手机号
    private String hide_mail;//隐藏过得邮箱
    private boolean bind_tel;//是否绑定手机号
    private boolean bind_mail;//是否绑定邮箱

    public UserInfo(String hide_tel, String hide_mail, boolean bind_tel, boolean bind_mail) {
        this.hide_tel = hide_tel;
        this.hide_mail = hide_mail;
        this.bind_tel = bind_tel;
        this.bind_mail = bind_mail;
    }
}
