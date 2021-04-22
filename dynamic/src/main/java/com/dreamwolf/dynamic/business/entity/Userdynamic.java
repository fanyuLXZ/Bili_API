package com.dreamwolf.dynamic.business.entity;

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
 * 用户动态表
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Userdynamic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单条动态ID，主键自增
     */
    @TableId("udID")
    private Integer udID;

    /**
     * 用户ID
     */
    @TableField("uID")
    private Integer uID;

    /**
     * 动态正文
     */
    private String content;

    /**
     * 发表动态的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("updateTime")
    private LocalDateTime updateTime;

    /**
     * 动态是否已经删除
     */
    @TableField("isDel")
    private Integer isDel;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
