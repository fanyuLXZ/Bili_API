package com.dreamwolf.entity.comment.web_interface;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Replies {

    private CommentPage page;//分页对象
    private List<Commentinfo> replies;

}
