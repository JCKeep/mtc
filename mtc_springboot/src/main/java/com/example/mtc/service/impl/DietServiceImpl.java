package com.example.mtc.service.impl;

import com.example.mtc.mapper.DietRecordMapper;
import com.example.mtc.model.DietRecord;
import com.example.mtc.service.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DietServiceImpl implements DietService {
  @Autowired
  private DietRecordMapper dietRecordMapper;

  @Override
  public List<DietRecord> getByUserIdAndDate(Long userId, Date start, Date end) {
    return dietRecordMapper.selectByUserIdAndDate(userId, start, end);
  }

  @Override
  public void insert(DietRecord dietRecord) {
    dietRecordMapper.insert(dietRecord);
  }

  @Override
  public void delete(Long id) {
    dietRecordMapper.deleteByPrimaryKey(id);
  }

  @Override
  public void update(DietRecord dietRecord) {
    dietRecordMapper.updateByPrimaryKeySelective(dietRecord);
  }

  @Override
  public DietRecord get(Long id) {
    return dietRecordMapper.selectByPrimaryKey(id);
  }
}
