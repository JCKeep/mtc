package com.example.mtc.controller;

import com.example.mtc.model.User;
import com.example.mtc.service.UserService;
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
      UserInfo userInfo = new UserInfo(user1.getUserId(), email, user1.getUserName(), user.getUserPortrait());
      return JsonResult.success(userInfo);
    } else {
      return JsonResult.failure();
    }
  }

  @PostMapping("update")
  public JsonResult<Boolean> update(@RequestBody User user) {
    user.setUserId(userService.getUserByEmailWithNull(user.getUserEmail()).getUserId());
    userService.updateUser(user);
    return JsonResult.success();
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
  public JsonResult<User> getUser(@RequestParam("email") String email) {
    User user = userService.getUserByEmail(email);
    user.setUserPassword(null);
    return JsonResult.success(user);
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
  public JsonResult<Integer> upload(@RequestBody List<MultipartFile> files) {
    for (var now : files) {
      if (now.isEmpty()) {
        continue;
      }
      String fileName = now.getOriginalFilename();
      System.out.println(fileName);
      File dest = new File("/usr/local/nginx/static/file", fileName);
      if (dest.exists()) {
        return JsonResult.failure();
      }
      try {
        now.transferTo(dest);
      } catch (Exception e) {
        e.printStackTrace();
        return JsonResult.failure();
      }
    }
    return JsonResult.success();
  }
}
