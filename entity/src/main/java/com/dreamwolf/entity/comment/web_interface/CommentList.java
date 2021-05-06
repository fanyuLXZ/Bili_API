package com.dreamwolf.entity.comment.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 评论通用返回list
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentList {

    public List list;

}
