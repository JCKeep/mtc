package com.example.mtc.model;

import java.io.Serializable;

public class HealthSuggest implements Serializable {
  private Long suggestId;

  private String healthDataType;

  private String healthDataRange;

  private String suggestContent;

  private static final long serialVersionUID = 1L;

  public Long getSuggestId() {
    return suggestId;
  }

  public void setSuggestId(Long suggestId) {
    this.suggestId = suggestId;
  }

  public String getHealthDataType() {
    return healthDataType;
  }

  public void setHealthDataType(String healthDataType) {
    this.healthDataType = healthDataType;
  }

  public String getHealthDataRange() {
    return healthDataRange;
  }

  public void setHealthDataRange(String healthDataRange) {
    this.healthDataRange = healthDataRange;
  }

  public String getSuggestContent() {
    return suggestContent;
  }

  public void setSuggestContent(String suggestContent) {
    this.suggestContent = suggestContent;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", suggestId=").append(suggestId);
    sb.append(", healthDataType=").append(healthDataType);
    sb.append(", healthDataRange=").append(healthDataRange);
    sb.append(", suggestContent=").append(suggestContent);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}