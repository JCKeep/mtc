package com.example.mtc.controller;

import com.example.mtc.model.DietRecord;
import com.example.mtc.model.Food;
import com.example.mtc.model.HealthRecord;
import com.example.mtc.model.User;
import com.example.mtc.service.CommunityService;
import com.example.mtc.service.DietService;
import com.example.mtc.service.HealthDataService;
import com.example.mtc.service.UserService;
import com.example.mtc.util.DateUtil;
import com.example.mtc.util.JsonResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
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
    public String userSex;
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
    public Float breakfastCalo;
    public Float lunchCalo;
    public Float dinnerCalo;
    public Integer caloPercent;
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
  private static class Con {
    public FoofInfo food1;
    public FoofInfo food2;
    public FoofInfo food3;
    public FoofInfo food4;
  }

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  private static class DietInfoRequest {
    public Long userId;
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
    public String dietDate;
    public String dietTime;
    public String foodName;
    public Float foodWeight;
    public Float foodCalories;
    public Float foodSugar;
    public Float foodFat;
    public Float foodProtein;
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
            s, user.getUserSex(), user.getUserPortrait(), 18);
    return JsonResult.success(userInfo);
  }

  @GetMapping("/dietInf")
  public JsonResult<DietInfo> dietInformation(@RequestParam("userId") Long userId, @RequestParam("date") String s) {
    Date date = DateUtil.parse(s);
    log.info("--------------------" + userId);
    log.info("--------------------" + date);
    List<DietRecord> diets = dietService.getByUserIdAndDate(Long.valueOf(userId), date, date);
    DietInfo info = new DietInfo(0.0F, 0.0F, 0.0F,
            0.0F, 0.0F, 0.0F, 0.0F, 0);
    for (DietRecord diet : diets) {
      Food food = communityService.getFoodById(diet.getFoodId());

      if (food.getFoodFat() != null)
        info.todayFat += food.getFoodFat();
      if (food.getFoodProtein() != null)
        info.todayProtein += food.getFoodProtein();
      if (food.getFoodSuger() != null)
        info.todaySuger += food.getFoodSuger().floatValue();
      if (food.getFoodEnergy() != null) {
        info.todayCalories += food.getFoodEnergy();
        if (diet.getDietType().startsWith("早")) {
          info.breakfastCalo += food.getFoodEnergy();
        } else if (diet.getDietType().startsWith("中")) {
          info.lunchCalo += food.getFoodEnergy();
        } else if (diet.getDietType().startsWith("晚")) {
          info.dinnerCalo += food.getFoodEnergy();
        }
      }
    }
    info.breakfastCalo /= 4.184F;
    info.dinnerCalo /= 4.184F;
    info.lunchCalo /= 4.184F;
    info.todayCalories /= 4.184F;
    info.caloPercent = 70 + Math.abs(random.nextInt() % 31);
    return JsonResult.success(info);
  }

  @GetMapping("/addRecord")
  public JsonResult<Integer> add(@RequestParam("userId") Long userId, @RequestParam("dietTime") String dietTime,
                                 @RequestParam("dietDate") String s, @RequestParam("foodWeight") Long weight,
                                 @RequestParam("foodCalories") Float calories, @RequestParam("foodSugar") Float sugar,
                                 @RequestParam("foodFat") Float fat, @RequestParam("foodProtein") Float protein,
                                 @RequestParam("desc") String description, @RequestParam("foodName") String name) {
    DietRecord record = new DietRecord();
    if (calories == -1F) calories = null;
    if (sugar == -1F) sugar = null;
    if (fat == -1F) fat = null;
    if (protein == -1F) protein = null;
    if (weight == -1F) weight = null;
    Food food = new Food();
    Date date = DateUtil.parse(s);
    String tmp = name + "_" + UUID.randomUUID().toString();
    record.setDietDate(date);
    record.setUserId(userId);
    food.setFoodProtein(protein);
    food.setFoodEnergy(calories * 4.184F);
    food.setFoodIntroduction(description);
    food.setFoodSuger(sugar);
    food.setFoodFat(fat);
    food.setFoodName(tmp);
    food.setFoodWeight(Float.valueOf(weight));
    communityService.addFood(food);
    record.setDietType(dietTime);
    record.setFoodId(communityService.getFoodByName(tmp, false).get(0).getFoodId());
    dietService.insert(record);

    return JsonResult.success();
  }

  @GetMapping("/getRecord")
  public JsonResult<List<DietDetails_t>> get(@RequestParam("userId") Long userId, @RequestParam("date") String s) {
    Date date = DateUtil.parse(s);
    List<DietRecord> diets = dietService.getByUserIdAndDate(Long.valueOf(userId), date, date);
    List<DietDetails_t> diets_t = new ArrayList<>();
    for (DietRecord d : diets) {
      DietDetails_t dietDetails_t = new DietDetails_t(d.getDietId(), d.getDietType(), null, null);
      Food food = communityService.getFoodById(d.getFoodId());
      dietDetails_t.foodName = food.getFoodName().split("_")[0];
      dietDetails_t.foodCalories = food.getFoodEnergy() / 4.184F;
      diets_t.add(dietDetails_t);
    }
    return JsonResult.success(diets_t);
  }

  @GetMapping("/deleteRecord")
  public JsonResult<Integer> delete(@RequestParam("dietId") Long id) {
    dietService.delete(id);
    return JsonResult.success();
  }

  @GetMapping("/updateRecord")
  public JsonResult<Integer> update(@RequestParam("dietId") Long dietId, @RequestParam("dietTime") String dietTime,
                                    @RequestParam("dietDate") String s, @RequestParam("foodWeight") Long weight,
                                    @RequestParam("foodCalories") Float calories, @RequestParam("foodSugar") Float sugar,
                                    @RequestParam("foodFat") Float fat, @RequestParam("foodProtein") Float protein,
                                    @RequestParam("desc") String description, @RequestParam("foodName") String name) {
    DietRecord diet = dietService.get(dietId);
    if (calories == -1F) calories = null;
    if (sugar == -1F) sugar = null;
    if (fat == -1F) fat = null;
    if (protein == -1F) protein = null;
    if (weight == -1F) weight = null;
    diet.setDietType(dietTime);
    diet.setDietDate(DateUtil.parse(s));
    Food food = communityService.getFoodById(diet.getFoodId());
    String tmp = name + "_" + UUID.randomUUID().toString();
    food.setFoodFat(fat);
    food.setFoodProtein(protein);
    food.setFoodIntroduction(description);
    food.setFoodEnergy(calories * 4.184F);
    food.setFoodSuger(sugar);
    food.setFoodWeight(Float.valueOf(weight));
    food.setFoodName(tmp);
    communityService.updateFood(food);
    dietService.update(diet);

    return JsonResult.success();
  }

  @GetMapping("/foodInfo")
  public JsonResult<FoodDetails> foodInfo(@RequestParam("foodId") Long fid) {
    Food food = communityService.getFoodById(fid);
    FoodDetails details =
            new FoodDetails(food.getFoodName().split("_")[0],
                    food.getFoodImage(), food.getFoodIntroduction(),
                    food.getFoodEnergy() / 1.484F, food.getFoodProtein(),
                    food.getFoodFat(), food.getFoodSuger());
    return JsonResult.success(details);
  }

  @GetMapping("/recommand")
  public JsonResult<List<FoofInfo>> foodInfo1(@RequestParam("userId") Long uid) {
    List<Food> foods = communityService.getFoodByRange(0L, 4L, true);
    List<FoofInfo> infos = new ArrayList<>();
    Con con = new Con();

    for (Food food : foods) {
      FoofInfo f = new FoofInfo(food.getFoodEnergy() / 4.184F, food.getFoodId(), food.getFoodImage(),
              food.getFoodName().split("_")[0]);
      infos.add(f);
    }

    return JsonResult.success(infos);
  }

  @GetMapping("/information")
  public JsonResult<Diet_t> infomation(@RequestParam("userId") Long uid, @RequestParam("dietId") Long did) {
    DietRecord record = dietService.get(did);
    Food food = communityService.getFoodById(record.getFoodId());
    Diet_t diet_t = new Diet_t(DateUtil.parse(record.getDietDate()), record.getDietType(),
            food.getFoodName().split("_")[0],
            food.getFoodWeight(), food.getFoodEnergy() / 4.184F,
            food.getFoodSuger(), food.getFoodFat(), food.getFoodProtein(), food.getFoodIntroduction());
    return JsonResult.success(diet_t);
  }
}
