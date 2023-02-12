package com.example.mtc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.mtc.controller.CommunityController;
import com.example.mtc.mapper.*;
import com.example.mtc.model.*;
import com.example.mtc.service.CommunityService;
import com.example.mtc.util.Base64Util;
import com.example.mtc.util.FileUtil;
import com.example.mtc.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@Slf4j
@Service
@SuppressWarnings("ALL")
public class CommunityServiceImpl implements CommunityService {
  public static Lock access_lock = new ReentrantLock();
  public static String accessToken = "[24.e7f5b0f6bac6a7f05e07ad74ef7e4171.2592000.1678465183.282335-28446618]";
  private static final String url = "https://aip.baidubce.com/rest/2.0/image-classify/v2/dish";
  private static String authHost = "https://aip.baidubce.com/oauth/2.0/token?";

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Autowired
  private DoctorVerifyMapper doctorVerifyMapper;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private RelationMapper relationMapper;

  @Autowired
  private DrugMapper drugMapper;
  @Autowired
  private FoodMapper foodMapper;


  public static String parseUserMessageListRedisListKey(Long uid1, Long uid2) throws Exception {
    if (uid1 == uid2) {
      throw new Exception();
    }

    if (uid1 > uid2) {
      Long tmp = uid1;
      uid1 = uid2;
      uid2 = tmp;
    }

    return "MESSAGE_LIST_" + uid1 + "_" + uid2;
  }


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
      accessToken = getAuth();
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
  public String foodRecognizeByURL(String u) {
    try {
      String para = "url=" + u + "&top_num=" + 5;

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

  @Override
  public Food getFoodById(Long id) {
    return foodMapper.selectByPrimaryKey(id);
  }

  @Override
  public Drug getDrugByID(Long id) {
    return drugMapper.selectByPrimaryKey(id);
  }

  @Override
  public void addFood(Food food) {
    foodMapper.insertSelective(food);
  }

  @Override
  public void updateFood(Food food) {
    foodMapper.updateByPrimaryKeyWithBLOBs(food);
  }

  @Override
  public void updateDrug(Drug drug) {
    drugMapper.updateByPrimaryKeySelective(drug);
  }

  @Override
  public void deleteDrug(Long id) {
    drugMapper.deleteByPrimaryKey(id);
  }

  @Override
  public void deleteFood(Long id) {
    foodMapper.deleteByPrimaryKey(id);
  }

  @Override
  public void addDrug(Drug drug) {
    drugMapper.insertSelective(drug);
  }

  @Override
  public List<CommunityController.DList> dlist(Long userId) {
    List<DoctorVerify> doctorVerifies = doctorVerifyMapper.selectAll();
    List<User> users = doctorVerifies.stream()
            .map(doctor -> {
              return userMapper.selectByPrimaryKey(doctor.getUserId());
            })
            .collect(Collectors.toList());

    List<CommunityController.DList> dlist = new ArrayList<>();
    for (int i = 0; i < doctorVerifies.size(); i++) {
      CommunityController.DList dList = new CommunityController.DList();
      dList.setUserId(users.get(i).getUserId());
      dList.setUserImage(users.get(i).getUserPortrait());
      dList.setUserName(users.get(i).getUserName());
      dList.setUserHospital(doctorVerifies.get(i).getDoctorHospital());
      dList.setUserCount(relationMapper.getUserCount(doctorVerifies.get(i).getDoctorId()));
      dList.setDoctorId(doctorVerifies.get(i).getDoctorId());
      dList.setUserBindingStatus(relationMapper.getBindingStatus(doctorVerifies.get(i).getDoctorId(), userId));
      if (dList.getUserBindingStatus() == null) {
        dList.setUserBindingStatus(2);
      }
      dlist.add(dList);
    }

    return dlist;
  }

  @Override
  public List<CommunityController.UList> ulist(Long userId) {
    DoctorVerify doctorVerify = doctorVerifyMapper.selectByUserId(userId);
    List<Long> userIds = relationMapper.getUsers(doctorVerify.getDoctorId());
    System.out.println(doctorVerify);
    System.out.println(userIds);

    List<CommunityController.UList> users = userIds.stream()
            .map(id -> {
              return userMapper.selectByPrimaryKey(id);
            })
            .map(user -> {
              CommunityController.UList uList = new CommunityController.UList();
              uList.setUserId(user.getUserId());
              uList.setUserImage(user.getUserPortrait());
              uList.setUserName(user.getUserName());
              uList.setUserSex(user.getUserSex());
              uList.setUserAge(new Date().getYear() - user.getUserBirthday().getYear());
              uList.setUserBindingStatus(relationMapper.getBindingStatus(doctorVerify.getDoctorId(), user.getUserId()));

              return uList;
            })
            .collect(Collectors.toList());


    return users;
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

  /**
   * @param userId
   * @param doctorId
   * @param type     0 申请；1 接触；2 同意
   */
  @Override
  public void binding(Long userId, Long doctorId, Integer type) {
    Relation relation = new Relation();
    relation.setDoctorId(doctorId);
    relation.setUserId(userId);

    switch (type) {
      case 0:
        relation.setRelationState(1);
        relationMapper.delete(userId, doctorId);
        relationMapper.insertSelective(relation);
        break;
      case 1:
        relationMapper.delete(userId, doctorId);
        break;
      default:
        relationMapper.update(userId, doctorId, 0);
        break;
    }
  }

  @Override
  public Long getDoctorId(Long userId) {
    return doctorVerifyMapper.selectByUserId(userId).getDoctorId();
  }

  @Override
  public DoctorVerify getVerify(Long userId) {
    return doctorVerifyMapper.selectByUserId(userId);
  }

  @Override
  public void addDoctorVerify(DoctorVerify doctorVerify) {
    DoctorVerify dv = doctorVerifyMapper.selectByUserId1(doctorVerify.getUserId());

    if (dv == null) {
      doctorVerifyMapper.insertSelective(doctorVerify);
    } else {
      doctorVerifyMapper.updateByPrimaryKeySelective(doctorVerify);
    }
  }

  @Override
  public List<CommunityController.DoctorInfoB> dibList() {
    return
            doctorVerifyMapper.selectAllToVerify()
                    .stream()
                    .map(doctorVerify -> {
                      User user = userMapper.selectByPrimaryKey(doctorVerify.getUserId());

                      CommunityController.DoctorInfoB infoB = new CommunityController.DoctorInfoB();

                      infoB.setUserId(user.getUserId());
                      infoB.setName(user.getUserName());
                      infoB.setEmail(user.getUserEmail());
                      infoB.setSex(user.getUserSex());
                      infoB.setAddress(doctorVerify.getDoctorHospital());
                      infoB.setUserBindingStatus(Integer.valueOf(user.getUserType(), 2));
                      infoB.setQualificationsUrl(doctorVerify.getDoctorQualification());
                      infoB.setIdNumber(doctorVerify.getDoctorIdnumber());

                      return infoB;
                    })
                    .collect(Collectors.toList());
  }

  @Override
  public void doctorChangeState(Long userId) {
    DoctorVerify doctorVerify = doctorVerifyMapper.selectByUserId1(userId);
    User user = userMapper.selectByPrimaryKey(userId);
    if (doctorVerify.getVerifyState().equals("00")) {
      doctorVerify.setVerifyState("01");
      user.setUserType("00");
    } else {
      doctorVerify.setVerifyState("00");
      user.setUserType("01");
    }

    doctorVerifyMapper.updateByPrimaryKeyWithBLOBs(doctorVerify);
    userMapper.updateByPrimaryKeySelective(user);
  }

  @Override
  public void deleteDV(Long userId) {
    DoctorVerify doctorVerify = doctorVerifyMapper.selectByUserId1(userId);
    if (doctorVerify != null) {
      doctorVerifyMapper.deleteByPrimaryKey(doctorVerify.getDoctorId());
      relationMapper.getAllByUserId(userId)
              .stream()
              .map(relation -> {
                return doctorVerifyMapper.selectByPrimaryKey(relation.getDoctorId()).getUserId();
              })
              .forEach(id -> {
                stringRedisTemplate.delete("MTC_USER_HASH_" + userId + "_" + id);
                stringRedisTemplate.delete("MTC_USER_HASH_" + id + "_" + userId);
                stringRedisTemplate.opsForSet().remove("MTC_USER_CONTENT_SET_" + userId, String.valueOf(id));
                stringRedisTemplate.opsForSet().remove("MTC_USER_CONTENT_SET_" + id, String.valueOf(userId));
              });
      relationMapper.deleteRev(doctorVerify.getDoctorId());
    }
  }

  @Override
  public void doctorChangeState1(Long userId) {
    DoctorVerify doctorVerify = doctorVerifyMapper.selectByUserId1(userId);
    User user = userMapper.selectByPrimaryKey(userId);

    doctorVerify.setVerifyState("00");
    user.setUserType("01");

    DoctorVerify doctorVerify1 = new DoctorVerify();
    doctorVerify1.setDoctorId(doctorVerify.getDoctorId());
    doctorVerify1.setVerifyState("00");
    doctorVerifyMapper.updateByPrimaryKeySelective(doctorVerify1);
    userMapper.updateByPrimaryKeySelective(user);
  }

  @Override
  public void doctorChangeState2(Long userId) {
    DoctorVerify doctorVerify = doctorVerifyMapper.selectByUserId1(userId);
    User user = userMapper.selectByPrimaryKey(userId);

    doctorVerify.setVerifyState("01");
    user.setUserType("00");

    doctorVerifyMapper.deleteByPrimaryKey(doctorVerify.getDoctorId());
    userMapper.updateByPrimaryKeySelective(user);
  }

  @Override
  public DoctorVerify getVerifyByKey(Long doctorId) {
    return doctorVerifyMapper.selectByPrimaryKey(doctorId);
  }
}
