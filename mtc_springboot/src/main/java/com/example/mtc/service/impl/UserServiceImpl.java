package com.example.mtc.service.impl;

import com.example.mtc.mapper.UserMapper;
import com.example.mtc.model.User;
import com.example.mtc.service.MailService;
import com.example.mtc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserServiceImpl implements UserService {
  static ConcurrentHashMap<String, String> m = new ConcurrentHashMap<>();
  static Random random = new Random();
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private MailService mailService;

  @Override
  public boolean login(User user) {
    User u = userMapper.selectByEmail(user.getUserEmail());
    if (u == null) {
      return Boolean.FALSE;
    } else {
      return u.getUserPassword().equals(user.getUserPassword());
    }
  }

  @Override
  public boolean register(User user, String code) {
    if (code == null || !m.get(user.getUserEmail()).equals(code)) {
      return Boolean.FALSE;
    }
    try {
      userMapper.insertSelective(user);
      m.remove(user.getUserEmail());
      return Boolean.TRUE;
    } catch (Exception e) {
      return Boolean.FALSE;
    }
  }

  @Override
  public String verifyCode(User user) {
    String code = String.format("%06d", Math.abs(random.nextInt() % 1000000));
    m.put(user.getUserEmail(), code);
    mailService.sendMail("jckeep@foxmail.com", user.getUserEmail(), "Code", code);
    return code;
  }
}
