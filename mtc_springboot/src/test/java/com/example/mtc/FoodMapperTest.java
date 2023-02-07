package com.example.mtc;

import com.example.mtc.service.DietFoodService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class FoodMapperTest {
  @Autowired
  private DietFoodService foodMapper;

//  @Autowired
//  private FoodMapper foodMapper;
//  @Autowired
//  private HealthMedicationMapper medicationMapper;
//
//  @Test
//  void test() {
//    System.out.println(foodMapper.selectByPrimaryKey(19L));
//    HealthMedication healthMedication = medicationMapper.selectByUserId(1L, 2L, null, null, "午间").get(0);
//    System.out.println(healthMedication);
//    healthMedication.setIsTake(true);
//    medicationMapper.updateByUserIdAndDateSelective(healthMedication);
//  }

  @Test
  void newFoodTest() {
    System.out.println(foodMapper.selectByPrimaryKey(10L));
    Date date = new Date();
    Date prev = DateUtils.addDays(date, -7);
    System.out.println(date + "\n" + prev);
  }
}
