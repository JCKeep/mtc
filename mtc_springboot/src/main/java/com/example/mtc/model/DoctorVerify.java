package com.example.mtc.model;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class DoctorVerify implements Serializable {
  private Long doctorId;

  private Long userId;

  private String doctorIdnumber;

  private String doctorHospital;

  private String verifyState;

  private Long adminId;

  private byte[] doctorQualification;

  private static final long serialVersionUID = 1L;

  public Long getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(Long doctorId) {
    this.doctorId = doctorId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getDoctorIdnumber() {
    return doctorIdnumber;
  }

  public void setDoctorIdnumber(String doctorIdnumber) {
    this.doctorIdnumber = doctorIdnumber;
  }

  public String getDoctorHospital() {
    return doctorHospital;
  }

  public void setDoctorHospital(String doctorHospital) {
    this.doctorHospital = doctorHospital;
  }

  public String getVerifyState() {
    return verifyState;
  }

  public void setVerifyState(String verifyState) {
    this.verifyState = verifyState;
  }

  public Long getAdminId() {
    return adminId;
  }

  public void setAdminId(Long adminId) {
    this.adminId = adminId;
  }

  public byte[] getDoctorQualification() {
    return doctorQualification;
  }

  public void setDoctorQualification(byte[] doctorQualification) {
    this.doctorQualification = doctorQualification;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() +
            " [" +
            "Hash = " + hashCode() +
            ", doctorId=" + doctorId +
            ", userId=" + userId +
            ", doctorIdnumber=" + doctorIdnumber +
            ", doctorHospital=" + doctorHospital +
            ", verifyState=" + verifyState +
            ", adminId=" + adminId +
            ", doctorQualification=" + String.valueOf(doctorQualification) +
            ", serialVersionUID=" + serialVersionUID +
            "]";
  }
}