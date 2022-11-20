package com.example.mtc.service;

import com.example.mtc.model.DietRecord;

import java.util.Date;
import java.util.List;

public interface DietService {
  List<DietRecord> getByUserIdAndDate(Long userId, Date start, Date end);
  void insert(DietRecord dietRecord);
  void delete(Long id);
  void update(DietRecord dietRecord);
  DietRecord get(Long id);
}
