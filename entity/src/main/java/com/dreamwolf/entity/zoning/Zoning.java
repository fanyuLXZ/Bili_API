package com.dreamwolf.entity.zoning;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 分区表
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-20
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
     * 父分区id，为空表示根分区
     */
    @TableField("zFatherID")
    private Integer zFatherID;

    /**
     * 分区名称
     */
    @TableField("zName")
    private String zName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getzID() {
        return zID;
    }

    public void setzID(Integer zID) {
        this.zID = zID;
    }

    public Integer getzFatherID() {
        return zFatherID;
    }

    public void setzFatherID(Integer zFatherID) {
        this.zFatherID = zFatherID;
    }

    public String getzName() {
        return zName;
    }

    public void setzName(String zName) {
        this.zName = zName;
    }
}
