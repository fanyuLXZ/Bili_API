package com.dreamwolf.dynamic.business.entity;

import lombok.Data;

//评论信息
@Data
public class Reply {
    private Page page;//评论分页
    //private Replies replies;//评论对象集合

    public Reply(Page page) {
        this.page = page;
    }
}
