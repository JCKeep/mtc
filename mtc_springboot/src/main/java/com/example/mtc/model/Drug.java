package com.example.mtc.model;

import java.io.Serializable;

@SuppressWarnings("ALL")
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
    return getClass().getSimpleName() +
            " [" +
            "Hash = " + hashCode() +
            ", drugId=" + drugId +
            ", drugName=" + drugName +
            ", drugImage=" + String.valueOf(drugImage) +
            ", drugDetail=" + drugDetail +
            ", serialVersionUID=" + serialVersionUID +
            "]";
  }
}