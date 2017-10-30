package com.lanou.mapper;

import com.lanou.bean.AdminInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);

    AdminInfo findByName(AdminInfo adminUser);

    List<AdminInfo> findAll();

    List<AdminInfo> findAllBySearch(@Param("moduleId") Integer moduleId,@Param("name") String name);

    Integer insertIntoAdminAndRole(@Param("adminId") Integer adminId,@Param("roleId") Integer roleId);

    Integer deleteAdminAndRoleByAdminId(Integer adminId);
}