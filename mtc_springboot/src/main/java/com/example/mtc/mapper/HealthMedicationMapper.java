package com.example.mtc.mapper;

import com.example.mtc.model.HealthMedication;

public interface HealthMedicationMapper {
    int deleteByPrimaryKey(Long medicationId);

    int insert(HealthMedication record);

    int insertSelective(HealthMedication record);

    HealthMedication selectByPrimaryKey(Long medicationId);

    int updateByPrimaryKeySelective(HealthMedication record);

    int updateByPrimaryKey(HealthMedication record);
}