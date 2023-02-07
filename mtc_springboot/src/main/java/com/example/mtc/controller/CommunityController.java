package com.example.mtc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mtc.model.Drug;
import com.example.mtc.model.Food;
import com.example.mtc.service.CommunityService;
import com.example.mtc.util.JsonResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/mtc/community")
@SuppressWarnings("ALL")
@Slf4j
public class CommunityController {
  @Autowired
  private CommunityService communityService;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class DrugInner {
    private Long drugId;
    private String drugName;
    private String drugImage;
    private String drugDetail;
    private List<String> sendType;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class DrugInfo {
    public Long drugid;
    public String drugname;
    public String drugpic;
    public String drugdetail;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class FoodInfo {
    public Long foodid;
    public String foodname;
    public String foodpic;
    public Float foodsugar;
    public Float foodprotein;
    public Float foodfat;
    public Float foodenergy;
    public String foodintroduction;
  }

  @PostMapping("/getFood")
  public JsonResult<List<Food>> food(@RequestBody HashMap<String, Object> body) {
    Boolean op = (Boolean) body.get("option");
    String name = (String) body.get("name");
    Long l = Long.valueOf((Integer) body.get("left"));
    Long r = Long.valueOf((Integer) body.get("right"));
    if (op == null) {
      op = Boolean.FALSE;
    }
    if (name != null) {
      List<Food> foods = communityService.getFoodByName(name, op);
      foods.forEach(item -> {
        item.setFoodName(item.getFoodName().split("_")[0]);
      });
      return JsonResult.success(foods);
    } else {
      if (l == null) l = 1L;
      if (r == null) r = l + 8L;
      List<Food> foods = communityService.getFoodByRange(l, r, op);
      foods.forEach(item -> {
        item.setFoodName(item.getFoodName().split("_")[0]);
      });
      return JsonResult.success(foods);
    }
  }

  @PostMapping("/getDrug")
  public JsonResult<List<DrugInner>> drug(
          @RequestBody HashMap<String, Object> body) {
    Boolean op = (Boolean) body.get("option");
    String name = (String) body.get("name");
    Long l = Long.valueOf((Integer) body.get("left"));
    Long r = Long.valueOf((Integer) body.get("right"));
    if (op == null) {
      op = Boolean.FALSE;
    }

    List<DrugInner> drugInnerList = new ArrayList<>();
    List<Drug> drugList = null;
    List<String> types = new ArrayList<>();

    if (name != null) {
      drugList = communityService.getDrugByName(name, op);
      for (Drug drug : drugList) {
        drugInnerList.add(new DrugInner(drug.getDrugId(), drug.getDrugName(),
                drug.getDrugImage(), drug.getDrugDetail(),
                types));
      }
      return JsonResult.success(drugInnerList);
    } else {
      if (l == null) l = 1L;
      if (r == null) r = l + 8L;

      drugList = communityService.getDrugByRange(l, r, op);

      for (Drug drug : drugList) {
        drugInnerList.add(new DrugInner(drug.getDrugId(), drug.getDrugName(),
                drug.getDrugImage(), drug.getDrugDetail(),
                types));
      }
      return JsonResult.success(drugInnerList);
    }
  }

  @GetMapping("/drug/information")
  public JsonResult<DrugInfo> drugInfo(@RequestParam("drugid") Long id) {
    Drug drug = communityService.getDrugByID(id);
    DrugInfo drugInfo = new DrugInfo(drug.getDrugId(), drug.getDrugName(),
            drug.getDrugImage(), drug.getDrugDetail());
    return JsonResult.success(drugInfo);
  }

  @GetMapping("/drug/edit")
  public JsonResult<Boolean> drugEdit(@RequestParam("drugid") Long id,
                                      @RequestParam("drugname") String name,
                                      @RequestParam("drugpic") String pic,
                                      @RequestParam("drugdetail") String detail) {
    Drug drug = new Drug();
    drug.setDrugId(id);
    drug.setDrugName(name);
    drug.setDrugDetail(detail);
    drug.setDrugImage(pic);
    communityService.updateDrug(drug);
    return JsonResult.success();
  }

  @GetMapping("/drug/delete")
  public JsonResult<Boolean> drugDel(@RequestParam("drugid") Long id) {
    communityService.deleteDrug(id);
    return JsonResult.success();
  }

  @GetMapping("/drug/add")
  public JsonResult<Boolean> drugAdd(@RequestParam("drugname") String name,
                                     @RequestParam("drugpic") String pic,
                                     @RequestParam("drugdetail") String detail) {
    Drug drug = new Drug();
    drug.setDrugName(name);
    drug.setDrugDetail(detail);
    drug.setDrugImage(pic);
    communityService.addDrug(drug);
    return JsonResult.success();
  }

  @GetMapping("/drug/getlist")
  public JsonResult<List<DrugInfo>> drugList() {
    List<Drug> drugList =
            communityService.getDrugByRange(0L, 0x7fffffffL, true);
    List<DrugInfo> drugInfoList = new ArrayList<>();
    for (Drug drug : drugList) {
      drugInfoList.add(new DrugInfo(drug.getDrugId(), drug.getDrugName(),
              drug.getDrugImage(), drug.getDrugDetail()));
    }
    return JsonResult.success(drugInfoList);
  }

  @GetMapping("/food/information")
  public JsonResult<FoodInfo> foodinfo(@RequestParam("foodid") Long id) {
    Food food = communityService.getFoodById(id);
    FoodInfo foodInfo = new FoodInfo(food.getFoodId(), food.getFoodName(),
            food.getFoodImage(), food.getFoodSuger(), food.getFoodProtein(),
            food.getFoodFat(), food.getFoodEnergy(), food.getFoodIntroduction());
    return JsonResult.success(foodInfo);
  }

  @GetMapping("/food/edit")
  public JsonResult<Boolean> foodEdit(@RequestParam("foodid") Long id,
                                      @RequestParam("foodname") String name,
                                      @RequestParam("foodpic") String pic,
                                      @RequestParam("foodsugar") Float sugar,
                                      @RequestParam("foodprotein") Float protein,
                                      @RequestParam("foodfat") Float fat,
                                      @RequestParam("foodenergy") Float energy,
                                      @RequestParam("foodintroduction")
                                        String introduction) {
    Food food = new Food();
    food.setFoodId(id);
    food.setFoodName(name);
    food.setFoodSuger(sugar);
    food.setFoodEnergy(energy);
    food.setFoodProtein(protein);
    food.setFoodImage(pic);
    food.setFoodFat(fat);
    food.setFoodIntroduction(introduction);
    communityService.updateFood(food);
    return JsonResult.success();
  }

  @GetMapping("/food/delete")
  public JsonResult<Boolean> foodDelete(@RequestParam("foodid") Long id) {
    communityService.deleteFood(id);
    return JsonResult.success();
  }

  @GetMapping("/food/add")
  public JsonResult<Boolean> foodAdd(@RequestParam("foodname") String name,
                                     @RequestParam("foodpic") String pic,
                                     @RequestParam("foodsugar") Float sugar,
                                     @RequestParam("foodprotein") Float protein,
                                     @RequestParam("foodfat") Float fat,
                                     @RequestParam("foodenergy") Float energy,
                                     @RequestParam("foodintroduction")
                                       String introduction) {
    Food food = new Food();
    food.setFoodName(name);
    food.setFoodSuger(sugar);
    food.setFoodEnergy(energy);
    food.setFoodProtein(protein);
    food.setFoodImage(pic);
    food.setFoodFat(fat);
    food.setFoodIntroduction(introduction);
    communityService.addFood(food);
    return JsonResult.success();
  }

  @GetMapping("/food/getlist")
  public JsonResult<List<FoodInfo>> foodInfoJsonResult() {
    List<Food> foodList =
            communityService.getFoodByRange(1L, 0x7fffffffL, true);
    List<FoodInfo> foodInfoList = new ArrayList<>();
    for (Food food : foodList) {
      foodInfoList.add(new FoodInfo(food.getFoodId(), food.getFoodName(),
              food.getFoodImage(), food.getFoodSuger(), food.getFoodProtein(),
              food.getFoodFat(), food.getFoodEnergy(), food.getFoodIntroduction()));
    }
    return JsonResult.success(foodInfoList);
  }

  @GetMapping("/food/recognize")
  public JsonResult<JSONObject>
  recognizeFood(@RequestParam("path") String path) {
    JSONObject jsonObject =
            JSONObject.parseObject(communityService.foodRecognize(path));
    int result_num = jsonObject.getIntValue("result_num");
    if (result_num > 0 && result_num <= 5)
      return JsonResult.success("success", jsonObject);
    return JsonResult.failure("failure");
  }

  @PostMapping("/food/recognize")
  public JsonResult<JSONObject> recognizeFood1(@RequestBody byte[] b)
          throws IOException {
    JSONObject jsonObject =
            JSONObject.parseObject(communityService.foodRecognize(b));
    int result_num = jsonObject.getIntValue("result_num");
    if (result_num > 0 && result_num <= 5)
      return JsonResult.success("success", jsonObject);
    return JsonResult.failure("failure");
  }

  @PostMapping("/food/uploadByImage")
  public JsonResult<Long> rec(@RequestBody List<MultipartFile> files)
          throws IOException {
    for (MultipartFile file : files) {
      if (file.isEmpty()) {
        continue;
      }
      JSONObject jsonObject = JSONObject.parseObject(communityService.foodRecognize(file.getBytes()));
      int result_num = jsonObject.getIntValue("result_num");
      log.info(String.valueOf(result_num));
      log.info(file.getOriginalFilename());
      if (result_num > 0) {
        JSONArray result = jsonObject.getJSONArray("result");
        log.info("food_name: " + result.getJSONObject(0).get("name"));
        for (int i = 0; i < result_num; i++) {
          List<Food> o = communityService.getFoodByName((String) result.getJSONObject(i).get("name"),
                  false);
          if (o.size() > 0) {
            return JsonResult.success(o.get(0).getFoodId());
          }
        }
      }
    }
    return JsonResult.failure();
  }

  @GetMapping("/food/uploadByLink")
  public JsonResult<Long> rec1(@RequestParam String link) {
    JSONObject jsonObject = JSONObject.parseObject(communityService.foodRecognizeByURL(link));
    System.out.println(jsonObject);
    int result_num = jsonObject.getIntValue("result_num");
    log.info(String.valueOf(result_num));
    if (result_num > 0) {
      JSONArray result = jsonObject.getJSONArray("result");
      log.info("food_name: " + result.getJSONObject(0).get("name"));
      for (int i = 0; i < result_num; i++) {
        List<Food> o = communityService.getFoodByName((String) result.getJSONObject(i).get("name"),
                false);
        if (o.size() > 0) {
          return JsonResult.success(o.get(0).getFoodId());
        }
      }
    }

    return JsonResult.failure();
  }



}
