package com.dreamwolf.entity.watchhistory.web_interface;

import lombok.Data;

@Data
public class History {
    private Integer oid;//hid
    private String business;//类型
    private Integer page;//p数

    public History() {
    }

    public History(Integer oid, String business, Integer page) {
        this.oid = oid;
        this.business = business;
        this.page = page;
    }
}
