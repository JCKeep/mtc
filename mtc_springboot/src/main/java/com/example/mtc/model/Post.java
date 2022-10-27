package com.example.mtc.model;

import java.io.Serializable;
import java.util.Date;

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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", postId=").append(postId);
        sb.append(", userId=").append(userId);
        sb.append(", userName=").append(userName);
        sb.append(", postTime=").append(postTime);
        sb.append(", postTitle=").append(postTitle);
        sb.append(", likeNumber=").append(likeNumber);
        sb.append(", favoriteNumber=").append(favoriteNumber);
        sb.append(", postContent=").append(postContent);
        sb.append(", postImage=").append(postImage);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}