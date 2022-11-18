package com.example.mtc.controller;

import com.example.mtc.model.User;
import com.example.mtc.service.UserService;
import com.example.mtc.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/mtc/user")
@SuppressWarnings("ALL")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/login")
  @ResponseBody
  public ResponseEntity<Boolean> login(@RequestParam(name = "email") String email, @RequestParam(name = "passwd") String passwd) {
    User user = new User();
    user.setUserEmail(email);
    user.setUserPassword(passwd);
    return ResponseEntity.ok(userService.login(user));
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

  @GetMapping("/getcode")
  @ResponseBody
  public ResponseEntity<String> getCode(@RequestParam(name = "email") String email) {
    User user = new User();
    user.setUserEmail(email);
    return ResponseEntity.ok(userService.verifyCode(user));
  }

  @GetMapping("getuserinfo")
  public JsonResult<User> getUser(@RequestParam("email") String email) {
    User user = userService.getUserByEmail(email);
    user.setUserId(null);
    user.setUserPassword(null);
    return JsonResult.success(user);
  }
}
