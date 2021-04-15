package com.dreamwolf.watchhistory.entities;

/**
 * @author: wzx
 * @data: 2021/4/14 10:06
 * @version: 1.0
 */
public class User {
    private int uID;
    private String userName;//用户名
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "uID=" + uID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private String nickName;
}
