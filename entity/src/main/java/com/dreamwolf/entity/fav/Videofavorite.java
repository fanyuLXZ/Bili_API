package com.dreamwolf.entity.fav;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 *视频收藏表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@ApiModel(value="Videofavorite对象", description="视频收藏表")
public class Videofavorite {

    @ApiModelProperty(value = "被收藏的视频ID")
    @TableId(value = "bvID", type = IdType.AUTO)
    private Integer bvID;

    @ApiModelProperty(value = "视频收藏夹ID")
    @TableField("favListID")
    private Integer favListID;

    @ApiModelProperty(value = "收藏时间，默认为当前时间，不支持设置系统时间")
    @TableField("favTime")
    private Date favTime;

    private Video video;

}
