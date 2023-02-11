package com.example.mtc.mapper;

import com.example.mtc.model.DoctorVerify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@SuppressWarnings("ALL")
public interface DoctorVerifyMapper {
  int deleteByPrimaryKey(Long doctorId);

  int insert(DoctorVerify record);

  int insertSelective(DoctorVerify record);

  DoctorVerify selectByPrimaryKey(Long doctorId);

  int updateByPrimaryKeySelective(DoctorVerify record);

  int updateByPrimaryKeyWithBLOBs(DoctorVerify record);

  int updateByPrimaryKey(DoctorVerify record);

  List<DoctorVerify> selectAll();

  DoctorVerify selectByUserId(Long userId);

  List<DoctorVerify> selectAllToVerify();

  DoctorVerify selectByUserId1(Long userId);
}