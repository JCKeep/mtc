package com.example.mtc.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mtc.model.Drug;
import com.example.mtc.model.Food;
import com.example.mtc.service.CommunityService;
import com.example.mtc.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/mtc/community")
@SuppressWarnings("ALL")
@Slf4j
public class CommunityController {
  @Autowired
  private CommunityService communityService;

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
      return JsonResult.success(communityService.getFoodByName(name, op));
    } else {
      if (l == null) l = 1L;
      if (r == null) r = l + 8L;
      return JsonResult.success(communityService.getFoodByRange(l, r, op));
    }
  }

  @PostMapping("/getDrug")
  public JsonResult<List<Drug>> drug(@RequestBody HashMap<String, Object> body) {
    Boolean op = (Boolean) body.get("option");
    String name = (String) body.get("name");
    Long l = Long.valueOf((Integer) body.get("left"));
    Long r = Long.valueOf((Integer) body.get("right"));
    if (op == null) {
      op = Boolean.FALSE;
    }
    if (name != null) {
      return JsonResult.success(communityService.getDrugByName(name, op));
    } else {
      if (l == null) l = 1L;
      if (r == null) r = l + 8L;
      return JsonResult.success(communityService.getDrugByRange(l, r, op));
    }
  }

  @GetMapping("/food/recognize")
  public JsonResult<JSONObject> recognizeFood(@RequestParam("path") String path) {
    JSONObject jsonObject = JSONObject.parseObject(communityService.foodRecognize(path));
    int result_num = jsonObject.getIntValue("result_num");
    if (result_num > 0 && result_num <= 5)
      return JsonResult.success("success", jsonObject);
    return JsonResult.failure("failure");
  }

  @PostMapping("/food/recognize")
  public JsonResult<JSONObject> recognizeFood1(@RequestBody byte[] b) throws IOException {
    JSONObject jsonObject = JSONObject.parseObject(communityService.foodRecognize(b));
    int result_num = jsonObject.getIntValue("result_num");
    if (result_num > 0 && result_num <= 5)
      return JsonResult.success("success", jsonObject);
    return JsonResult.failure("failure");
  }
}
