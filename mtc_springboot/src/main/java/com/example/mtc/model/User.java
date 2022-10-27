package com.example.mtc.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Long userId;

    private String userName;

    private String userEmail;

    private String userSex;

    private String userPassword;

    private String userBloodtype;

    private Date userBirthday;

    private Integer userHeight;

    private String userType;

    private String userPermission;

    private String userKeyword;

    private byte[] userPortrait;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserBloodtype() {
        return userBloodtype;
    }

    public void setUserBloodtype(String userBloodtype) {
        this.userBloodtype = userBloodtype;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Integer getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(Integer userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserPermission() {
        return userPermission;
    }

    public void setUserPermission(String userPermission) {
        this.userPermission = userPermission;
    }

    public String getUserKeyword() {
        return userKeyword;
    }

    public void setUserKeyword(String userKeyword) {
        this.userKeyword = userKeyword;
    }

    public byte[] getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(byte[] userPortrait) {
        this.userPortrait = userPortrait;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", userEmail=").append(userEmail);
        sb.append(", userSex=").append(userSex);
        sb.append(", userPassword=").append(userPassword);
        sb.append(", userBloodtype=").append(userBloodtype);
        sb.append(", userBirthday=").append(userBirthday);
        sb.append(", userHeight=").append(userHeight);
        sb.append(", userType=").append(userType);
        sb.append(", userPermission=").append(userPermission);
        sb.append(", userKeyword=").append(userKeyword);
        sb.append(", userPortrait=").append(userPortrait);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}