package com.example.mtc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.mtc.mapper.DrugMapper;
import com.example.mtc.mapper.FoodMapper;
import com.example.mtc.model.Drug;
import com.example.mtc.model.Food;
import com.example.mtc.service.CommunityService;
import com.example.mtc.util.Base64Util;
import com.example.mtc.util.FileUtil;
import com.example.mtc.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@SuppressWarnings("ALL")
public class CommunityServiceImpl implements CommunityService {
  private static final String accessToken = "[24.fb529394fd2b4ea61c85948b1a21f5f5.2592000.1671116233.282335-28446618]";
  private static final String url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/dish";
  private static String authHost = "https://aip.baidubce.com/oauth/2.0/token?";

  @Autowired
  private DrugMapper drugMapper;
  @Autowired
  private FoodMapper foodMapper;

  @Override
  public List<Food> getFoodByRange(Long l, Long r, Boolean option) {
    if (option) {
      return foodMapper.selectInRange(l, r);
    } else {
      return foodMapper.selectInRangeWithNull(l, r);
    }
  }

  @Override
  public List<Food> getFoodByName(String name, Boolean option) {
    if (option) {
      return foodMapper.selectByName(name);
    } else {
      return foodMapper.selectByNameWithNull(name);
    }
  }

  @Override
  public String foodRecognize(String path) {
    try {
      byte[] imgData = FileUtil.readFileByBytes(path);
      String imgStr = Base64Util.encode(imgData);
      String imgPara = URLEncoder.encode(imgStr, StandardCharsets.UTF_8);

      String para = "image=" + imgPara + "&top_num=" + 5;

      String result = HttpUtil.post(url, accessToken, para);
      log.info("food recognize ok");
      return result;
    } catch (Exception e) {
      log.info("Reading photo error");
    }
    return null;
  }

  @Override
  public String foodRecognize(byte[] imgData) {
    try {
      String imgStr = Base64Util.encode(imgData);
      String imgPara = URLEncoder.encode(imgStr, StandardCharsets.UTF_8);

      String para = "image=" + imgPara + "&top_num=" + 5;

      String result = HttpUtil.post(url, accessToken, para);
      log.info("food recognize ok");
      return result;
    } catch (Exception e) {
      log.info("Reading photo error");
    }
    return null;
  }

  @Override
  public List<Drug> getDrugByRange(Long l, Long r, Boolean option) {
    if (option) {
      return drugMapper.selectInRange(l, r);
    } else {
      return drugMapper.selectInRangeWithNull(l, r);
    }
  }

  @Override
  public List<Drug> getDrugByName(String name, Boolean option) {
    if (option) {
      return drugMapper.selectByName(name);
    } else {
      return drugMapper.selectByNameWithNull(name);
    }
  }

  public static String getAuth() {
    String clientId = "5iRow8o85K4jG2cqNpFVd7Ck";
    String clientSecret = "AcThgkpbejXQA4VzazmGwXqDAE8GQev1";
    return getAuth(clientId, clientSecret);
  }

  public static String getAuth(String ak, String sk) {
    String getAccessTokenUrl = authHost
            + "grant_type=client_credentials"
            + "&client_id=" + ak
            + "&client_secret=" + sk;
    try {
      URL realUrl = new URL(getAccessTokenUrl);
      HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
      connection.setRequestMethod("GET");
      connection.connect();
      Map<String, List<String>> map = connection.getHeaderFields();
      for (String key : map.keySet()) {
        System.err.println(key + "--->" + map.get(key));
      }
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String result = "";
      String line;
      while ((line = in.readLine()) != null) {
        result += line;
      }

      JSONObject jsonObject = JSONObject.parseObject(result);
      String access_token = jsonObject.getString("access_token");
      return access_token;
    } catch (Exception e) {
      System.err.printf("获取token失败！");
      e.printStackTrace(System.err);
    }
    return null;
  }
}
