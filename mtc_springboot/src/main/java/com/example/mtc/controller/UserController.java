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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/api/mtc/user")
@SuppressWarnings("ALL")
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private HealthDataService healthDataService;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class UserInfo {
    public Long userId;
    public String email;
    public String name;
    public String photo;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class UserInfoToUpdate {
    public String name;
    public String email;
    public String sex;
    public String bloodType;
    public Date birthday;
    public Long height;
    public String userEmail;
    public String code;
    public String userPasswordNew;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class UserPasswordChange {
    public String userEmail;
    public String userPasswordOld;
    public String userPasswordNew;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private class UserResponse {
    private Long userId;
    private String userName;
    private String userEmail;
    private String userSex;
    private String userBloodtype;
    private String userBirthday;
    private Integer userHeight;
    private String userType;
    private String userPortrait;
    private Integer userWeight;
    private Float userBlood;
    private Integer userAge;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  private static class UserKeywordRequest {
    public String email;
    public List<String> keywords;
  }

  @GetMapping("/login")
  @ResponseBody
  public ResponseEntity<Boolean> login(@RequestParam(name = "email") String email,
                                       @RequestParam(name = "passwd") String passwd) {
    User user = new User();
    user.setUserEmail(email);
    user.setUserPassword(passwd);
    return ResponseEntity.ok(userService.login(user));
  }

  @GetMapping("/loginWithUserId")
  public JsonResult<UserInfo> long2(@RequestParam(name = "email") String email,
                                    @RequestParam(name = "passwd") String passwd) {
    User user = new User();
    user.setUserEmail(email);
    user.setUserPassword(passwd);
    Boolean b = userService.login(user);
    if (b) {
      User user1 = userService.getUserByEmail(email);
      UserInfo userInfo = new UserInfo(user1.getUserId(), email,
              user1.getUserName(), user.getUserPortrait());
      return JsonResult.success(userInfo);
    } else {
      return JsonResult.failure();
    }
  }

  @PostMapping("update")
  public JsonResult<Boolean> update(@RequestBody UserInfoToUpdate info) {
    User user = null;
    if (info.email != null) {
      user = userService.getUserByEmail(info.email);
      user.setUserBirthday(info.birthday);
      user.setUserHeight(info.height.intValue());
      user.setUserSex(info.sex);
      user.setUserBloodtype(info.bloodType);
      userService.updateUser(user);
    } else if (info.userEmail != null) {
      user = userService.getUserByEmail(info.userEmail);
      user.setUserEmail(info.userEmail);
      user.setUserPassword(info.userPasswordNew);
      if (userService.changePasswd(user, info.code)) {
        return JsonResult.success();
      } else {
        return JsonResult.failure();
      }
    }
    return JsonResult.success();
  }

  @PostMapping("changePassword")
  public JsonResult<Boolean> changePassword(@RequestBody UserPasswordChange passwordChange) {
    User user = userService.getUserByEmail(passwordChange.userEmail);
    System.out.println(user.getUserPassword());
    System.out.println(passwordChange.userPasswordOld);
    if (user.getUserPassword().equals(passwordChange.userPasswordOld)) {
      System.out.println(user.getUserPassword().equals(passwordChange.userPasswordOld));
      user.setUserPassword(passwordChange.getUserPasswordNew());
      userService.updateUser(user);
      return JsonResult.success();
    } else {
      System.out.println(user.getUserPassword().equals(passwordChange.userPasswordOld));
      return JsonResult.failure();
    }
  }

  @PostMapping("/register")
  @ResponseBody
  public ResponseEntity<Boolean> register(@RequestBody Map<String, Object> json) {
    User user = new User();
    user.setUserName((String) json.get("name"));
    user.setUserEmail((String) json.get("email"));
    user.setUserPassword((String) json.get("passwd"));
    return ResponseEntity.ok(userService.register(user, (String) json.get("code")));
  }

  @GetMapping("/delete")
  public JsonResult<Boolean> delete(@RequestParam("email") String email) {
    userService.delete(userService.getUserByEmailWithNull(email).getUserId());
    return JsonResult.success();
  }

  @GetMapping("/getcode")
  @ResponseBody
  public ResponseEntity<String> getCode(@RequestParam(name = "email") String email) {
    User user = new User();
    user.setUserEmail(email);
    return ResponseEntity.ok(userService.verifyCode(user));
  }

  @GetMapping("/getuserinfo")
  public JsonResult<UserResponse> getUser(@RequestParam("email") String email) {
    User user = userService.getUserByEmail(email);
    Date date = new Date();
    List<HealthRecord> records = healthDataService.getHealthData(user.getUserId(), date, date);
    Integer a;
    Float c;

    if (records.size() == 0) {
      a = null;
      c = null;
    } else {
      a = records.get(0).getUserWeight();
      c = records.get(0).getUserBloodsugar().floatValue();
    }

    UserResponse response = new UserResponse(user.getUserId(), user.getUserName(),
            user.getUserEmail(), user.getUserSex(), user.getUserBloodtype(), DateUtil.parse(user.getUserBirthday()),
            user.getUserHeight(), user.getUserType(), user.getUserPortrait(), a, c,
            date.getYear() - user.getUserBirthday().getYear());
    return JsonResult.success(response);
  }

  @GetMapping("/keywords")
  public JsonResult<Set<String>> getKeywords(@RequestParam("email") String email) {
    User u = userService.getUserByEmail(email);
    Set<String> keywords = new HashSet<>();
    for (String word : u.getUserKeyword().split(",")) {
      keywords.add(word);
    }
    return JsonResult.success(keywords);
  }

  @PostMapping("/delKeywords")
  public JsonResult<Boolean> delKeywords(@RequestBody UserKeywordRequest request) {
    User user = userService.getUserByEmail(request.email);
    Set<String> keywords = new HashSet<>();
    for (String word : user.getUserKeyword().split(",")) {
      keywords.add(word);
    }
    for (String word : request.keywords) {
      keywords.remove(word);
    }
    StringBuilder word = new StringBuilder();
    int count = 0;
    for (String w : keywords) {
      if (count > 0) {
        word.append(",");
      }
      word.append(w);
      count += 1;
    }
    user.setUserKeyword(word.toString());
    userService.updateUser(user);
    return JsonResult.success();
  }

  @PostMapping("/keywords")
  public JsonResult<Integer> addKeywords(@RequestBody UserKeywordRequest request) {
    User user = userService.getUserByEmail(request.email);
    Set<String> keywords = new HashSet<>();
    for (String word : user.getUserKeyword().split(",")) {
      keywords.add(word);
    }
    for (String word : request.keywords) {
      keywords.add(word);
    }
    StringBuilder word = new StringBuilder();
    int count = 0;
    for (String w : keywords) {
      if (count > 0) {
        word.append(",");
      }
      word.append(w);
      count += 1;
    }
    user.setUserKeyword(word.toString());
    userService.updateUser(user);
    return JsonResult.success();
  }

  @PostMapping("/upload")
  public JsonResult<String> upload(@RequestBody List<MultipartFile> files) {
    String path = null;
    for (var now : files) {
      if (now.isEmpty()) {
        continue;
      }
      String fileName = now.getOriginalFilename();
      System.out.println(fileName);
      File dest = new File("/usr/local/nginx/static/file", fileName);
      path = "http://114.132.226.110/file/" + fileName;
      if (dest.exists()) {
        return JsonResult.success(path);
      }
      try {
        now.transferTo(dest);
      } catch (Exception e) {
        e.printStackTrace();
        return JsonResult.failure();
      }
    }
    return JsonResult.success(path);
  }

  @PostMapping("/setPortrait")
  public JsonResult<Boolean> setPortrait(@RequestBody HashMap<String, Object> body) {
    User user = userService.getUserByEmail((String) body.get("email"));
    user.setUserPortrait((String) body.get("address"));
    userService.updateUser(user);
    return JsonResult.success();
  }

  @GetMapping("id")
  public JsonResult<Long> getUserId(@RequestParam("email") String email) {
    return JsonResult.success(userService.getUserByEmailWithNull(email).getUserId());
  }

  @PostMapping("changestate")
  public JsonResult<?> changeUserState(Long userId) {
    userService.changeUserState(userId);
    return JsonResult.success();
  }

  @GetMapping("type")
  public JsonResult<?> getUserType(Long userId) {
    return JsonResult.success(userService.getUserType(userId));
  }
}
