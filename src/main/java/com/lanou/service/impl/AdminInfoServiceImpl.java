package com.lanou.service.impl;

import com.lanou.bean.AdminInfo;
import com.lanou.mapper.AdminInfoMapper;
import com.lanou.service.AdminInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dllo
 * @date 17/10/20
 */
@Service
public class AdminInfoServiceImpl implements AdminInfoService {
    @Resource
    private AdminInfoMapper adminInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer adminId) {
        return adminInfoMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public int insert(AdminInfo record) {
        return adminInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(AdminInfo record) {
        return adminInfoMapper.insertSelective(record);
    }

    @Override
    public AdminInfo selectByPrimaryKey(Integer adminId) {
        return adminInfoMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public int updateByPrimaryKeySelective(AdminInfo record) {
        return adminInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(AdminInfo record) {
        return adminInfoMapper.updateByPrimaryKey(record);
    }

    //通过用户名去数据库查找是否有相应的用户与之对应
    @Override
    public AdminInfo findByName(AdminInfo adminUser) {
        return adminInfoMapper.findByName(adminUser);
    }
}
