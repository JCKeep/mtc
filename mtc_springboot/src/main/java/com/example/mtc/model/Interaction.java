package com.example.mtc.model;

import java.io.Serializable;

public class Interaction implements Serializable {
  private Long interactionId;

  private Long postId;

  private Long userId;

  private Boolean isLike;

  private Boolean isFavorite;

  private static final long serialVersionUID = 1L;

  public Long getInteractionId() {
    return interactionId;
  }

  public void setInteractionId(Long interactionId) {
    this.interactionId = interactionId;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Boolean getIsLike() {
    return isLike;
  }

  public void setIsLike(Boolean isLike) {
    this.isLike = isLike;
  }

  public Boolean getIsFavorite() {
    return isFavorite;
  }

  public void setIsFavorite(Boolean isFavorite) {
    this.isFavorite = isFavorite;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", interactionId=").append(interactionId);
    sb.append(", postId=").append(postId);
    sb.append(", userId=").append(userId);
    sb.append(", isLike=").append(isLike);
    sb.append(", isFavorite=").append(isFavorite);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}