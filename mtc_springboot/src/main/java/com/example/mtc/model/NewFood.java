package com.example.mtc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewFood implements Serializable {
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
}