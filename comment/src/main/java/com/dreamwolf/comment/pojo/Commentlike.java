package com.dreamwolf.comment.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Commentlike {

    @TableId(value = "cID")
    private Integer cID;    //被点赞的评论ID
    @TableField("uID")
    private Integer uID;    //点赞的用户ID
    @TableField("status")
    private Integer status; //点赞状态，未做任何操作为0，点赞为1，点踩为2默认为0
    @TableField("createTime")
    private Date createTime;    //点赞时间，默认为当前时间
}
