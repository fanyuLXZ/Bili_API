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
 * 
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
@ApiModel(value="Favoritelike对象", description="")
public class Favoritelike implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "被点赞的收藏夹ID")
    @TableId(value = "favListID", type = IdType.ID_WORKER)
    private Integer favListID;

    @ApiModelProperty(value = "给予点赞的用户ID")
    @TableField("uID")
    private Integer uID;

    @ApiModelProperty(value = "点赞状态，0为未点赞，1为已点赞")
    private Integer status;


}
