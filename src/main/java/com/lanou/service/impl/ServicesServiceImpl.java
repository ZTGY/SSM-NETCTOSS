package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Services;
import com.lanou.mapper.ServiceMapper;
import com.lanou.service.ServicesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/25.
 */
@Service
public class ServicesServiceImpl implements ServicesService {
    @Resource
    private ServiceMapper serviceMapper;

    @Override
    public int deleteByPrimaryKey(Integer serviceId) {
        return serviceMapper.deleteByPrimaryKey(serviceId);
    }

    @Override
    public int insert(Services record) {
        return serviceMapper.insert(record);
    }

    @Override
    public int insertSelective(Services record) {
        return serviceMapper.insertSelective(record);
    }

    @Override
    public Services selectByPrimaryKey(Integer serviceId) {
        return serviceMapper.selectByPrimaryKey(serviceId);
    }

    @Override
    public int updateByPrimaryKeySelective(Services record) {
        return serviceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Services record) {
        return serviceMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<Services> findAllServicesWithPageInfo(Integer pageNo, Integer pageSize) {

        pageNo = pageNo == null ? 1 : pageNo;

        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        List<Services> servicesList = serviceMapper.findAll();

        PageInfo<Services> pageInfo = new PageInfo<Services>(servicesList);

        return pageInfo;
    }

    @Override
    public PageInfo<Services> findAllServicesWithPageInfo1(Integer pageNo, Integer pageSize, String osUsername,String unixHost,String idcardNo,String status) {

        pageNo = pageNo == null ? 1 : pageNo;

        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        List<Services> servicesList = serviceMapper.findAllBySearch(osUsername ,unixHost,idcardNo,status);

        PageInfo<Services> pageInfo = new PageInfo<Services>(servicesList);

        return pageInfo;
    }
}
