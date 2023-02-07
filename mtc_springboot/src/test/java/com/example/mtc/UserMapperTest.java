package com.example.mtc;

import com.example.mtc.mapper.UserMapper;
import com.example.mtc.model.User;
import com.example.mtc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserMapperTest {
  @Autowired
  private UserService userService;

  @Test
  void insertTest() {
    User user = userService.getUserByEmail("2407018371@qq.com");
    System.out.println(user);
    userService.changeUserState(user.getUserId());
    System.out.println(userService.getUserByEmail("2407018371@qqcom"));
  }
}
