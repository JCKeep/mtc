package com.example.mtc.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("ALL")
public class LoginLog implements Serializable {
  private Long loginId;

  private Long userId;

  private Date loginTime;

  private static final long serialVersionUID = 1L;

  public Long getLoginId() {
    return loginId;
  }

  public void setLoginId(Long loginId) {
    this.loginId = loginId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Date getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Date loginTime) {
    this.loginTime = loginTime;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() +
            " [" +
            "Hash = " + hashCode() +
            ", loginId=" + loginId +
            ", userId=" + userId +
            ", loginTime=" + loginTime +
            ", serialVersionUID=" + serialVersionUID +
            "]";
  }
}