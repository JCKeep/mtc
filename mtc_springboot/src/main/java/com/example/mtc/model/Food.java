package com.example.mtc.model;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class Food implements Serializable {
  private Long foodId;

  private String foodName;

  private Float foodSuger;

  private Float foodProtein;

  private Float foodFat;

  private Float foodEnergy;

  private String foodImage;

  private String foodIntroduction;
  private Float foodWeight;

  private static final long serialVersionUID = 1L;

  public Long getFoodId() {
    return foodId;
  }

  public Float getFoodWeight() {
    return foodWeight;
  }

  public void setFoodWeight(Float foodWeight) {
    this.foodWeight = foodWeight;
  }

  public void setFoodId(Long foodId) {
    this.foodId = foodId;
  }

  public String getFoodName() {
    return foodName;
  }

  public void setFoodName(String foodName) {
    this.foodName = foodName;
  }

  public Float getFoodSuger() {
    return foodSuger;
  }

  public void setFoodSuger(Float foodSuger) {
    this.foodSuger = foodSuger;
  }

  public Float getFoodProtein() {
    return foodProtein;
  }

  public void setFoodProtein(Float foodProtein) {
    this.foodProtein = foodProtein;
  }

  public Float getFoodFat() {
    return foodFat;
  }

  public void setFoodFat(Float foodFat) {
    this.foodFat = foodFat;
  }

  public Float getFoodEnergy() {
    return foodEnergy;
  }

  public void setFoodEnergy(Float foodEnergy) {
    this.foodEnergy = foodEnergy;
  }

  public String getFoodImage() {
    return foodImage;
  }

  public void setFoodImage(String foodImage) {
    this.foodImage = foodImage;
  }

  public String getFoodIntroduction() {
    return foodIntroduction;
  }

  public void setFoodIntroduction(String foodIntroduction) {
    this.foodIntroduction = foodIntroduction;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() +
            " [" +
            "Hash = " + hashCode() +
            ", foodId=" + foodId +
            ", foodName=" + foodName +
            ", foodSuger=" + foodSuger +
            ", foodProtein=" + foodProtein +
            ", foodFat=" + foodFat +
            ", foodEnergy=" + foodEnergy +
            ", foodWeight=" + foodWeight +
            ", foodImage=" + String.valueOf(foodImage) +
            ", foodIntroduction=" + foodIntroduction +
            ", serialVersionUID=" + serialVersionUID +
            "]";
  }
}