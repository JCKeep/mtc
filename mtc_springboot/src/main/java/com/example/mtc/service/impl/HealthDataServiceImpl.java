package com.example.mtc.service.impl;

import com.example.mtc.mapper.HealthRecordMapper;
import com.example.mtc.model.HealthRecord;
import com.example.mtc.service.HealthDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("ALL")
public class HealthDataServiceImpl implements HealthDataService {
  @Autowired
  private HealthRecordMapper healthRecordMapper;

  @Override
  public List<HealthRecord> getHealthData(Long userId, Date start, Date end) {
    return healthRecordMapper.selectByUserId(userId, start, end);
  }

  @Override
  public List<HealthRecord> getHealthData(Long userId, int year, int month, int day) {
    Date date = new Date(year - 1900, month - 1, day);
    return healthRecordMapper.selectByUserId(userId, date, date);
  }

  @Override
  public void addHealthData(HealthRecord healthRecord) {
    if (healthRecordMapper.selectByUserId(healthRecord.getUserId(),
            healthRecord.getRecordDate(), healthRecord.getRecordDate()).size() == 0) {
      healthRecordMapper.insertSelective(healthRecord);
    } else {
      healthRecordMapper.updateByUserIdSelective(healthRecord);
    }
  }

  @Override
  public void addHealthData(Long userId, Date date, BigDecimal userBloodsugar,
                            Integer userLowBloodpressure, Integer userHighBloodpressure,
                            Integer userHeartrate, Integer userWeight, String userHealthCondition) {
    HealthRecord healthRecord = new HealthRecord(
            null,
            userId,
            date,
            userBloodsugar,
            userLowBloodpressure,
            userHighBloodpressure,
            userHeartrate, userWeight,
            userHealthCondition
    );
    if (healthRecordMapper.selectByUserId(userId, date, date).size() == 0) {
      healthRecordMapper.insertSelective(healthRecord);
    } else {
      healthRecordMapper.updateByUserIdSelective(healthRecord);
    }
  }

  @Override
  public void deleteHealthData(Long userId, Date start, Date end) {
    healthRecordMapper.deleteByUserId(userId, start, end);
  }

  @Override
  public void deleteHealthData(Long userId, int year, int month, int day) {
    Date date = new Date(year - 1900, month - 1, day);
    healthRecordMapper.deleteByUserId(userId, date, date);
  }


}
