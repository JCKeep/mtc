package com.example.mtc.service;


import com.example.mtc.model.User;

public interface UserService {
  boolean login(User user);
  boolean register(User user, String code);
  String verifyCode(User user);
}
