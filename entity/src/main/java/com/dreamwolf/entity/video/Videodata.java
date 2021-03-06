package com.dreamwolf.entity.video;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 视频数据表
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
@ApiModel(value="Videodata对象", description="视频数据表")
public class Videodata implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频对应BV号")
    @TableField("bvID")
    private Integer bvID;

    @ApiModelProperty(value = "视频播放数")
    @TableField("bvPlayNum")
    private Long bvPlayNum=0L;

    @ApiModelProperty(value = "视频弹幕数")
    @TableField("bvPopupsNum")
    private Long bvPopupsNum=0L;

    @ApiModelProperty(value = "视频顶数")
    @TableField("bvLikeNum")
    private Long bvLikeNum=0L;

    @ApiModelProperty(value = "视频硬币数")
    @TableField("bvCoinNum")
    private Long bvCoinNum=0L;

    @ApiModelProperty(value = "视频收藏数")
    @TableField("bvFavoriteNum")
    private Long bvFavoriteNum=0L;

    @ApiModelProperty(value = "视频转发数")
    @TableField("bvRetweetNum")
    private Long bvRetweetNum=0L;

    @ApiModelProperty(value = "视频评论数")
    @TableField("bvCommentNum")
    private Long bvCommentNum=0L;

    @TableField("bvDislike")
    private Long bvDislike=0L;

    public Videodata(Integer bvID) {
        this.bvID = bvID;
    }
}
