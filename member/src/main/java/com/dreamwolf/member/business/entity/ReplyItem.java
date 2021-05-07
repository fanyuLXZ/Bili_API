package com.dreamwolf.member.business.entity;

import com.dreamwolf.entity.member.ReplyUser;
import com.dreamwolf.entity.message.web_interface.MMItems;
import lombok.Data;

@Data
public class ReplyItem {
    private ReplyUser user;//回复我的对象
    private MMItems item;//回复我的评论对象

    public ReplyItem() {
    }

    public ReplyItem(ReplyUser user, MMItems item) {
        this.user = user;
        this.item = item;
    }
}
