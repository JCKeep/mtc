package com.example.mtc;

import com.example.mtc.mapper.DrugMapper;
import com.example.mtc.model.Drug;
import com.example.mtc.service.CommunityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class DrugMapperTest {
  @Autowired
  private CommunityService communityService;

  @Test
  void drugTest() {
    Drug drug = communityService.getDrugByID(3L);
    System.out.println(drug);
  }
}
