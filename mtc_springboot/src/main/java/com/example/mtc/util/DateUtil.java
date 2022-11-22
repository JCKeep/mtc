package com.example.mtc.util;

import java.util.Date;

@SuppressWarnings("ALL")
public class DateUtil {
  public final static Date parse(String s) {
    String[] sp = s.split("-");
    return new Date(Integer.valueOf(sp[0]) - 1900, Integer.valueOf(sp[1]) - 1, Integer.valueOf(sp[2]));
  }

  public final static String parse(Date date) {
    return (date.getYear() + 1900) + "-" + (date.getMonth() + 1) +"-" + date.getDate();
  }
}
