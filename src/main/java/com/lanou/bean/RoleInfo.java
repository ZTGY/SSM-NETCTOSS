package com.lanou.bean;

import java.util.List;

/**
 * @author dllo
 */
public class RoleInfo {
    private Integer roleId;//角色id

    private String name;//角色名称

    private List<ModuleInfo> moduleInfoList;

    public List<ModuleInfo> getModuleInfoList() {
        return moduleInfoList;
    }

    public void setModuleInfoList(List<ModuleInfo> moduleInfoList) {
        this.moduleInfoList = moduleInfoList;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    @Override
    public String toString() {
        return "RoleInfo{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                ", moduleInfoList=" + moduleInfoList +
                '}';
    }
}