package com.example.mtc.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@SuppressWarnings("ALL")
public class Post implements Serializable {
  private Long postId;

  private Long userId;

  private String userName;

  private Date postTime;

  private String postTitle;

  private Long likeNumber;

  private Long favoriteNumber;

  private String postContent;

  private byte[] postImage;

  private static final long serialVersionUID = 1L;

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

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Date getPostTime() {
    return postTime;
  }

  public void setPostTime(Date postTime) {
    this.postTime = postTime;
  }

  public String getPostTitle() {
    return postTitle;
  }

  public void setPostTitle(String postTitle) {
    this.postTitle = postTitle;
  }

  public Long getLikeNumber() {
    return likeNumber;
  }

  public void setLikeNumber(Long likeNumber) {
    this.likeNumber = likeNumber;
  }

  public Long getFavoriteNumber() {
    return favoriteNumber;
  }

  public void setFavoriteNumber(Long favoriteNumber) {
    this.favoriteNumber = favoriteNumber;
  }

  public String getPostContent() {
    return postContent;
  }

  public void setPostContent(String postContent) {
    this.postContent = postContent;
  }

  public byte[] getPostImage() {
    return postImage;
  }

  public void setPostImage(byte[] postImage) {
    this.postImage = postImage;
  }

  @Override
  public String toString() {
    return getClass().getSimpleName() +
            " [" +
            "Hash = " + hashCode() +
            ", postId=" + postId +
            ", userId=" + userId +
            ", userName=" + userName +
            ", postTime=" + postTime +
            ", postTitle=" + postTitle +
            ", likeNumber=" + likeNumber +
            ", favoriteNumber=" + favoriteNumber +
            ", postContent=" + postContent +
            ", postImage=" + Arrays.toString(postImage) +
            ", serialVersionUID=" + serialVersionUID +
            "]";
  }
}