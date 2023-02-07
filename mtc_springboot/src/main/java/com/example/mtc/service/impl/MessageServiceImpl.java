package com.example.mtc.service.impl;

import com.example.mtc.controller.MessageController;
import com.example.mtc.mapper.MessageMapper;
import com.example.mtc.mapper.UserMapper;
import com.example.mtc.model.Message;
import com.example.mtc.model.User;
import com.example.mtc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
  @Autowired
  private MessageMapper messageMapper;
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  public static String genHashKey(Long a, Long b) {
      return "MTC_USER_HASH_" + a + "_" + b;
  }

  @Override
  public int send(Message message) {
    messageMapper.insertSelective(message);
    stringRedisTemplate.opsForHash().increment(genHashKey(message.getUserId(),
            message.getMessageFrom()), "UNREAD_MSG", 1);
    stringRedisTemplate.opsForSet()
            .add("MTC_USER_CONTENT_SET_" + message.getUserId(),
                    String.valueOf(message.getMessageFrom()));
    stringRedisTemplate.opsForSet()
            .add("MTC_USER_CONTENT_SET_" + message.getMessageFrom(),
                    String.valueOf(message.getUserId()));

    return 0;
  }

  @Override
  public List<Message> getMessage(Long myId, Long uid) {
    List<Message> msg = messageMapper.selectMessage(myId, uid);
    stringRedisTemplate.opsForHash().put(genHashKey(myId, uid),
            "UNREAD_MSG", String.valueOf(0));

    return msg;
  }

  @Override
  public List<MessageController.ChatList> getChatList(Long userId) {
    List<MessageController.ChatList> set = stringRedisTemplate.opsForSet()
            .members("MTC_USER_CONTENT_SET_" + userId)
            .stream()
            .map(s -> {
              Long id = Long.valueOf(s);
              Message message = messageMapper.peekMsg(userId, id);
              User user = userMapper.selectByPrimaryKey(id);
              String count = (String) stringRedisTemplate.opsForHash()
                      .get(genHashKey(message.getUserId(),
                              message.getMessageFrom()), "UNREAD_MSG");
              MessageController.ChatList chatList =
                      new MessageController.ChatList();
              chatList.setUserId(id);
              chatList.setUserName(user.getUserName());
              chatList.setUserImage(user.getUserPortrait());
              chatList.setLastMassage(message.getMessageContent());
              chatList.setLastTime(message.getMessageType());
              assert count != null;
              chatList.setUnreadCount(Long.valueOf(count));
              return chatList;
            })
            .collect(Collectors.toList());

    return set;
  }
}
