package com.dreamwolf.dynamic.business.entity;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 动态点赞表，用于区分用户点赞
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Dynamiclike implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 被点赞的动态ID
     */
    @TableId("udID")
    private Integer udID;

    /**
     * 点赞用户ID
     */
    @TableField("uID")
    private Integer uID;

    /**
     * 点赞状态，点赞状态，未做任何操作为0，点赞为1
默认为0
     */
    private Integer status;

    /**
     * 点赞时间，默认为当前时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @TableField("createTime")
    private LocalDateTime createTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUdID() {
        return udID;
    }

    public void setUdID(Integer udID) {
        this.udID = udID;
    }

    public Integer getuID() {
        return uID;
    }

    public void setuID(Integer uID) {
        this.uID = uID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
