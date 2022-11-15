package com.example.mtc.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("ALL")
public class Chat implements Serializable {
  private Long chatId;

  private Long relationId;

  private Date chatTime;

  private String chatFrom;

  private String chatContent;

  private static final long serialVersionUID = 1L;

  public Long getChatId() {
    return chatId;
  }

  public void setChatId(Long chatId) {
    this.chatId = chatId;
  }

  public Long getRelationId() {
    return relationId;
  }

  public void setRelationId(Long relationId) {
    this.relationId = relationId;
  }

  public Date getChatTime() {
    return chatTime;
  }

  public void setChatTime(Date chatTime) {
    this.chatTime = chatTime;
  }

  public String getChatFrom() {
    return chatFrom;
  }

  public void setChatFrom(String chatFrom) {
    this.chatFrom = chatFrom;
  }

  public String getChatContent() {
    return chatContent;
  }

  public void setChatContent(String chatContent) {
    this.chatContent = chatContent;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() +
            " [" +
            "Hash = " + hashCode() +
            ", chatId=" + chatId +
            ", relationId=" + relationId +
            ", chatTime=" + chatTime +
            ", chatFrom=" + chatFrom +
            ", chatContent=" + chatContent +
            ", serialVersionUID=" + serialVersionUID +
            "]";
  }
}