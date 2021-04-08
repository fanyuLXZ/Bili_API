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
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 用户消息表
 * </p>
 *
 * @author 老徐
 * @since 2021-04-07
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@ApiModel(value="Usermsgs对象", description="用户消息表")
public class Usermsgs implements Serializable {

//    private static final long serialVersionUID = 1L;
//
//    @ApiModelProperty(value = "主键自增ID，无意义")
    @TableId(value = "umID", type = IdType.AUTO)//“value”：设置数据库字段值“type”：设置主键类型、如果数据库主键设置了自增建议使用“AUTO”
    private Integer umID;

//    @ApiModelProperty(value = "发送信息的用户ID")
    @TableField("userID")
    private Integer userID;

//    @ApiModelProperty(value = "接收信息的用户ID")
    @TableField("friendID")
    private Integer friendID;

//    @ApiModelProperty(value = "留言发送者")
    private String sender;

//    @ApiModelProperty(value = "留言接收者")
    private String receiver;

//    @ApiModelProperty(value = "发送信息时间")
    @TableField("updateTime")
    private Date updateTime;

//    @ApiModelProperty(value = "留言内容")
    private String content;


}