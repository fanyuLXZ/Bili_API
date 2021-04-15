package com.dreamwolf.fav.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * 视频基础信息表
 */
@Configuration
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="视频对象", description="视频基本信息表")
public class Video {

    @ApiModelProperty(value = "bv号 (视频id)")
    @TableId(value = "bvID", type = IdType.AUTO)
    private Integer bvID;   //bv号 (视频id)
    @TableField(value = "uID")
    private Integer uID;    //视频作者ID
    @TableField(value = "bvTitle")
    private String bvTitle; //视频标题
    @TableField(value = "bvDesc")
    private String bvDesc;  //视频简介
    @TableField(value = "bvPostTime")
    private Date bvPostTime; //视频上传日期（默认为当前时间）
    @TableField(value = "bvChildZoning")
    private Integer bvChildZoning;  //视频子分区ID
    @TableField(value = "bvIsDel")
    private Integer bvIsDel;    //是否已删除，0为未删除，1为已删除


}
