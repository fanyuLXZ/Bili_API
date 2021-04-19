package com.dreamwolf.zoning.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 分区关系表
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Zoningrelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，无意义
     */
    @TableId(value = "zrID", type = IdType.AUTO)
    private Integer zrID;

    /**
     * 父分区id
     */
    @TableField("zIDFather")
    private Integer zIDFather;

    /**
     * 子分区id
     */
    @TableField("zIDChild")
    private Integer zIDChild;


}
