package com.dreamwolf.video.pojo;

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
 * 视频点赞表
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Controller
@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@ApiModel(value="Videolike对象", description="视频点赞表")
public class Videolike implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频ID")
    @TableId(value = "bvID", type = IdType.ID_WORKER)
    private Integer bvID;

    @ApiModelProperty(value = "点赞的用户ID")
    @TableField("uID")
    private Integer uID;

    @ApiModelProperty(value = "点赞状态，0为未作任何操作，1为点赞 默认为0")
    private Integer status;

    @ApiModelProperty(value = "点赞时间")
    @TableField("createTime")
    private Date createTime;


}
