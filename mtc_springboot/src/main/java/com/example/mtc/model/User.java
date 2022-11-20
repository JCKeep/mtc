package com.example.mtc.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("ALL")
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

  private String userPortrait;

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

  public String getUserPortrait() {
    return userPortrait;
  }

  public void setUserPortrait(String userPortrait) {
    this.userPortrait = userPortrait;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() +
            " [" +
            "Hash = " + hashCode() +
            ", userId=" + userId +
            ", userName=" + userName +
            ", userEmail=" + userEmail +
            ", userSex=" + userSex +
            ", userPassword=" + userPassword +
            ", userBloodtype=" + userBloodtype +
            ", userBirthday=" + userBirthday +
            ", userHeight=" + userHeight +
            ", userType=" + userType +
            ", userPermission=" + userPermission +
            ", userKeyword=" + userKeyword +
            ", userPortrait=" + String.valueOf(userPortrait) +
            ", serialVersionUID=" + serialVersionUID +
            "]";
  }
}