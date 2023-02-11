package com.example.mtc.model;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class HealthSuggest implements Serializable {
  public static final String[] suggest = {
          "饮食通过对饮食结构的调整达到辅助加强治疗效果，减轻糖尿病患者对药物的依赖性。不要吃含糖较高、高胆固醇以及含酒精等食物，少吃辛辣刺激性食物，可多吃五谷杂粮、豆类及豆制品。此外，苦瓜、桑叶、洋葱、香菇、柚子等可辅助降低血糖，是糖尿病人的理想食物。",
          "运动适当的运动可增强体质，提高胰岛素的敏感性，改善血脂代谢，调节体重，防止并发症的产生。运动后应有适意感且适当进食，避免出现低血糖。依照患者身体情况选择运动种类，如步行、慢跑、骑自行车、打太极拳、踢毽子、球类运动等有氧运动。坚持循序渐进，持之以恒的原则，运动应选择在餐后1h进行。",
          "用药糖尿病系终身性疾病，无论患者采用哪种降糖药物都会有相对不同的副作用和不良反应。患者应正确掌握用药的时间、用法及用量，如降糖药饭前、饭后服用的例外类型，用药要准确，凡在联合用药时更要小心谨慎，不要过量或重复使用。胰岛素在饭前半小时注射，注射后按时就餐。根据血糖情况，由医生来调整药物的剂量，避免患者乱用偏方或者盲目增减用药剂量。",
          "预防并发症 让患者及家属严格执行糖尿病患者的饮食及运动方案，了解糖尿病并发症的相关知识。定期去医院进行血糖、尿糖监测，全面了解用药水平和控制水平。也可采用便携式血糖仪进行血糖的自我检测，经常测血压、血脂，积极控制高血压和治疗高血脂，定期检查眼底、眼压、防止视网膜病变等视力危机损害。鞋袜要合脚，卫生、透气，防止周围神经和血管病变致足损伤。症状严重者，要及时送往医院治疗，以免耽误病情。",
          "心理由于糖尿病是慢性、终身性疾病，患者又缺乏医学知识，加之环境因素的影响，可能会产生孤独、焦虑、抑郁、悲观失望、恐惧心理、甚至产生厌世心理。患病早期阶段应进行及时的心理疏导，帮助患者接受患病事实，尽快转变角色，接受治疗，有助于早期控制疾病的发展，同时鼓励家属共同参与，使他们增强战胜疾病的信心。"
  };
  private Long suggestId;

  private String healthDataType;

  private String healthDataRange;

  private String suggestContent;

  private static final long serialVersionUID = 1L;

  public Long getSuggestId() {
    return suggestId;
  }

  public void setSuggestId(Long suggestId) {
    this.suggestId = suggestId;
  }

  public String getHealthDataType() {
    return healthDataType;
  }

  public void setHealthDataType(String healthDataType) {
    this.healthDataType = healthDataType;
  }

  public String getHealthDataRange() {
    return healthDataRange;
  }

  public void setHealthDataRange(String healthDataRange) {
    this.healthDataRange = healthDataRange;
  }

  public String getSuggestContent() {
    return suggestContent;
  }

  public void setSuggestContent(String suggestContent) {
    this.suggestContent = suggestContent;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() +
            " [" +
            "Hash = " + hashCode() +
            ", suggestId=" + suggestId +
            ", healthDataType=" + healthDataType +
            ", healthDataRange=" + healthDataRange +
            ", suggestContent=" + suggestContent +
            ", serialVersionUID=" + serialVersionUID +
            "]";
  }
}