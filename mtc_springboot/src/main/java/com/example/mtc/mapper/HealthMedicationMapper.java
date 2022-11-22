package com.example.mtc.mapper;

import com.example.mtc.model.HealthMedication;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
@SuppressWarnings("ALL")
public interface HealthMedicationMapper {
  int deleteByPrimaryKey(Long medicationId);

  int deleteByUserId(Long userId, Long drugId, Date start, Date end, String type);

  int insert(HealthMedication record);

  int insertSelective(HealthMedication record);

  HealthMedication selectByPrimaryKey(Long medicationId);
  List<HealthMedication> selectByUserId(Long userId, Long drugId, Date start, Date end, String type);


  int updateByPrimaryKeySelective(HealthMedication record);

  int updateByUserIdAndDateSelective(HealthMedication record);

  int updateByPrimaryKey(HealthMedication record);

  int flushAll();
}