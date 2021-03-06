package com.dreamwolf.entity.video;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 视频基础信息表
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
@ApiModel(value = "Video对象", description = "视频基础信息表")
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

    private Integer countbv; // 数量
    private Integer bvChild; //子分区id

    public Video(Integer uID, String bvCoverImgPath, String bvVideoPath, String bvTitle, String bvDesc, Date bvPostTime, Integer bvChildZoning,Integer duration) {
        this.uID = uID;
        this.bvCoverImgPath = bvCoverImgPath;
        this.bvVideoPath = bvVideoPath;
        this.bvTitle = bvTitle;
        this.bvDesc = bvDesc;
        this.bvPostTime = bvPostTime;
        this.bvChildZoning = bvChildZoning;
        this.duration = duration;
    }
}
