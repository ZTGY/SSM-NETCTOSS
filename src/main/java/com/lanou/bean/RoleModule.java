package com.lanou.bean;

/**
 * @author dllo
 */
public class RoleModule {
    private Integer roleId;

    private Integer moduleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    @Override
    public String toString() {
        return "RoleModule{" +
                "roleId=" + roleId +
                ", moduleId=" + moduleId +
                '}';
    }
}