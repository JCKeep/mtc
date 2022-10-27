package com.example.mtc.mapper;

import com.example.mtc.model.DoctorVerify;

public interface DoctorVerifyMapper {
    int deleteByPrimaryKey(Long doctorId);

    int insert(DoctorVerify record);

    int insertSelective(DoctorVerify record);

    DoctorVerify selectByPrimaryKey(Long doctorId);

    int updateByPrimaryKeySelective(DoctorVerify record);

    int updateByPrimaryKeyWithBLOBs(DoctorVerify record);

    int updateByPrimaryKey(DoctorVerify record);
}