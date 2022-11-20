package com.example.mtc.controller;

import com.example.mtc.model.User;
import com.example.mtc.service.UserService;
import com.example.mtc.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mtc/user")
@SuppressWarnings("ALL")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/login")
  @ResponseBody
  public ResponseEntity<Boolean> login(@RequestParam(name = "email") String email,
                                       @RequestParam(name = "passwd") String passwd) {
    User user = new User();
    user.setUserEmail(email);
    user.setUserPassword(passwd);
    return ResponseEntity.ok(userService.login(user));
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
    user.setUserId(null);
    user.setUserPassword(null);
    return JsonResult.success(user);
  }

  @PostMapping("/upload")
  public JsonResult<Integer> upload(@RequestBody List<MultipartFile> files, @RequestParam("email") String email) {
    User u = userService.getUserByEmailWithNull(email);
    for (var now : files) {
      if (now.isEmpty()) {
        continue;
      }
      String fileName = now.getOriginalFilename();
      System.out.println(fileName);
      File dest = new File("/usr/local/nginx/static/file", u.getUserId() + "-" + fileName);
      if (dest.exists()) {
        return JsonResult.failure();
      }
      try {
        now.transferTo(dest);
        u.setUserPortrait("http://114.132.226.110/file/" + u.getUserId() + "-" + fileName);
      } catch (Exception e) {
        e.printStackTrace();
        return JsonResult.failure();
      }
    }
    return JsonResult.success();
  }
}
