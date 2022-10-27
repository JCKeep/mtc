package com.example.mtc.mapper;

import com.example.mtc.model.Post;

public interface PostMapper {
    int deleteByPrimaryKey(Long postId);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(Long postId);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKeyWithBLOBs(Post record);

    int updateByPrimaryKey(Post record);
}