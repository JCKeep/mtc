package com.example.mtc.service;

import com.example.mtc.controller.MessageController;
import com.example.mtc.model.Message;

import java.util.List;

public interface MessageService {
  int send(Message message);
  List<Message> getMessage(Long myId, Long uid);
  List<MessageController.ChatList> getChatList(Long userId);
}
