package com.example.mtc.model;

import java.io.Serializable;

public class Drug implements Serializable {
  private Long drugId;

  private String drugName;

  private byte[] drugImage;

  private String drugDetail;

  private static final long serialVersionUID = 1L;

  public Long getDrugId() {
    return drugId;
  }

  public void setDrugId(Long drugId) {
    this.drugId = drugId;
  }

  public String getDrugName() {
    return drugName;
  }

  public void setDrugName(String drugName) {
    this.drugName = drugName;
  }

  public byte[] getDrugImage() {
    return drugImage;
  }

  public void setDrugImage(byte[] drugImage) {
    this.drugImage = drugImage;
  }

  public String getDrugDetail() {
    return drugDetail;
  }

  public void setDrugDetail(String drugDetail) {
    this.drugDetail = drugDetail;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", drugId=").append(drugId);
    sb.append(", drugName=").append(drugName);
    sb.append(", drugImage=").append(drugImage);
    sb.append(", drugDetail=").append(drugDetail);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}