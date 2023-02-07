package com.example.mtc.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("ALL")
public class Message implements Serializable {
  private Long messageId;

  private Long userId;

  private Long messageFrom;

  private Date messageDate;

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

  public Long getMessageFrom() {
    return messageFrom;
  }

  public void setMessageFrom(Long messageFrom) {
    this.messageFrom = messageFrom;
  }

  public Date getMessageType() {
    return messageDate;
  }

  public void setMessageType(Date messageType) {
    this.messageDate = messageType;
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
            ", messageType=" + messageDate +
            ", messageContent=" + messageContent +
            ", serialVersionUID=" + serialVersionUID +
            "]";
  }
}