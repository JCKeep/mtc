package com.example.mtc.model;

import java.io.Serializable;
import java.util.Date;

public class DietRecord implements Serializable {
  private Long dietId;

  private Long foodId;

  private Long userId;

  private Date dietDate;

  private String dietType;

  private static final long serialVersionUID = 1L;

  public Long getDietId() {
    return dietId;
  }

  public void setDietId(Long dietId) {
    this.dietId = dietId;
  }

  public Long getFoodId() {
    return foodId;
  }

  public void setFoodId(Long foodId) {
    this.foodId = foodId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Date getDietDate() {
    return dietDate;
  }

  public void setDietDate(Date dietDate) {
    this.dietDate = dietDate;
  }

  public String getDietType() {
    return dietType;
  }

  public void setDietType(String dietType) {
    this.dietType = dietType;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", dietId=").append(dietId);
    sb.append(", foodId=").append(foodId);
    sb.append(", userId=").append(userId);
    sb.append(", dietDate=").append(dietDate);
    sb.append(", dietType=").append(dietType);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}