package com.example.mtc.model;

import java.io.Serializable;

public class Food implements Serializable {
    private Long foodId;

    private String foodName;

    private Float foodSuger;

    private Float foodProtein;

    private Float foodFat;

    private Float foodEnergy;

    private byte[] foodImage;

    private String foodIntroduction;

    private static final long serialVersionUID = 1L;

    public Long getFoodId() {
        return foodId;
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

    public byte[] getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(byte[] foodImage) {
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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", foodId=").append(foodId);
        sb.append(", foodName=").append(foodName);
        sb.append(", foodSuger=").append(foodSuger);
        sb.append(", foodProtein=").append(foodProtein);
        sb.append(", foodFat=").append(foodFat);
        sb.append(", foodEnergy=").append(foodEnergy);
        sb.append(", foodImage=").append(foodImage);
        sb.append(", foodIntroduction=").append(foodIntroduction);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}