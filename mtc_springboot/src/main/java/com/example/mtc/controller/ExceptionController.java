package com.example.mtc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
@SuppressWarnings("ALL")
public class ExceptionController {
  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Map<String, Object> globalException(HttpServletRequest request, Exception e) {
    Map<String, Object> errorResult = new LinkedHashMap<>();
    errorResult.put("code", 201);
    errorResult.put("msg", e.getMessage());
    errorResult.put("object", null);
    e.printStackTrace();
    log.error(e.getMessage());
    return errorResult;
  }
}
