package com.example.mtc.controller;

import com.example.mtc.model.DietRecord;
import com.example.mtc.model.Food;
import com.example.mtc.model.HealthRecord;
import com.example.mtc.model.User;
import com.example.mtc.service.CommunityService;
import com.example.mtc.service.DietService;
import com.example.mtc.service.HealthDataService;
import com.example.mtc.service.UserService;
import com.example.mtc.util.JsonResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("api/mtc/diet")
@SuppressWarnings("ALL")
public class DietController {
  private static final Random random = new Random();
  @Autowired
  private UserService userService;
  @Autowired
  private HealthDataService healthDataService;
  @Autowired
  private CommunityService communityService;
  @Autowired
  private DietService dietService;

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class UserInfo {
    public String userName;
    public Integer userHeight;
    public Integer userWeight;
    public Float userBoold;
    public String userPortrait;
    public Integer userAge;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class DietInfo {
    public Float todayCalories;
    public Float todaySuger;
    public Float todayProtein;
    public Float todayFat;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class FoofInfo {
    public Float foodCalories;
    public Long foodId;
    public String foodImage;
    public String foodName;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class DietInfoRequest {
    public Integer userId;
    public Date date;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class FoodDetails {
    public String foodName;
    public String foodImage;
    public String foodInfo;
    public Float foodCalories;
    public Float foodProtein;
    public Float foodFat;
    public Float foodSugar;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class DietRequest {
    public Long userId;
    public Long foodId;
    public Date dietDate;
    public String dietTime;
    public String foodName;
    public String desc;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class DietRequest_t {
    public Long dietId;
    public Date dietDate;
    public String dietTime;
    public String foodName;
    public String desc;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class DietDetails_t {
    public Long dietId;
    public String dietTime;
    public String foodName;
    public Float foodCalories;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class Diet_t {
    public Date dietDate;
    public String dietTime;
    public String foodName;
    public Float foodWeight;
    public Float foodCalories;
    public Float foodSugar;
    public String desc;
  }

  @GetMapping("/userInformation")
  public JsonResult<UserInfo> info(@RequestParam("userId") Integer id) {
    User user = userService.getUserById(id);
    Date date = new Date();
    List<HealthRecord> records = healthDataService.getHealthData(Long.valueOf(id), date, date);
    Integer w = null;
    Float s = null;
    if (records.size() > 0) {
      w = records.get(0).getUserWeight();
      s = records.get(0).getUserBloodsugar().floatValue();
    }
    UserInfo userInfo = new UserInfo(user.getUserName(), user.getUserHeight(), w,
            s, user.getUserPortrait(), 18);
    return JsonResult.success(userInfo);
  }

  @GetMapping("/dietInf")
  public JsonResult<DietInfo> dietInformation(@RequestBody DietInfoRequest request) {
    List<DietRecord> diets = dietService.getByUserIdAndDate(Long.valueOf(request.userId), request.date, request.date);
    DietInfo info = new DietInfo(0.0F, 0.0F, 0.0F, 0.0F);
    for (DietRecord diet : diets) {
      Food food = communityService.getFoodById(diet.getFoodId());
      if (food.getFoodFat() != null)
        info.todayFat += food.getFoodFat();
      if (food.getFoodProtein() != null)
        info.todayProtein += food.getFoodProtein();
      if (food.getFoodSuger() != null)
        info.todaySuger += food.getFoodSuger().floatValue();
      if (food.getFoodEnergy() != null)
        info.todayCalories += food.getFoodEnergy();
    }
    info.todayCalories /= 4.184F;
    return JsonResult.success(info);
  }

  @PostMapping("/record")
  public JsonResult<Integer> add(@RequestBody DietRequest dietRequest) {
    DietRecord record = new DietRecord();
    if (dietRequest.foodId == null || dietRequest.foodId == 0) {
      record.setDietDate(dietRequest.dietDate);
      record.setDietType(dietRequest.dietTime);
      record.setFoodId(communityService.getFoodByName(dietRequest.foodName, false).get(0).getFoodId());
      record.setUserId(dietRequest.userId);
    } else {
      record.setDietDate(dietRequest.dietDate);
      record.setDietType(dietRequest.dietTime);
      record.setUserId(dietRequest.userId);
      record.setFoodId(dietRequest.foodId);
    }
    dietService.insert(record);
    return JsonResult.success();
  }

  @GetMapping("/record")
  public JsonResult<List<DietDetails_t>> get(@RequestBody DietInfoRequest request) {
    List<DietRecord> diets = dietService.getByUserIdAndDate(Long.valueOf(request.userId), request.date, request.date);
    List<DietDetails_t> diets_t = new ArrayList<>();
    for (DietRecord d : diets) {
      DietDetails_t dietDetails_t = new DietDetails_t(d.getDietId(), d.getDietType(), null, null);
      Food food = communityService.getFoodById(d.getFoodId());
      dietDetails_t.foodName = food.getFoodName();
      dietDetails_t.foodCalories = food.getFoodEnergy() / 4.184F;
      diets_t.add(dietDetails_t);
    }
    return JsonResult.success(diets_t);
  }

  @DeleteMapping("/record")
  public JsonResult<Integer> delete(@RequestParam("dietId") Long id) {
    dietService.delete(id);
    return JsonResult.success();
  }

  @PutMapping("/record")
  public JsonResult<Integer> update(@RequestBody DietRequest_t dietRequest) {
    DietRecord record = new DietRecord();
    record.setDietId(dietRequest.dietId);
    record.setDietType(dietRequest.dietTime);
    record.setDietDate(dietRequest.dietDate);
    if (dietRequest.foodName != null) {
      record.setFoodId(communityService.getFoodByName(dietRequest.foodName, false).get(0).getFoodId());
    }
    dietService.update(record);
    return JsonResult.success();
  }

  @GetMapping("/foodInfo")
  public JsonResult<FoodDetails> foodInfo(@RequestParam("foodId") Long fid) {
    Food food = communityService.getFoodById(fid);
    FoodDetails details =
            new FoodDetails(food.getFoodName(), food.getFoodImage(), food.getFoodIntroduction(),
                    food.getFoodEnergy() / 1.484F, food.getFoodProtein(), food.getFoodFat(), food.getFoodSuger());
    return JsonResult.success(details);
  }

  @GetMapping("/recommand")
  public JsonResult<List<FoofInfo>> foodInfo1(@RequestParam("userId") Long uid) {
    List<Food> foods = communityService.getFoodByRange(0L, 4L, true);
    List<FoofInfo> infos = new ArrayList<>();

    for (Food food : foods) {
      FoofInfo f = new FoofInfo(food.getFoodEnergy() / 4.184F, food.getFoodId(), food.getFoodImage(),
              food.getFoodName());
      infos.add(f);
    }
    return JsonResult.success(infos);
  }

  @GetMapping("/information")
  public JsonResult<Diet_t> infomation(@RequestParam("userId") Long uid, @RequestParam("dietId") Long did) {
    DietRecord record = dietService.get(did);
    Food food = communityService.getFoodById(record.getFoodId());
    Diet_t diet_t = new Diet_t(record.getDietDate(), record.getDietType(), food.getFoodName(), null,
            food.getFoodEnergy() / 4.184F, food.getFoodSuger(), food.getFoodIntroduction());
    return JsonResult.success(diet_t);
  }
}
