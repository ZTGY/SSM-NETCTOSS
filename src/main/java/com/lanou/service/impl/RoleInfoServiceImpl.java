package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.ModuleInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.mapper.ModuleInfoMapper;
import com.lanou.mapper.RoleInfoMapper;
import com.lanou.service.RoleInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Service
public class RoleInfoServiceImpl implements RoleInfoService {
    @Resource
    private RoleInfoMapper roleInfoMapper;
    @Resource
    private ModuleInfoMapper moduleInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer roleId) {
        return roleInfoMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    public int insert(RoleInfo record) {
        return roleInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(RoleInfo record) {
        return roleInfoMapper.insertSelective(record);
    }

    @Override
    public RoleInfo selectByPrimaryKey(Integer roleId) {
        return roleInfoMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public int updateByPrimaryKeySelective(RoleInfo record) {
        return roleInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(RoleInfo record) {
        return roleInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageInfo<RoleInfo> findAllRolesWithPageInfo(Integer pageNo, Integer pageSize) {
        pageNo = pageNo == null ? 1 : pageNo;

        pageSize = pageSize == null ? 5 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        List<RoleInfo> roleInfoList = roleInfoMapper.findAll();

        PageInfo<RoleInfo> pageInfo = new PageInfo<>(roleInfoList);

        return pageInfo;
    }

    @Override
    public List<ModuleInfo> findAllModuleNames() {

        return moduleInfoMapper.findAll();
    }

    @Override
    public ModuleInfo selectModule(Integer moduleId ) {
        return moduleInfoMapper.selectModule(moduleId);
    }

    @Override
    public Integer insertIntoRoleAndModule(Integer roleId, Integer moduleId) {
        return roleInfoMapper.insertIntoRoleAndModule(roleId,moduleId);
    }

    @Override
    public Integer deleteRoleAndModuleByRoleId(Integer roleId) {
        return roleInfoMapper.deleteRoleAndModuleByRoleId(roleId);
    }

    @Override
    public List<RoleInfo> findAllRoleNames() {
        return roleInfoMapper.findAll();
    }

}
