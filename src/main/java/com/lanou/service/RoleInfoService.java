package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.ModuleInfo;
import com.lanou.bean.RoleInfo;

import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
public interface RoleInfoService {
    int deleteByPrimaryKey(Integer roleId);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);

    PageInfo<RoleInfo> findAllRolesWithPageInfo(Integer pageNo, Integer pageSize);

    List<ModuleInfo> findAllModuleNames();

    ModuleInfo selectModule(Integer moduleId);

    Integer insertIntoRoleAndModule(Integer roleId, Integer moduleId);

    Integer deleteRoleAndModuleByRoleId(Integer roleId);

    List<RoleInfo> findAllRoleNames();
}
