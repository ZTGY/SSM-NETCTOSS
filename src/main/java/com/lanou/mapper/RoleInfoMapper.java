package com.lanou.mapper;

import com.lanou.bean.RoleInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer roleId);

    List<RoleInfo> findRoleInfosByModuleId(Integer moduleId);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);

    List<RoleInfo> findAll();

    Integer insertIntoRoleAndModule(@Param("roleId") Integer roleId,@Param("moduleId") Integer moduleId);

    Integer deleteRoleAndModuleByRoleId(Integer roleId);

    Integer deleteRoleAndModuleByModlueId(Integer moduleId);
}