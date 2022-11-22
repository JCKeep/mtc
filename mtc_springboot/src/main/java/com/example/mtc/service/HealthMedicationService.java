package com.example.mtc.service;

import com.example.mtc.model.HealthMedication;

import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
public interface HealthMedicationService {
  void add(HealthMedication medication);
  List<HealthMedication> get(Long userId, Long drugId, Date start, Date end, String type);
  void update(HealthMedication medication);
  void delete(Long userId, Long drugId, Date start, Date end, String type);
  void flushAll();
}
