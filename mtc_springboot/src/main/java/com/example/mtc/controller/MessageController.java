package com.example.mtc.controller;

import com.example.mtc.model.Message;
import com.example.mtc.service.MessageService;
import com.example.mtc.util.JsonResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/mtc/msg")
public class MessageController {
  @Autowired
  private MessageService messageService;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class SendMsgBox {
    private Long myId;
    private Long receiverId;
    private String text;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class ChatRecord {
    private Long messageSender;
    private String messageText;
    private Date messageTime;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ChatList {
    private Long userId;
    private String userImage;
    private String userName;
    private Date lastTime;
    private String lastMassage;
    private Long unreadCount;
  }

  @PostMapping("/send")
  public JsonResult<?> sendMsg(@RequestBody SendMsgBox msg) {
    Message message = new Message();
    message.setUserId(msg.receiverId);
    message.setMessageFrom(msg.myId);
    message.setMessageContent(msg.text);
    message.setMessageType(new Date());

    messageService.send(message);
    return JsonResult.success();
  }

  @GetMapping("/peekmsg")
  public JsonResult<?> peekMsg(Long myId, Long uid) {
    Message msg = messageService.peek(myId, uid);
    ChatRecord chatRecord = new ChatRecord();
    if (Objects.equals(msg.getUserId(), myId)) {
      chatRecord.messageSender = 0L;
    } else {
      chatRecord.messageSender = 1L;
    }
    chatRecord.messageText = msg.getMessageContent();
    chatRecord.messageTime = msg.getMessageType();
    return JsonResult.success(chatRecord);
  }

  @GetMapping("/getmsg")
  public JsonResult<?> getMsg(Long myId, Long uid) {
    List<Message> msgList = messageService.getMessage(myId, uid);
    List<ChatRecord> records = new ArrayList<>();

    for (Message msg : msgList) {
      ChatRecord chatRecord = new ChatRecord();

      if (Objects.equals(msg.getUserId(), myId)) {
        chatRecord.messageSender = 0L;
      } else {
        chatRecord.messageSender = 1L;
      }
      chatRecord.messageText = msg.getMessageContent();
      chatRecord.messageTime = msg.getMessageType();
      records.add(chatRecord);
    }

    return JsonResult.success(records);
  }

  @GetMapping("/chatlist")
  public JsonResult<?> getChatList(Long userId) {
    return JsonResult.success(messageService.getChatList(userId));
  }
}
