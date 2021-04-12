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
 * @since 2021-04-12
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
     * 视频id
     */
    @TableField("bvID")
    private Integer bvID;

    /**
     * 分区id
     */
    @TableField("zID")
    private Integer zID;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getZrID() {
        return zrID;
    }

    public void setZrID(Integer zrID) {
        this.zrID = zrID;
    }

    public Integer getBvID() {
        return bvID;
    }

    public void setBvID(Integer bvID) {
        this.bvID = bvID;
    }

    public Integer getzID() {
        return zID;
    }

    public void setzID(Integer zID) {
        this.zID = zID;
    }
}
