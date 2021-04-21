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
 * 视频数据表
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
@ApiModel(value="Videodata对象", description="视频数据表")
public class Videodata implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频对应BV号")
    @TableField("bvID")
    private Integer bvID;

    @ApiModelProperty(value = "视频播放数")
    @TableField("bvPlayNum")
    private Long bvPlayNum;

    @ApiModelProperty(value = "视频弹幕数")
    @TableField("bvPopupsNum")
    private Long bvPopupsNum;

    @ApiModelProperty(value = "视频顶数")
    @TableField("bvLikeNum")
    private Long bvLikeNum;

    @ApiModelProperty(value = "视频硬币数")
    @TableField("bvCoinNum")
    private Long bvCoinNum;

    @ApiModelProperty(value = "视频收藏数")
    @TableField("bvFavoriteNum")
    private Long bvFavoriteNum;

    @ApiModelProperty(value = "视频转发数")
    @TableField("bvRetweetNum")
    private Long bvRetweetNum;

    @ApiModelProperty(value = "视频评论数")
    @TableField("bvCommentNum")
    private Long bvCommentNum;


}
