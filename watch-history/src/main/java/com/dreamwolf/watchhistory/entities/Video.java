package com.dreamwolf.watchhistory.entities;

import java.util.Date;

/**
 * @author: wzx
 * @data: 2021/4/13 9:35
 * @version: 1.0
 */
public class Video {
    private int bvID;//bv号
    private int uID;//视频作者ID
    private String bvTitle;//视频标题
    private String bvDesc;//视频简介
    private Date bvPostTime;//视频上传日期（默认为当前时间）
    private int bvChildZoning;//视频子分区ID
    private int bvIsDel;//是否已删除，0为未删除，1为已删除
    public int getBvID() {
        return bvID;
    }

    public void setBvID(int bvID) {
        this.bvID = bvID;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getBvTitle() {
        return bvTitle;
    }

    public void setBvTitle(String bvTitle) {
        this.bvTitle = bvTitle;
    }

    public String getBvDesc() {
        return bvDesc;
    }

    public void setBvDesc(String bvDesc) {
        this.bvDesc = bvDesc;
    }

    public Date getBvPostTime() {
        return bvPostTime;
    }

    public void setBvPostTime(Date bvPostTime) {
        this.bvPostTime = bvPostTime;
    }

    public int getBvChildZoning() {
        return bvChildZoning;
    }

    public void setBvChildZoning(int bvChildZoning) {
        this.bvChildZoning = bvChildZoning;
    }

    public int getBvIsDel() {
        return bvIsDel;
    }

    public void setBvIsDel(int bvIsDel) {
        this.bvIsDel = bvIsDel;
    }




}
