package com.dreamwolf.member.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户大会员信息
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Vip implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键无意义
     */
    @TableId(value = "vID", type = IdType.AUTO)
    private Integer vID;

    /**
     * 用户ID
     */
    @TableField("uID")
    private Integer uID;

    /**
     * 大会员过期时间
     */
    @TableField("ExpirationTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ExpirationTime;

    /**
     * 会员积分
     */
    @TableField("vPoint")
    private Integer vPoint;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getvID() {
        return vID;
    }

    public void setvID(Integer vID) {
        this.vID = vID;
    }

    public Integer getuID() {
        return uID;
    }

    public void setuID(Integer uID) {
        this.uID = uID;
    }

    public LocalDateTime getExpirationTime() {
        return ExpirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        ExpirationTime = expirationTime;
    }

    public Integer getvPoint() {
        return vPoint;
    }

    public void setvPoint(Integer vPoint) {
        this.vPoint = vPoint;
    }
}
