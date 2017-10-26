package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Services;

/**
 * Created by dllo on 17/10/25.
 */
public interface ServicesService {
    int deleteByPrimaryKey(Integer serviceId);

    int insert(Services record);

    int insertSelective(Services record);

    Services selectByPrimaryKey(Integer serviceId);

    int updateByPrimaryKeySelective(Services record);

    int updateByPrimaryKey(Services record);

    PageInfo<Services> findAllServicesWithPageInfo(Integer pageNo, Integer pageSize);
}
