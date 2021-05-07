package com.dreamwolf.entity.comment.web_interface;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commentinfo {

    private Integer id; //评论id
    private Integer rpid;   //回复评论id
    private CommentPage page;//分页对象
//    private member ;  //用户对象
    private Long like;//评论点赞数
    private Long dislike;   //评论点踩数
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime;//评论时间
    private Messagecontext content; //评论内容对象

}
