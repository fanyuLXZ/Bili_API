package com.dreamwolf.entity.watchhistory;

import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户观看历史表
 * </p>
 *
 * @author zhaolin
 * @since 2021-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Videohistory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId("uID")
    private Integer uID;

    @TableField("oid")
    private Integer oid;

    /**
     * 视频ID
     */
    @TableField("bvID")
    private Integer bvID;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    /**
     * 关闭媒体时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("CloseTime")
    private LocalDateTime CloseTime;

    /**
     * 关闭时，时间线位置
     */
    @TableField("TimelinePosition")
    private LocalTime TimelinePosition;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getuID() {
        return uID;
    }

    public void setuID(Integer uID) {
        this.uID = uID;
    }

    public Integer getBvID() {
        return bvID;
    }

    public void setBvID(Integer bvID) {
        this.bvID = bvID;
    }

    public LocalDateTime getCloseTime() {
        return CloseTime;
    }

    public void setCloseTime(LocalDateTime closeTime) {
        CloseTime = closeTime;
    }

    public LocalTime getTimelinePosition() {
        return TimelinePosition;
    }

    public void setTimelinePosition(LocalTime timelinePosition) {
        TimelinePosition = timelinePosition;
    }
}
