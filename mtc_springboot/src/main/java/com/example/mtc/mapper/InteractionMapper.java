package com.example.mtc.mapper;

import com.example.mtc.model.Interaction;

public interface InteractionMapper {
    int deleteByPrimaryKey(Long interactionId);

    int insert(Interaction record);

    int insertSelective(Interaction record);

    Interaction selectByPrimaryKey(Long interactionId);

    int updateByPrimaryKeySelective(Interaction record);

    int updateByPrimaryKey(Interaction record);
}