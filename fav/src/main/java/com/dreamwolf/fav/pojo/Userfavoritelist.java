package com.dreamwolf.fav.pojo;

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
 * 用户收藏列表
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
@ApiModel(value="Userfavoritelist对象", description="用户收藏列表")
public class Userfavoritelist implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "视频收藏夹ID，主键唯一")
    @TableId(value = "favListID", type = IdType.AUTO)
    private Integer favListID;

    @ApiModelProperty(value = "创建收藏夹的用户ID")
    @TableField("uID")
    private Integer uID;

    @ApiModelProperty(value = "收藏夹名称，可以重复")
    private String name;

    @ApiModelProperty(value = "是否为私密收藏夹，私密为1，公开为0")
    @TableField("isSecret")
    private Integer isSecret;

    @ApiModelProperty(value = "收藏夹的总点赞数")
    @TableField("tLikeNum")
    private Integer tLikeNum;

    @ApiModelProperty(value = "收藏夹描述")
    @TableField("desc")
    private String desc;


}
