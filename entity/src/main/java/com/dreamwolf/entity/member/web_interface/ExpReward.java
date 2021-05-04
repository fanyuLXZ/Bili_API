package com.dreamwolf.entity.member.web_interface;

import lombok.Data;

@Data
public class ExpReward {
    private boolean login;//是否登录
    private boolean watch;//是否观看视频
    private Integer coins;//今日投币数
    private boolean chare;//是否分享过视频
    private boolean email;//是否绑定了邮箱
    private boolean tel;//是否绑定了手机号

    public ExpReward(boolean login, boolean watch, Integer coins, boolean chare, boolean email, boolean tel) {
        this.login = login;
        this.watch = watch;
        this.coins = coins;
        this.chare = chare;
        this.email = email;
        this.tel = tel;
    }
}
