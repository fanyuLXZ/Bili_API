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
 * 视频基础信息表
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
@ApiModel(value="Video对象", description="视频基础信息表")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "bv号")
    @TableId(value = "bvID", type = IdType.AUTO)
    private Integer bvID;

    @ApiModelProperty(value = "视频作者ID")
    @TableField("uID")
    private Integer uID;

    @ApiModelProperty(value = "视频封面图")
    @TableField("bvCoverImgPath")
    private String bvCoverImgPath;

    @ApiModelProperty(value = "视频文件路径")
    @TableField("bvVideoPath")
    private String bvVideoPath;

    @ApiModelProperty(value = "视频标题")
    @TableField("bvTitle")
    private String bvTitle;

    @ApiModelProperty(value = "视频简介")
    @TableField("bvDesc")
    private String bvDesc;

    @ApiModelProperty(value = "视频上传日期（默认为当前时间）")
    @TableField("bvPostTime")
    private Date bvPostTime;

    @ApiModelProperty(value = "视频子分区ID")
    @TableField("bvChildZoning")
    private Integer bvChildZoning;

    @ApiModelProperty(value = "是否已删除，0为未删除，1为已删除")
    @TableField("bvIsDel")
    private Integer bvIsDel;

    @ApiModelProperty(value = "时长")
    @TableField("duration")
    private Integer duration;

    //视频评分
    private Videorating videorating;

    private String date;
    private String datetime;


}
