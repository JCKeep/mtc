package com.example.mtc;

import com.example.mtc.mapper.FoodMapper;
import com.example.mtc.mapper.HealthMedicationMapper;
import com.example.mtc.model.HealthMedication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FoodMapperTest {
  @Autowired
  private FoodMapper foodMapper;
  @Autowired
  private HealthMedicationMapper medicationMapper;

  @Test
  void test() {
    System.out.println(foodMapper.selectByPrimaryKey(19L));
    HealthMedication healthMedication = medicationMapper.selectByUserId(1L, 2L, null, null, "午间").get(0);
    System.out.println(healthMedication);
    healthMedication.setIsTake(true);
    medicationMapper.updateByUserIdAndDateSelective(healthMedication);
  }
}
