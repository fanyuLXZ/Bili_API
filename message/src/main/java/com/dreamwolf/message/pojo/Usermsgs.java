package com.dreamwolf.message.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 用户消息表
 * </p>
 *
 * @author 老徐
 * @since 2021-04-23
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Usermsgs对象", description="用户消息表")
public class Usermsgs implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键自增ID，无意义")
    @TableId(value = "umID", type = IdType.AUTO)
    private Integer umID;

    @ApiModelProperty(value = "发送信息的用户ID")
    @TableField("userID")
    private Integer userID;

    @ApiModelProperty(value = "接收信息的用户ID")
    @TableField("friendID")
    private Integer friendID;

    @ApiModelProperty(value = "留言发送者(用户名)")
    private String sender;

    @ApiModelProperty(value = "留言接收者(用户名)")
    private String receiver;

    @ApiModelProperty(value = "发送信息时间")
    @TableField("updateTime")
    private Date updateTime;

    @ApiModelProperty(value = "留言内容")
    private String content;


}
