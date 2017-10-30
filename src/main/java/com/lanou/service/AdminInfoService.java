package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.AdminInfo;

/**
 *
 * @author dllo
 * @date 17/10/20
 */
public interface AdminInfoService {
    int deleteByPrimaryKey(Integer adminId);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);

    AdminInfo findByName(AdminInfo adminUser);

    PageInfo<AdminInfo> findAllAdminInfoWithPageInfo(Integer pageNo, Integer pageSize);

    PageInfo<AdminInfo> findAllBySearch(Integer pageNo, Integer pageSize,Integer moduleId,String name);

    Integer insertIntoAdminAndRole(Integer adminId, Integer roleId);

    Integer deleteAdminAndRoleByAdminId(Integer adminId);
}
