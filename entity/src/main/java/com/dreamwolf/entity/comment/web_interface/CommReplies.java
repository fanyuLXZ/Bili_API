package com.dreamwolf.entity.comment.web_interface;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

/**
 * 子评论对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommReplies {

    private Messagecontext content;//回复内容对象
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;//评论时间
    private Long like; //点赞数
//    private member;//用户对象

}
