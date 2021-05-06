package com.dreamwolf.entity.comment.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentPage {
    private Integer count;  //子评论总数
    private Integer num;//页码
    private Integer size;   //每页总条数
}
