package com.example.mtc.service.impl;

import com.example.mtc.mapper.HealthMedicationMapper;
import com.example.mtc.model.HealthMedication;
import com.example.mtc.service.HealthMedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("ALL")
public class HMSI implements HealthMedicationService {
  @Autowired
  private HealthMedicationMapper healthMedicationMapper;

  @Override
  public void add(HealthMedication medication) {
    healthMedicationMapper.insertSelective(medication);
  }

  @Override
  public List<HealthMedication> get(Long userId, Date start, Date end, String type) {
    return healthMedicationMapper.selectByUserId(userId, start, end, type);
  }

  @Override
  public void update(HealthMedication medication) {
    healthMedicationMapper.updateByUserIdAndDateSelective(medication);
  }

  @Override
  public void delete(Long userId, Date start, Date end, String type) {
    healthMedicationMapper.deleteByUserId(userId, start, end, type);
  }
}
