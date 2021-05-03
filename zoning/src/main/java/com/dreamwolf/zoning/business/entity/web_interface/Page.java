package com.dreamwolf.zoning.business.entity.web_interface;

import lombok.Data;

@Data
public class Page {
    private Integer count;//总数
    private Integer num;//页码
    private Integer size;//每页条数

    public Page(Integer count, Integer num, Integer size) {
        this.count = count;
        this.num = num;
        this.size = size;
    }
}
