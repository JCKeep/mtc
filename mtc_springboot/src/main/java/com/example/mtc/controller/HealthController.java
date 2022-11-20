package com.example.mtc.controller;

import com.example.mtc.model.HealthRecord;
import com.example.mtc.model.User;
import com.example.mtc.service.HealthDataService;
import com.example.mtc.service.UserService;
import com.example.mtc.util.JsonResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/api/mtc/health")
@SuppressWarnings("ALL")
public class HealthController {
  private static final Random random = new Random();
  @Autowired
  private UserService userService;
  @Autowired
  private HealthDataService healthDataService;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class HealthRecordPeriod {
    public String email;
    public Date start;
    public Date end;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class BasicHealthData {
    public String email;
    public String sex;
    public String bloodType;
    public Date birthday;
    public Integer height;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class HealthSuggestion {
    public List<String> dataType;
    public List<String> avgDataValue;
    public List<String> suggestion;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class HealthRecordItem {
    public String email;
    public Date date;
    public BigDecimal bloodSugar;
    public Integer lowBloodpressure;
    public Integer highBloodpressure;
    public Integer heartRate;
    public Integer weight;
    public String healthCondition;
  }

  @GetMapping("/suggest")
  public JsonResult<HealthSuggestion> getHealthSuggestion(@RequestParam("email") String email) {
    return JsonResult.success();
  }

  @PostMapping("/getRecord")
  public JsonResult<List<HealthRecord>> getHealthData(@RequestBody HealthRecordPeriod tmp) {
    return JsonResult.success(healthDataService.getHealthData(userService.getUserByEmailWithNull(tmp.email).getUserId(),
            tmp.start, tmp.end));
  }

  @GetMapping("rating")
  public JsonResult<Integer> getHealthRating(@RequestParam("email") String email) {
    return JsonResult.success(70 + Math.abs(random.nextInt() % 31));
  }

  @PostMapping("addRecord")
  public JsonResult<Integer> postHealthData(@RequestBody HealthRecordItem item) {
    Long userId = userService.getUserByEmailWithNull(item.email).getUserId();

    healthDataService.addHealthData(userId, item.date, item.bloodSugar, item.lowBloodpressure,
            item.highBloodpressure, item.heartRate, item.weight, item.healthCondition);

    return JsonResult.success();
  }

  @PostMapping("deleteRecord")
  public JsonResult<Integer> deleteHealthData(@RequestBody HealthRecordPeriod healthRecordPeriod) {
    healthDataService.deleteHealthData(userService.getUserByEmailWithNull(healthRecordPeriod.email).getUserId(),
            healthRecordPeriod.start, healthRecordPeriod.end);
    return JsonResult.success();
  }

  @GetMapping("base")
  public JsonResult<BasicHealthData> getBasicHealthData(@RequestParam("email") String email) {
    User user = userService.getUserByEmail(email);
    return JsonResult.success(new BasicHealthData(null, user.getUserSex(),
            user.getUserBloodtype(), user.getUserBirthday(), user.getUserHeight()));
  }

  @PostMapping("base")
  public JsonResult<Integer> addBasichealthData(@RequestBody BasicHealthData basicHealthData) {
    User user = userService.getUserByEmailWithNull(basicHealthData.email);
    user.setUserSex(basicHealthData.sex);
    user.setUserHeight(basicHealthData.height);
    user.setUserBloodtype(basicHealthData.bloodType);
    user.setUserHeight(basicHealthData.height);
    user.setUserBirthday(basicHealthData.birthday);
    userService.updateUser(user);
    return JsonResult.success();
  }

  @PostMapping(value = "export")
  public JsonResult<String> exportHealthData(@RequestBody HealthRecordPeriod period) {
    Long id = userService.getUserByEmailWithNull(period.email).getUserId();
    List<HealthRecord> d = healthDataService.getHealthData(id,
            period.start, period.end);
    StringBuilder stringBuilder =
            new StringBuilder("Date,BloodSugar,LowPressure,HighPressure,HeartRate,Weight,HealthRate\n");
    for (HealthRecord item : d) {
      stringBuilder.append(item.getRecordDate().toString());
      stringBuilder.append(',');
      if (item.getUserBloodsugar() == null) {
        stringBuilder.append(" ,");
      } else {
        stringBuilder.append(item.getUserBloodsugar().floatValue());
        stringBuilder.append(',');
      }
      if (item.getUserLowBloodpressure() == null) {
        stringBuilder.append(" ,");
      } else {
        stringBuilder.append(item.getUserLowBloodpressure().toString());
        stringBuilder.append(',');
      }
      if (item.getUserHighBloodpressure() == null) {
        stringBuilder.append(" ,");
      } else {
        stringBuilder.append(item.getUserHighBloodpressure().toString());
        stringBuilder.append(',');
      }
      if (item.getUserHeartrate() == null) {
        stringBuilder.append(" ,");
      } else {
        stringBuilder.append(item.getUserHeartrate().toString());
        stringBuilder.append(',');
      }
      if (item.getUserWeight() == null) {
        stringBuilder.append(" ,");
      } else {
        stringBuilder.append(item.getUserWeight().toString());
        stringBuilder.append(',');
      }
      stringBuilder.append(70 + Math.abs(random.nextInt() % 31));
      stringBuilder.append('\n');
    }
    String dir1 = "/usr/local/nginx/static/file/";
    String dir = "C:\\Users\\jckeep\\Desktop\\";
    String filename = dir1 + id + ".csv";
    File file = new File(filename);
    try {
      file.createNewFile();
      FileOutputStream outputStream = new FileOutputStream(file, false);
      outputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
      outputStream.close();
      System.out.println(stringBuilder);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
    return JsonResult.success("http://114.132.226.110/file/" + id + ".csv");
  }
}
