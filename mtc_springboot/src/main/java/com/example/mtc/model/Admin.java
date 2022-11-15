package com.example.mtc.model;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class Admin implements Serializable {
  private Long adminId;

  private String adminPassword;

  private static final long serialVersionUID = 1L;

  public Long getAdminId() {
    return adminId;
  }

  public void setAdminId(Long adminId) {
    this.adminId = adminId;
  }

  public String getAdminPassword() {
    return adminPassword;
  }

  public void setAdminPassword(String adminPassword) {
    this.adminPassword = adminPassword;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() +
            " [" +
            "Hash = " + hashCode() +
            ", adminId=" + adminId +
            ", adminPassword=" + adminPassword +
            ", serialVersionUID=" + serialVersionUID +
            "]";
  }
}