package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;

import java.util.List;

/**
 * @author dllo
 * @date 17/10/20
 */
public interface CostService {

    int deleteByPrimaryKey(Integer costId);

    int insert(Cost record);

    int insertSelective(Cost record);

    Cost selectByPrimaryKey(Integer costId);

    int updateByPrimaryKeySelective(Cost record);

    int updateByPrimaryKey(Cost record);

    PageInfo<Cost> findWithPageInfo(Integer pageNo,Integer pageSize,Integer flag);

    List<Cost> findAllCosts();

    Cost selectByCostType(String costType);
}
