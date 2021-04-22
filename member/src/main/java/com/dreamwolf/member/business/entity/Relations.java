package com.dreamwolf.member.business.entity;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户关系表
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Relations implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户本人ID

     */
    @TableId("uID")
    private Integer uID;

    /**
     * 粉丝ID

     */
    @TableField("followUID")
    private Integer followUID;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getuID() {
        return uID;
    }

    public void setuID(Integer uID) {
        this.uID = uID;
    }

    public Integer getFollowUID() {
        return followUID;
    }

    public void setFollowUID(Integer followUID) {
        this.followUID = followUID;
    }
}