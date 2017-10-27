package com.lanou.mapper;

import com.lanou.bean.ModuleInfo;

import java.util.List;

public interface ModuleInfoMapper {
    int deleteByPrimaryKey(Integer moduleId);

    int insert(ModuleInfo record);

    int insertSelective(ModuleInfo record);

    ModuleInfo selectModule(Integer moduleId);

    List<ModuleInfo> findModuleInfosByRoleId(Integer roleId);

    int updateByPrimaryKeySelective(ModuleInfo record);

    int updateByPrimaryKey(ModuleInfo record);

    List<ModuleInfo> findAll();
}