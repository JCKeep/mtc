package com.example.mtc.service;


import com.example.mtc.controller.UserController;
import com.example.mtc.model.User;

import java.util.List;

@SuppressWarnings("ALL")
public interface UserService {
  boolean login(User user);
  boolean register(User user, String code);
  String verifyCode(User user);
  User getUserByEmailWithNull(String email);
  User getUserByEmail(String email);
  void updateUser(User user);
  User getUserById(Integer id);

  void delete(Long userId);

  Boolean changePasswd(User user, String code);

  void changeUserState(Long userId);
  Integer getUserType(Long userId);

  List<UserController.UserL> getUserList();
}
