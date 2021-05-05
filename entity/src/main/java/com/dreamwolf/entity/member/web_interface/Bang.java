package com.dreamwolf.entity.member.web_interface;

import lombok.Data;

//entrance 最新动态up主对象
@Data
public class Bang {
    private String icon;//头像路径
    private Integer mid;//id
    private String type;//类型

    public Bang(String icon, Integer mid, String type) {
        this.icon = icon;
        this.mid = mid;
        this.type = type;
    }
}
