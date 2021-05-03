package com.dreamwolf.entity.dynamic;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Dynamiccomment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 动态ID
     */
    @TableId("udID")
    private Integer udID;

    /**
     * 评论ID
     */
    @TableField("cID")
    private Integer cID;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUdID() {
        return udID;
    }

    public void setUdID(Integer udID) {
        this.udID = udID;
    }

    public Integer getcID() {
        return cID;
    }

    public void setcID(Integer cID) {
        this.cID = cID;
    }
}
