package com.example.mtc.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HealthRecord implements Serializable {
  private Long healthRecordId;

  private Long userId;

  private Date recordDate;

  private BigDecimal userBloodsugar;

  private Integer userLowBloodpressure;

  private Integer userHighBloodpressure;

  private Integer userHeartrate;

  private Integer userWeight;

  private String userHealthCondition;

  private static final long serialVersionUID = 1L;

  public Long getHealthRecordId() {
    return healthRecordId;
  }

  public void setHealthRecordId(Long healthRecordId) {
    this.healthRecordId = healthRecordId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Date getRecordDate() {
    return recordDate;
  }

  public void setRecordDate(Date recordDate) {
    this.recordDate = recordDate;
  }

  public BigDecimal getUserBloodsugar() {
    return userBloodsugar;
  }

  public void setUserBloodsugar(BigDecimal userBloodsugar) {
    this.userBloodsugar = userBloodsugar;
  }

  public Integer getUserLowBloodpressure() {
    return userLowBloodpressure;
  }

  public void setUserLowBloodpressure(Integer userLowBloodpressure) {
    this.userLowBloodpressure = userLowBloodpressure;
  }

  public Integer getUserHighBloodpressure() {
    return userHighBloodpressure;
  }

  public void setUserHighBloodpressure(Integer userHighBloodpressure) {
    this.userHighBloodpressure = userHighBloodpressure;
  }

  public Integer getUserHeartrate() {
    return userHeartrate;
  }

  public void setUserHeartrate(Integer userHeartrate) {
    this.userHeartrate = userHeartrate;
  }

  public Integer getUserWeight() {
    return userWeight;
  }

  public void setUserWeight(Integer userWeight) {
    this.userWeight = userWeight;
  }

  public String getUserHealthCondition() {
    return userHealthCondition;
  }

  public void setUserHealthCondition(String userHealthCondition) {
    this.userHealthCondition = userHealthCondition;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", healthRecordId=").append(healthRecordId);
    sb.append(", userId=").append(userId);
    sb.append(", recordDate=").append(recordDate);
    sb.append(", userBloodsugar=").append(userBloodsugar);
    sb.append(", userLowBloodpressure=").append(userLowBloodpressure);
    sb.append(", userHighBloodpressure=").append(userHighBloodpressure);
    sb.append(", userHeartrate=").append(userHeartrate);
    sb.append(", userWeight=").append(userWeight);
    sb.append(", userHealthCondition=").append(userHealthCondition);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}