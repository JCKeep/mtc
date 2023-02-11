package com.example.mtc.service;

import com.example.mtc.controller.CommunityController;
import com.example.mtc.model.DoctorVerify;
import com.example.mtc.model.Drug;
import com.example.mtc.model.Food;

import java.util.List;

@SuppressWarnings("ALL")
public interface CommunityService {
  List<Food> getFoodByRange(Long l, Long r, Boolean option);
  List<Food> getFoodByName(String name, Boolean option);
  String foodRecognize(String path);
  String foodRecognize(byte[] p);
  String foodRecognizeByURL(String url);
  List<Drug> getDrugByRange(Long l, Long r, Boolean option);
  List<Drug> getDrugByName(String name, Boolean option);
  Food getFoodById(Long id);
  Drug getDrugByID(Long id);
  void addFood(Food food);
  void updateFood(Food food);
  void updateDrug(Drug drug);
  void deleteDrug(Long id);
  void deleteFood(Long id);
  void addDrug(Drug drug);

  List<CommunityController.DList> dlist(Long userId);
  List<CommunityController.UList> ulist(Long userId);

  void binding(Long userId, Long doctorId, Integer type);

  Long getDoctorId(Long userId);

  DoctorVerify getVerify(Long userId);
  DoctorVerify getVerifyByKey(Long doctorId);
  void addDoctorVerify(DoctorVerify doctorVerify);

  List<CommunityController.DoctorInfoB> dibList();

  void doctorChangeState(Long userId);

  void doctorChangeState1(Long userId);

  void doctorChangeState2(Long userId);

  void deleteDV(Long userId);
}
