package com.entities;

///**
// * @author: wzx
// * @data: 2021/4/7 9:47
// * @version: 1.0
// */
//用户收藏列表
public class UserFavoriteListEntities {
    private int favListID;//视频收藏夹ID，主键唯一
    private int uID;//创建收藏夹的用户ID
    private String name;//收藏夹名称，可以重复
    private int isSecret;//是否为私密收藏夹，私密为1，公开为0
    private int tLikeNum;//收藏夹的总点赞数
    private String desc;//收藏夹描述

    public int getFavListID() {
        return favListID;
    }

    public void setFavListID(int favListID) {
        this.favListID = favListID;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(int isSecret) {
        this.isSecret = isSecret;
    }

    public int gettLikeNum() {
        return tLikeNum;
    }

    public void settLikeNum(int tLikeNum) {
        this.tLikeNum = tLikeNum;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
