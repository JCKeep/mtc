package com.example.mtc.service;


import com.example.mtc.model.User;

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
}
