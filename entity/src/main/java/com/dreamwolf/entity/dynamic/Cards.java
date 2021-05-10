package com.dreamwolf.entity.dynamic;

import lombok.Data;

//动态信息对象集合
@Data
public class Cards {
    private Desc desc;//基础信息对象
    private String card;//内容

    public Cards(Desc desc, String card) {
        this.desc = desc;
        this.card = card;
    }

    public Cards() {
    }
}
