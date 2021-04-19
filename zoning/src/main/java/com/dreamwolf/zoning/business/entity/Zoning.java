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
 * 分区表
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Zoning implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分区ID
     */
    @TableId(value = "zID", type = IdType.AUTO)
    private Integer zID;

    /**
     * 分区名称
     */
    @TableField("zName")
    private String zName;


}
