package com.example.mtc.service;

public interface MailService {
  void sendMail(String from, String to, String Subject, String content);
}
