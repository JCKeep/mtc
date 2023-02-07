package com.example.mtc.controller;

import com.example.mtc.model.HealthRecord;
import com.example.mtc.model.User;
import com.example.mtc.service.HealthDataService;
import com.example.mtc.service.UserService;
import com.example.mtc.util.DateUtil;
import com.example.mtc.util.JsonResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
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
  private static final String DIR = "/usr/local/nginx/static/file/";
  private static final String HTTP_DIR = "http://114.132.226.110/file/";
  private static final Random random = new Random();
  private static final Double MIDDLE_VALUE = 21.2;
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
    public String name;
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

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class HealthData {
    public Integer lowBlood;
    public Integer highBlood;
    public Float bloodSugar;
    public Float heartRate;
    public String bloodType;
    public Integer height;
    public Integer age;
    public Integer healthRate;
  }

  @GetMapping("/HealthCard")
  public JsonResult<HealthData> healthDate(@RequestParam("email") String email, @RequestParam("date") String s) {
    Date date = DateUtil.parse(s);
    User user = userService.getUserByEmail(email);
    HealthRecord record = healthDataService.getHealthData(user.getUserId(), date, date).get(0);
    HealthData data = new HealthData(record.getUserLowBloodpressure(), record.getUserHighBloodpressure(),
            record.getUserBloodsugar().floatValue(), record.getUserHeartrate().floatValue(),
            user.getUserBloodtype(),user.getUserHeight(), new Date().getYear() - user.getUserBirthday().getYear(),
            70 + Math.abs(random.nextInt() % 31));
    return JsonResult.success(data);
  }

  @GetMapping("/suggest")
  public JsonResult<HealthSuggestion> getHealthSuggestion(@RequestParam("email") String email) {
    return JsonResult.success();
  }

  @PostMapping("/getRecord")
  public JsonResult<List<HealthRecord>> getHealthData(@RequestBody HealthRecordPeriod tmp) {
    List<HealthRecord> records = healthDataService.getHealthData(
            userService.getUserByEmailWithNull(tmp.email).getUserId(),
            tmp.start, tmp.end);

    for (int i = 0; i < records.size(); i++) {
      System.out.println(DateUtil.parse(records.get(i).getRecordDate()));
      records.get(i).setRecordDate(
              DateUtil.parse(DateUtil.parse(records.get(i).getRecordDate())));
    }

    return JsonResult.success(records);
  }

  @GetMapping("rating")
  public JsonResult<Integer> getHealthRating(@RequestParam("email") String email) {
    Date start = new Date();
    User user = userService.getUserByEmail(email);
    List<HealthRecord> records = healthDataService.getHealthData(user.getUserId(),
            start, start);

    Double rate = 80.0;
    System.out.println(rate.intValue());
    if (records.size() > 0 && records.get(0).getUserWeight() != null && user.getUserHeight() != null) {
      Double h = Double.valueOf(user.getUserHeight()) / 100.0;
      Double w = Double.valueOf(records.get(0).getUserWeight());
      rate = w / (h * h);
      if (rate > MIDDLE_VALUE) {
        rate -= 2 * (rate - MIDDLE_VALUE);
      }
      rate = rate / MIDDLE_VALUE * 100;
      if (rate < 0) {
        rate = 60.0;
      }
    }
    return JsonResult.success(rate.intValue());
  }

  @PostMapping("addRecord")
  public JsonResult<Integer> postHealthData(@RequestBody HealthRecordItem item) {
    Long userId = userService.getUserByEmailWithNull(item.email).getUserId();

    System.out.println(item.date);
    item.date = DateUtil.parse(DateUtil.parse(item.date));

    if (healthDataService.getHealthData(userId, item.date, item.date).size() > 0) {
      healthDataService.deleteHealthData(userId, item.date, item.date);
    }

    healthDataService.addHealthData(userId, item.date, item.bloodSugar, item.lowBloodpressure,
            item.highBloodpressure, item.heartRate, item.weight, item.healthCondition);

    return JsonResult.success();
  }

  @PostMapping("deleteRecord")
  public JsonResult<Integer> deleteHealthData(@RequestBody HealthRecordPeriod healthRecordPeriod) {
    healthDataService.deleteHealthData(
            userService.getUserByEmailWithNull(healthRecordPeriod.email).getUserId(),
            healthRecordPeriod.start, healthRecordPeriod.end);
    return JsonResult.success();
  }

  @GetMapping("base")
  public JsonResult<BasicHealthData> getBasicHealthData(@RequestParam("email") String email) {
    User user = userService.getUserByEmail(email);
    return JsonResult.success(new BasicHealthData(user.getUserName(), null, user.getUserSex(),
            user.getUserBloodtype(), user.getUserBirthday(), user.getUserHeight()));
  }

  @PostMapping("base")
  public JsonResult<Integer> addBasichealthData(@RequestBody BasicHealthData basicHealthData) {
    User user = userService.getUserByEmailWithNull(basicHealthData.email);
    user.setUserName(basicHealthData.name);
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
    User user = userService.getUserByEmail(period.email);
    Long id = user.getUserId();
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
      Double rate = 80.0;
      System.out.println(rate.intValue());
      if (item.getUserWeight() != null && user.getUserHeight() != null) {
        Double h = Double.valueOf(user.getUserHeight()) / 100.0;
        Double w = Double.valueOf(item.getUserWeight());
        rate = w / (h * h);
        if (rate > MIDDLE_VALUE) {
          rate -= 2 * (rate - MIDDLE_VALUE);
        }
        rate = rate / MIDDLE_VALUE * 100;
        if (rate < 0) {
          rate = 60.0;
        }
      }
      stringBuilder.append(rate.intValue());
      stringBuilder.append('\n');
    }
    String filename = DIR + id + ".csv";
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
    return JsonResult.success(HTTP_DIR + id + ".csv");
  }

  @GetMapping("/predict")
  public JsonResult<List<Float>> predict(@RequestParam("userId") Long id) {
    Date nowt = new Date();
    Date prev = DateUtils.addDays(nowt, -7);
    System.out.println(nowt + "\n" + prev);

    List<HealthRecord> records = healthDataService.getHealthData(id, prev, nowt);
    List<Float> values = List.of(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
    System.out.println(records.size());

    Float sum = 0.0F;
    Integer count = 0;
    Float defaultv = null;
    for (int i = 0; i < 7; i++) {
      Date d = DateUtils.addDays(prev, i);
      HealthRecord record = null;
      for (HealthRecord record1 : records) {
        if (record1.getRecordDate().equals(d)) {
          record = record1;
          count += 1;
          if (count == 1) {
            defaultv = record.getUserBloodsugar().floatValue();
          }
          break;
        }
      }
      if (record != null) {
        sum += record.getUserBloodsugar().floatValue();
        values.set(i, sum / count);
      } else if (defaultv != null) {
        values.set(i, defaultv);
      }
    }

    for (int i = 0; i < values.size(); i++) {
      if (values.get(i) == 0.0F && defaultv != null) {
        values.set(i, defaultv);
      }
    }

    return JsonResult.success(values);
  }
}
