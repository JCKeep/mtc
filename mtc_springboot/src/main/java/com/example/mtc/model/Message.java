package com.example.mtc.model;

import java.io.Serializable;

@SuppressWarnings("ALL")
public class Message implements Serializable {
  private Long messageId;

  private Long userId;

  private String messageFrom;

  private String messageType;

  private String messageContent;

  private static final long serialVersionUID = 1L;

  public Long getMessageId() {
    return messageId;
  }

  public void setMessageId(Long messageId) {
    this.messageId = messageId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getMessageFrom() {
    return messageFrom;
  }

  public void setMessageFrom(String messageFrom) {
    this.messageFrom = messageFrom;
  }

  public String getMessageType() {
    return messageType;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }

  public String getMessageContent() {
    return messageContent;
  }

  public void setMessageContent(String messageContent) {
    this.messageContent = messageContent;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() +
            " [" +
            "Hash = " + hashCode() +
            ", messageId=" + messageId +
            ", userId=" + userId +
            ", messageFrom=" + messageFrom +
            ", messageType=" + messageType +
            ", messageContent=" + messageContent +
            ", serialVersionUID=" + serialVersionUID +
            "]";
  }
}