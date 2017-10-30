package com.lanou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.AdminInfo;
import com.lanou.mapper.AdminInfoMapper;
import com.lanou.service.AdminInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public PageInfo<AdminInfo> findAllAdminInfoWithPageInfo(Integer pageNo, Integer pageSize) {

        pageNo = pageNo == null ? 1 : pageNo;

        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        List<AdminInfo> adminInfos = adminInfoMapper.findAll();

        PageInfo<AdminInfo> pageInfo = new PageInfo<>(adminInfos);

        return pageInfo;

    }

    @Override
    public PageInfo<AdminInfo> findAllBySearch(Integer pageNo, Integer pageSize,Integer moduleId,String name) {
        pageNo = pageNo == null ? 1 : pageNo;

        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        List<AdminInfo> adminInfos = adminInfoMapper.findAllBySearch(moduleId,name);

        PageInfo<AdminInfo> pageInfo = new PageInfo<>(adminInfos);

        return pageInfo;
    }

    @Override
    public Integer insertIntoAdminAndRole(Integer adminId, Integer roleId) {

        return adminInfoMapper.insertIntoAdminAndRole(adminId,roleId);
    }

    @Override
    public Integer deleteAdminAndRoleByAdminId(Integer adminId) {

        return adminInfoMapper.deleteAdminAndRoleByAdminId(adminId);
    }
}
