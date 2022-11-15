package com.example.mtc.service;

@SuppressWarnings("ALL")
public interface MailService {
  void sendMail(String from, String to, String Subject, String content);
}
