package com.example.mtc;

import com.example.mtc.mapper.RelationMapper;
import com.example.mtc.model.Message;
import com.example.mtc.model.Relation;
import com.example.mtc.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class MessageTest {
  @Autowired
  private MessageService messageService;

  @Autowired
  private RelationMapper relationMapper;

  @Test
  void a() {
    Relation relation = new Relation();
    relation.setUserId(5L);
    relation.setDoctorId(5L);
    relationMapper.insertSelective(relation);
  }
}
