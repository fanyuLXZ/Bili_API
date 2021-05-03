package com.dreamwolf.entity.comment;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 评论数据表
 * </p>
 *
 * @author 老徐
 * @since 2021-04-22
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Commentdata对象", description="评论数据表")
public class Commentdata implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论ID")
    @TableField("cID")
    private Integer cID;

    @ApiModelProperty(value = "点赞数量")
    @TableField("cLikeNum")
    private Long cLikeNum;

    @ApiModelProperty(value = "点踩数量")
    @TableField("cUnLikeNum")
    private Long cUnLikeNum;


}
