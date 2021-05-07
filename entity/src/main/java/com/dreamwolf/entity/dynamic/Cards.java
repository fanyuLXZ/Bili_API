package com.dreamwolf.entity.dynamic;

import lombok.Data;

//动态信息对象集合
@Data
public class Cards {
    private Desc desc;//基础信息对象

    public Cards(Desc desc) {
        this.desc = desc;
    }

    public Cards() {
    }
}
