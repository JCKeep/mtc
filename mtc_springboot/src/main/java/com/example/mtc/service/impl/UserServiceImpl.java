package com.example.mtc.service.impl;

import com.example.mtc.controller.UserController;
import com.example.mtc.mapper.LoginLogMapper;
import com.example.mtc.mapper.UserMapper;
import com.example.mtc.model.LoginLog;
import com.example.mtc.model.User;
import com.example.mtc.service.MailService;
import com.example.mtc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;


@Slf4j
@Service
@SuppressWarnings("ALL")
public class UserServiceImpl implements UserService {
  static ConcurrentHashMap<String, String> m = new ConcurrentHashMap<>();
  static Random random = new Random();

  @Autowired
  private UserMapper userMapper;
  @Autowired
  private LoginLogMapper loginLogMapper;
  @Autowired
  private MailService mailService;

  @Override
  public boolean login(User user) {
    User u = userMapper.selectByEmail(user.getUserEmail());
    if (u == null) {
      return Boolean.FALSE;
    } else {
      if (u.getUserState() != 1) {
        return Boolean.FALSE;
      }
      if (u.getUserPassword().equals(user.getUserPassword())) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(u.getUserId());
        loginLog.setLoginTime(new Date(System.currentTimeMillis()));
        loginLogMapper.insert(loginLog);
        log.info(u.getUserEmail() + " login");
        return Boolean.TRUE;
      } else {
        return Boolean.FALSE;
      }
    }
  }

  @Override
  public boolean register(User user, String code) {
    String c = m.get(user.getUserEmail());
    log.info(c);
    if (c == null || !c.equals(code)) {
      return Boolean.FALSE;
    }
    try {
      userMapper.insertSelective(user);
      m.remove(user.getUserEmail());
      return Boolean.TRUE;
    } catch (Exception e) {
      e.printStackTrace();
      return Boolean.FALSE;
    }
  }

  @Override
  public String verifyCode(User user) {
    String code = String.format("%06d", Math.abs(random.nextInt() % 1000000));
    m.remove(user.getUserEmail());
    m.put(user.getUserEmail(), code);
    mailService.sendMail("jckeep@foxmail.com", user.getUserEmail(), "Code", code);
    return code;
  }

  @Override
  public User getUserByEmailWithNull(String email) {
    return userMapper.selectByEmailWithNull(email);
  }

  @Override
  public User getUserByEmail(String email) {
    return userMapper.selectByEmail(email);
  }

  @Override
  public void updateUser(User user) {
    userMapper.updateByPrimaryKeySelective(user);
  }

  @Override
  public User getUserById(Integer id) {
    return userMapper.selectByPrimaryKey(Long.valueOf(id));
  }

  @Override
  public void delete(Long userId) {
    userMapper.deleteByPrimaryKey(userId);
  }

  @Override
  public Boolean changePasswd(User user, String code) {
    String c = m.get(user.getUserEmail());
    if (c == null || !c.equals(code)) {
      return Boolean.FALSE;
    }
    try {
      userMapper.updateByPrimaryKeySelective(user);
      m.remove(user.getUserEmail());
      return Boolean.TRUE;
    } catch (Exception e) {
      e.printStackTrace();
      return Boolean.FALSE;
    }
  }

  @Override
  public void changeUserState(Long userId) {
    User user = userMapper.selectByPrimaryKey(userId);
    userMapper.updateUserState(userId, user.getUserState() ^ 1);
  }

  @Override
  public Integer getUserType(Long userId) {
    User user = userMapper.selectByPrimaryKey(userId);

    if (user.getUserType().equals("00")) {
      return 0;
    } else if (user.getUserType().equals("01")) {
      return 1;
    } else {
      return 2;
    }
  }

  @Override
  public List<UserController.UserL> getUserList() {
    return
    userMapper.selectAll()
            .stream()
            .map(user -> {
              UserController.UserL userL = new UserController.UserL();

              userL.setUseremail(user.getUserEmail());
              userL.setUserid(user.getUserId());
              userL.setUserpic(user.getUserPortrait());
              userL.setUsername(user.getUserName());
              userL.setUserstate(user.getUserState());

              return userL;
            })
            .collect(Collectors.toList());
  }
}
