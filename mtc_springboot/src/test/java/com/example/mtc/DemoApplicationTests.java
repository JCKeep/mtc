package com.example.mtc;

import com.example.mtc.mapper.AdminMapper;
import com.example.mtc.model.Admin;
import com.example.mtc.service.MailService;
import com.example.mtc.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;

@SpringBootTest
class DemoApplicationTests {
//	@Autowired
//	private AdminMapper adminMapper;
//	@Autowired
//	private MailService mailService;
//

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

	@Test
	void contextLoads() {
    Date date = DateUtil.parse("2022-1-1");
    System.out.println(date);
    System.out.println((date.getYear() + 1900) + ", " + (date.getMonth() + 1) + ", " + date.getDate());

    stringRedisTemplate.opsForValue().set("SPRING", "BOOT");
  }
//
//	@Test
//	void mailTest() {
//		mailService.sendMail("2407018371@qq.com", "2407018371@qq.com", "test", "test");
//	}
}
