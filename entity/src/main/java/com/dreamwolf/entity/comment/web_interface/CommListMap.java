package com.dreamwolf.entity.comment.web_interface;

import com.dreamwolf.entity.member.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommListMap {


    private Integer rpid;//评论id
    private Integer action;//点赞状态
    private Messagecontext content; //评论内容对象
    private Integer count;//子评论数量
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date ctime; //评论时间
    private Long like;   //点赞数
    private Member member;//发表评论人对象
    private List<CommReplies> replies;//子评论对象集合

}
