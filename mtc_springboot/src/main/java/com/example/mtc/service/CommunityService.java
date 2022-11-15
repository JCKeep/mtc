package com.example.mtc.service;

import com.example.mtc.model.Drug;
import com.example.mtc.model.Food;

import java.util.List;

@SuppressWarnings("ALL")
public interface CommunityService {
  List<Food> getFoodByRange(Long l, Long r, Boolean option);
  List<Food> getFoodByName(String name, Boolean option);
  String foodRecognize(String path);
  String foodRecognize(byte[] p);

  List<Drug> getDrugByRange(Long l, Long r, Boolean option);
  List<Drug> getDrugByName(String name, Boolean option);
}
