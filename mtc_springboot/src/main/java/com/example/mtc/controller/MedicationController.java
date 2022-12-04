package com.example.mtc.controller;

import com.example.mtc.model.Drug;
import com.example.mtc.model.HealthMedication;
import com.example.mtc.model.User;
import com.example.mtc.service.CommunityService;
import com.example.mtc.service.HealthMedicationService;
import com.example.mtc.service.UserService;
import com.example.mtc.util.DateUtil;
import com.example.mtc.util.JsonResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/mtc")
@SuppressWarnings("ALL")
public class MedicationController {
  private static final String STATE_HASH = "MEDICATION_STATE";
  @Autowired
  private HealthMedicationService medicationService;
  @Autowired
  private UserService userService;
  @Autowired
  private CommunityService communityService;
  @Autowired
  private StringRedisTemplate stringRedisTemplate;

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

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class MedicationRecord1 {
    public String type;
    public String email;
    public Long drugId;
    public Integer option;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class MedicationRecord2 {
    public Long medicationId;
    public Long userId;
    public Long drugId;
    public String drugName;
    public String medicationDate;
    public String medicationType;
    public Boolean isTake;
    public String medicationUsage;
  }

  @PostMapping("/medication/modify")
  public JsonResult<Integer> m1(@RequestBody MedicationRecord1 record) {
    if (record.drugId == null || record.email == null) {
      return JsonResult.failure("drugId or email is empty");
    }
    User user = userService.getUserByEmailWithNull(record.email);
    System.out.println(record.option);
    switch (record.option) {
      case -1: {
        medicationService.delete(user.getUserId(), record.drugId, null, null, record.type);
        break;
      }
      case  0: {
        try {
          HealthMedication medication1 =
                  medicationService.get(user.getUserId(), record.drugId, null, null, record.type).get(0);
          medication1.setIsTake(true);
          medicationService.update(medication1);
        } catch (Exception e) {
          e.printStackTrace();
          HealthMedication medication1 = new HealthMedication();
          medication1.setMedicationType(record.type);
          medication1.setDrugId(record.drugId);
          medication1.setUserId(user.getUserId());
          medication1.setIsTake(true);
          medicationService.add(medication1);
        }
        break;
      }
      case  1: {
        try {
          HealthMedication medication1 =
                  medicationService.get(user.getUserId(), record.drugId, null, null, record.type).get(0);
          System.out.println(medication1);
          medication1.setIsTake(false);
          medicationService.update(medication1);
        } catch (Exception e) {
          HealthMedication medication1 = new HealthMedication();
          medication1.setMedicationType(record.type);
          medication1.setDrugId(record.drugId);
          medication1.setUserId(user.getUserId());
          medication1.setIsTake(false);
          medicationService.add(medication1);
        }
        break;
      }
    }
    return JsonResult.success();
  }

  @PostMapping("/medication/get")
  public JsonResult<List<MedicationRecord2>> get1(@RequestBody MedicationRecord1 record) {
    List<HealthMedication> medication = medicationService.get(userService.getUserByEmailWithNull(record.email).getUserId(),
            null, null, null, record.type);
    List<MedicationRecord2> medicationRecord2s = new ArrayList<>();
    for (HealthMedication md : medication) {
      Drug drug = communityService.getDrugByID(md.getDrugId());
      MedicationRecord2 m2 = new MedicationRecord2(md.getMedicationId(),
              md.getUserId(), md.getDrugId(), drug.getDrugName(),
              DateUtil.parse( md.getMedicationDate()),
              md.getMedicationType(), md.getIsTake(), drug.getDrugDetail());
      medicationRecord2s.add(m2);
    }
    return JsonResult.success(medicationRecord2s);
  }

  @PostMapping("/getMedication")
  public JsonResult<List<HealthMedication>> get(@RequestBody MedicationRecordPeroid peroid) {
    return JsonResult.success(medicationService.get(userService.getUserByEmailWithNull(peroid.email).getUserId(),
           null, peroid.start, peroid.end, peroid.type));
  }

  @PostMapping("/addMedication")
  public JsonResult<Integer> add(@RequestBody MedicationRecord record) {
    HealthMedication medication = new HealthMedication();
    Long id = userService.getUserByEmailWithNull(record.email).getUserId();
    medication.setMedicationDate(record.date);
    medication.setMedicationType(record.type);
    medication.setIsTake(record.isTake);
    medication.setUserId(id);
    medication.setDrugId(communityService.getDrugByName(record.drugName, false).get(0).getDrugId());
    medicationService.add(medication);
    return JsonResult.success();
  }

  @PostMapping("/updateMedication")
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

  @PostMapping("/deleteMedication")
  public JsonResult<Integer> delete(@RequestBody MedicationRecordPeroid peroid) {
    medicationService.delete(userService.getUserByEmailWithNull(peroid.email).getUserId(),
            null, peroid.start, peroid.end, peroid.type);
    return JsonResult.success();
  }

  @GetMapping("/state")
  public JsonResult<Boolean> getState(@RequestParam("email") String email) {
    User user = userService.getUserByEmailWithNull(email);
    Boolean flag = Boolean.valueOf((String) stringRedisTemplate.opsForHash().get(STATE_HASH,
            String.valueOf(user.getUserId())));
    return JsonResult.success(flag);
  }

  @PostMapping("/state")
  public JsonResult<Boolean> setState(@RequestBody HashMap<String, Object> body) {
    User user = userService.getUserByEmailWithNull((String) body.get("email"));
    stringRedisTemplate.opsForHash().put(STATE_HASH, String.valueOf(user.getUserId()),
           String.valueOf((Boolean) body.get("state")));
    return JsonResult.success();
  }
}
