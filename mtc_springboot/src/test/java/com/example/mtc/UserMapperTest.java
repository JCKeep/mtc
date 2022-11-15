package com.example.mtc;

import com.example.mtc.mapper.UserMapper;
import com.example.mtc.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserMapperTest {
  @Autowired
  private UserMapper userMapper;

//  @Test
//  void insertTest() {
//    User user = new User();
//    user.setUserName("jckeep");
//    user.setUserEmail("240701837@qq.com");
//
//    userMapper.insertSelective(user);
//
//    userMapper.selectAll().forEach(System.out::println);
//    System.out.println(userMapper.selectByEmail(user.getUserEmail()));
//  }
}
