package com.example.mtc.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("ALL")
public class JsonResult<T> {
  private static final JsonResult SUCCESS;
  private static final JsonResult FAILURE;

  static {
    SUCCESS = new JsonResult();
    SUCCESS.code = 200;
    SUCCESS.msg = null;
    SUCCESS.object = null;
    ;

    FAILURE = new JsonResult();
    FAILURE.code = 200;
    FAILURE.msg = null;
    FAILURE.object = null;
  }

  public int code;
  public String msg;
  public T object;

  public static <T> JsonResult<T> success(String msg, T obj) {
    JsonResult<T> jsonResult = new JsonResult<>();
    jsonResult.code = 200;
    jsonResult.msg = msg;
    jsonResult.object = obj;
    return jsonResult;
  }

  public static <T> JsonResult<T> success(T obj) {
    JsonResult<T> jsonResult = new JsonResult<>();
    jsonResult.code = 200;
    jsonResult.msg = null;
    jsonResult.object = obj;
    return jsonResult;
  }

  public static <T> JsonResult<T> success() {
    return SUCCESS;
  }

  public static <T> JsonResult<T> failure(String msg, T obj) {
    JsonResult<T> jsonResult = new JsonResult<>();
    jsonResult.code = 201;
    jsonResult.msg = msg;
    jsonResult.object = obj;
    return jsonResult;
  }

  public static <T> JsonResult<T> failure(String msg) {
    JsonResult<T> jsonResult = new JsonResult<>();
    jsonResult.code = 201;
    jsonResult.msg = msg;
    jsonResult.object = null;
    return jsonResult;
  }

  public static <T> JsonResult<T> failure() {
    return FAILURE;
  }
}
