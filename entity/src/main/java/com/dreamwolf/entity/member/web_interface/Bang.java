package com.dreamwolf.entity.member.web_interface;

import lombok.Data;

@Data
public class Bang {
    private String icon;
    private Integer mid;
    private String type;

    public Bang(String icon, Integer mid, String type) {
        this.icon = icon;
        this.mid = mid;
        this.type = type;
    }
}
