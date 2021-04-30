package com.dreamwolf.video.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 视频综合评分表，根据该表判断视频排名
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
@ApiModel(value="Videorating对象", description="视频综合评分表，根据该表判断视频排名")
public class Videorating implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频ID")
    @TableField("bvID")
    private Integer bvID;

    @ApiModelProperty(value = "视频综合评分，评分规则详见数据库说明书。")
    @TableField("OverallRating")
    private Double OverallRating;


}
