package com.example.mtc.controller;

import com.example.mtc.model.HealthMedication;
import com.example.mtc.service.CommunityService;
import com.example.mtc.service.HealthMedicationService;
import com.example.mtc.service.UserService;
import com.example.mtc.util.JsonResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/mtc")
@SuppressWarnings("ALL")
public class MedicationController {
  @Autowired
  private HealthMedicationService medicationService;
  @Autowired
  private UserService userService;
  @Autowired
  private CommunityService communityService;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class MedicationRecordPeroid {
    public String email;
    public Date start;
    public Date end;
    public String type;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class MedicationRecord {
    public String email;
    public String drugName;
    public Date date;
    public String type;
    public Boolean isTake;
  }

  @GetMapping("/medication")
  public JsonResult<List<HealthMedication>> get(@RequestBody MedicationRecordPeroid peroid) {
    return JsonResult.success(medicationService.get(userService.getUserByEmailWithNull(peroid.email).getUserId(),
            peroid.start, peroid.end, peroid.type));
  }

  @PostMapping("/medication")
  public JsonResult<Integer> add(@RequestBody MedicationRecord record) {
    HealthMedication medication = new HealthMedication();
    medication.setMedicationDate(record.date);
    medication.setMedicationType(record.type);
    medication.setIsTake(record.isTake);
    medication.setUserId(userService.getUserByEmailWithNull(record.email).getUserId());
    medication.setDrugId(communityService.getDrugByName(record.drugName, false).get(0).getDrugId());
    medicationService.add(medication);
    return JsonResult.success();
  }

  @PutMapping("/medication")
  public JsonResult<Integer> update(@RequestBody MedicationRecord record) {
    HealthMedication medication = new HealthMedication();
    medication.setMedicationDate(record.date);
    medication.setMedicationType(record.type);
    medication.setIsTake(record.isTake);
    medication.setUserId(userService.getUserByEmailWithNull(record.email).getUserId());
    medication.setDrugId(communityService.getDrugByName(record.drugName, false).get(0).getDrugId());
    medicationService.update(medication);
    return JsonResult.success();
  }

  @DeleteMapping("/medication")
  public JsonResult<Integer> delete(@RequestBody MedicationRecordPeroid peroid) {
    medicationService.delete(userService.getUserByEmailWithNull(peroid.email).getUserId(),
            peroid.start, peroid.end, peroid.type);
    return JsonResult.success();
  }
}
