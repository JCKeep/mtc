package com.example.mtc.controller;

import com.example.mtc.util.JsonResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@SuppressWarnings("ALL")
public class TestController {
  @RequestMapping("/test")
  public JsonResult<Date> test(@RequestBody Date date) {
    return JsonResult.success(date);
  }
}
