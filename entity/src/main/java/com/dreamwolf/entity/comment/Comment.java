package com.dreamwolf.entity.comment;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户评论表

 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@ApiModel(value="Comment对象", description="用户评论表")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户评论ID，主键自增")
    @TableId(value = "cID", type = IdType.AUTO)
    private Integer cID;

    @ApiModelProperty(value = "撰写评论的用户ID")
    @TableField("uID")
    private Integer uID;

    @ApiModelProperty(value = "回复的评论ID")
    @TableField("cIDreply")
    private Integer cIDreply;

    @ApiModelProperty(value = "评论时间 ")
    @TableField("createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "评论正文")
    @TableField("cText")
    private String cText;

    @ApiModelProperty(value = "是否已删除，0为未删除，1为已删除")
    @TableField("isDel")
    private Integer isDel;

    private Commentdata commentdata;


}
