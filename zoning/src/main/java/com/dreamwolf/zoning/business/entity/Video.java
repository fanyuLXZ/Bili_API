package com.dreamwolf.zoning.business.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 视频基础信息表
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Video implements Serializable {


    private Integer countbv;

    private Integer bvChild;


    public Integer getCountbv() {
        return countbv;
    }

    public void setCountbv(Integer countbv) {
        this.countbv = countbv;
    }

    public Integer getBvChild() {
        return bvChild;
    }

    public void setBvChild(Integer bvChild) {
        this.bvChild = bvChild;
    }
}
