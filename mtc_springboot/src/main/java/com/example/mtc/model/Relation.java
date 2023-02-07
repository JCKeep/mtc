package com.example.mtc.model;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class Relation implements Serializable {
  private Long relationId;

  private Long userId;

  private Long doctorId;

  private Integer relationState;

  private static final long serialVersionUID = 1L;

  public Long getRelationId() {
    return relationId;
  }

  public void setRelationId(Long relationId) {
    this.relationId = relationId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(Long doctorId) {
    this.doctorId = doctorId;
  }

  public Integer getRelationState() {
    return relationState;
  }

  public void setRelationState(Integer relationState) {
    this.relationState = relationState;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() +
            " [" +
            "Hash = " + hashCode() +
            ", relationId=" + relationId +
            ", userId=" + userId +
            ", doctorId=" + doctorId +
            ", relationState=" + relationState +
            ", serialVersionUID=" + serialVersionUID +
            "]";
  }
}