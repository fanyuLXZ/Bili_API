package com.dreamwolf.entity.message.web_interface;

import com.dreamwolf.entity.member.ReplyUser;
import com.dreamwolf.entity.member.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MMres {

    private ReplyUser user;//用户对象
    private MMItems item;//评论对象


}
