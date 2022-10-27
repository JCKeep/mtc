package com.example.mtc.model;

import java.io.Serializable;
import java.util.Date;

public class HealthMedication implements Serializable {
    private Long medicationId;

    private Long userId;

    private Long drugId;

    private Date medicationDate;

    private String medicationType;

    private Boolean isTake;

    private static final long serialVersionUID = 1L;

    public Long getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(Long medicationId) {
        this.medicationId = medicationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }

    public Date getMedicationDate() {
        return medicationDate;
    }

    public void setMedicationDate(Date medicationDate) {
        this.medicationDate = medicationDate;
    }

    public String getMedicationType() {
        return medicationType;
    }

    public void setMedicationType(String medicationType) {
        this.medicationType = medicationType;
    }

    public Boolean getIsTake() {
        return isTake;
    }

    public void setIsTake(Boolean isTake) {
        this.isTake = isTake;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", medicationId=").append(medicationId);
        sb.append(", userId=").append(userId);
        sb.append(", drugId=").append(drugId);
        sb.append(", medicationDate=").append(medicationDate);
        sb.append(", medicationType=").append(medicationType);
        sb.append(", isTake=").append(isTake);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}