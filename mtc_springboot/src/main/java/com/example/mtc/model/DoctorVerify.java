package com.example.mtc.model;

import java.io.Serializable;

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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", doctorId=").append(doctorId);
        sb.append(", userId=").append(userId);
        sb.append(", doctorIdnumber=").append(doctorIdnumber);
        sb.append(", doctorHospital=").append(doctorHospital);
        sb.append(", verifyState=").append(verifyState);
        sb.append(", adminId=").append(adminId);
        sb.append(", doctorQualification=").append(doctorQualification);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}