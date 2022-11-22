package com.example.mtc.service;

import com.example.mtc.model.HealthRecord;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
public interface HealthDataService {
  List<HealthRecord> getHealthData(Long userId, Date start, Date end);
  List<HealthRecord> getHealthData(Long userId, int year, int month, int day);
  void addHealthData(HealthRecord healthRecord);
  void addHealthData(Long userId, Date date, BigDecimal userBloodsugar,
                     Integer userLowBloodpressure, Integer userHighBloodpressure,
                     Integer userHeartrate, Integer userWeight, String userHealthCondition);

  void deleteHealthData(Long userId, Date start, Date end);
  void deleteHealthData(Long userId, int year, int month, int day);
}
