package com.lanou.mapper;

import com.lanou.bean.Services;

import java.util.List;

public interface ServiceMapper {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(Services record);

    int insertSelective(Services record);

    Services selectByPrimaryKey(Integer serviceId);

    int updateByPrimaryKeySelective(Services record);

    int updateByPrimaryKey(Services record);

    List<Services> findAll();
}