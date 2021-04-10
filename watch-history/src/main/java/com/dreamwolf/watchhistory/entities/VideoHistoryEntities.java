package com.dreamwolf.watchhistory.entities;

import java.util.Date;

///**
// * @author: wzx
// * @data: 2021/4/7 10:03
// * @version: 1.0
// */
//用户观看历史表

public class VideoHistoryEntities {
    private int uID;//用户ID
    private int bvID;//视频ID
    private Date CloseTime;//关闭媒体时间
    private Date TimelinePosition;//关闭时，时间线位置

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public int getBvID() {
        return bvID;
    }

    public void setBvID(int bvID) {
        this.bvID = bvID;
    }

    public Date getCloseTime() {
        return CloseTime;
    }

    public void setCloseTime(Date closeTime) {
        CloseTime = closeTime;
    }

    public Date getTimelinePosition() {
        return TimelinePosition;
    }

    public void setTimelinePosition(Date timelinePosition) {
        TimelinePosition = timelinePosition;
    }
}
