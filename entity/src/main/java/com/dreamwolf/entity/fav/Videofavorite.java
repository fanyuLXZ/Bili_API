package com.dreamwolf.entity.fav;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dreamwolf.entity.video.Video;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date favTime;

    private Video video;

}
