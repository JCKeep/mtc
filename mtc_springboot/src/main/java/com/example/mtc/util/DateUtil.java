package com.example.mtc.util;

import java.util.Date;

@SuppressWarnings("ALL")
public final class DateUtil {
  public final static Date parse(String s) {
    if (s == null) {
      return null;
    }
    String[] sp = s.split("-");
    return new Date(Integer.valueOf(sp[0]) - 1900, Integer.valueOf(sp[1]) - 1, Integer.valueOf(sp[2]));
  }

  public final static String parse(Date date) {
    if (date == null) {
      return null;
    }
    return (date.getYear() + 1900) + "-" + (date.getMonth() + 1) +"-" + date.getDate();
  }

  public final static Date parse(int year, int month, int day) {
    return new Date(year - 1900, month - 1, day);
  }
}
