package com.entities;

///**
// * @author: wzx
// * @data: 2021/4/7 9:42
// * @version: 1.0
// */
//收藏夹状态表
public class FavoriteLikeEntities {
    private int favLisetID;//被点赞的收藏夹ID
    private int uID;//给予点赞的用户ID
    private int status;//点赞状态，0为未点赞，1为已点赞

    public int getFavLisetID() {
        return favLisetID;
    }

    public void setFavLisetID(int favLisetID) {
        this.favLisetID = favLisetID;
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
