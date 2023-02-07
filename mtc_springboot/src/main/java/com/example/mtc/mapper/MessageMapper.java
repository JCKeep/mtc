package com.example.mtc.mapper;

import com.example.mtc.model.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
@SuppressWarnings("ALL")
public interface MessageMapper {
  int deleteByPrimaryKey(Long messageId);

  int insert(Message record);

  int insertSelective(Message record);

  Message selectByPrimaryKey(Long messageId);

  int updateByPrimaryKeySelective(Message record);

  int updateByPrimaryKeyWithBLOBs(Message record);

  int updateByPrimaryKey(Message record);

  List<Message> selectMessage(Long myId, Long uid);

  Message peekMsg(Long myId, Long uid);
}