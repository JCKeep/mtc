package com.example.mtc;

import com.example.mtc.mapper.AdminMapper;
import com.example.mtc.mapper.DoctorVerifyMapper;
import com.example.mtc.mapper.MessageMapper;
import com.example.mtc.model.Admin;
import com.example.mtc.model.DoctorVerify;
import com.example.mtc.service.MailService;
import com.example.mtc.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;

@SpringBootTest
class DemoApplicationTests {
  @Autowired
  private DoctorVerifyMapper doctorVerifyMapper;

  @Autowired
  private MessageMapper messageMapper;

  @Test
  void test() {
    System.out.println(messageMapper.peekMsg(35L, 5L));
  }
}
