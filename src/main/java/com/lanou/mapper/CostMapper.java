package com.lanou.mapper;

import com.lanou.bean.Cost;

import java.util.List;

public interface CostMapper {
    int deleteByPrimaryKey(Integer costId);

    int insert(Cost record);

    int insertSelective(Cost record);

    Cost selectByPrimaryKey(Integer costId);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);

    List<Cost> findAll();

    List<Cost> findAllByCostAsc();

    List<Cost> findAllByCostDesc();

    List<Cost> findAllByTimeAsc();

    List<Cost> findAllByTimeDesc();

    List<Cost> findAllCosts();

    Cost selectByCostType(String costType);
}