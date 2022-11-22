package com.example.mtc;

import com.example.mtc.mapper.FoodMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FoodMapperTest {
  @Autowired
  private FoodMapper foodMapper;

  @Test
  void test() {
    System.out.println(foodMapper.selectByPrimaryKey(19L));
  }
}
