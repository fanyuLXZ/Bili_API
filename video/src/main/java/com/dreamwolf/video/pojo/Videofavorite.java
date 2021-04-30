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
 * 视频收藏表
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@ApiModel(value="Videofavorite对象", description="视频收藏表")
public class Videofavorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "被收藏的视频ID")
    @TableId(value = "bvID", type = IdType.ID_WORKER)
    private Integer bvID;

    @ApiModelProperty(value = "视频收藏夹ID")
    @TableField("favListID")
    private Integer favListID;

    @ApiModelProperty(value = "收藏时间，默认为当前时间，不支持设置系统时间")
    @TableField("favTime")
    private Date favTime;


}
