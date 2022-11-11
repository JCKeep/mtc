package com.example.mtc.mapper;

import com.example.mtc.model.Relation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RelationMapper {
    int deleteByPrimaryKey(Long relationId);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(Long relationId);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);
}