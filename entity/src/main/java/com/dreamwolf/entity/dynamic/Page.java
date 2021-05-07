package com.dreamwolf.entity.dynamic;

import lombok.Data;

//评论分页
@Data
public class Page {
    private Integer acount;//总评论(父评论+子评论)
    private Integer count;//评论数
    private Integer num;//当前页码
    private Integer size;//每页显示数

    public Page(Integer acount, Integer count, Integer num, Integer size) {
        this.acount = acount;
        this.count = count;
        this.num = num;
        this.size = size;
    }
}
