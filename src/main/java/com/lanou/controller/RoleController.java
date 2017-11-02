package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.ModuleInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.service.RoleInfoService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by dllo on 17/10/26.
 */
@Controller
public class RoleController {
    @Resource
    private RoleInfoService roleInfoService;

    //跳转到显示全部角色页面
    @RequestMapping(value = "/role_list")
    public String roleManager() {

        return "role/role_list";

    }

    //跳转到添加角色页面
    @RequestMapping(value = "/role_add")
    public String roleAdd() {

        return "role/role_add";

    }

    //添加一条角色
    @ResponseBody
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public AjaxResult addCost(@RequestParam("name") String name, @RequestParam("moduleId") String moduleId) {

        RoleInfo roleInfo = new RoleInfo();

        roleInfo.setName(name);

        //将角色名添加到role_info表
        int i = roleInfoService.insertSelective(roleInfo);

        String[] split = moduleId.split(",");

        for (String s : split) {

            //将roleId和module添加到中间表

            roleInfoService.insertIntoRoleAndModule(roleInfo.getRoleId(), Integer.valueOf(s));

        }

        if (i > 0) {

            return new AjaxResult(true);

        } else {

            return new AjaxResult(false);
        }

    }

    //查找到所有的权限
    @ResponseBody
    @RequestMapping(value = "/findAllModuleNames")
    public AjaxResult findAllModuleNames() {

        List<ModuleInfo> moduleInfoList = roleInfoService.findAllModuleNames();

        return new AjaxResult(moduleInfoList);
    }

    //查找到所有的角色
    @ResponseBody
    @RequestMapping(value = "/findAllRoleNames")
    public AjaxResult findAllRoleNames() {

        List<RoleInfo> roleInfoList = roleInfoService.findAllRoleNames();

        return new AjaxResult(roleInfoList);
    }

    //显示全部信息
    @ResponseBody
    @RequestMapping(value = "role_pageInfo")
    public PageInfo<RoleInfo> roleInfoPageInfo(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {

        return roleInfoService.findAllRolesWithPageInfo(pageNo, pageSize);

    }

    //查找当前所选的角色对象
    @ResponseBody
    @RequestMapping(value = "/role_modify")
    public AjaxResult feeModify(@RequestParam("roleId") Integer roleId, HttpServletRequest request) {

        RoleInfo roleInfo = roleInfoService.selectByPrimaryKey(roleId);

        request.getSession().setAttribute("roleInfo", roleInfo);

        return new AjaxResult(roleInfo);
    }

    //跳转到修改界面
    @RequestMapping(value = "/role_modify1")
    public String feeModify1() {

        return "role/role_modify";

    }

    //找到当前所选角色对象用来回显
    @ResponseBody
    @RequestMapping(value = "/role_modify2")
    public AjaxResult feeModify2(HttpServletRequest request) {

        RoleInfo roleInfo = (RoleInfo) request.getSession().getAttribute("roleInfo");

        return new AjaxResult(roleInfo);
    }

    //修改一条数据
    @RequestMapping(value = "/updateRole")
    public String update(@RequestParam("name") String name, @RequestParam("moduleId") String moduleId, HttpServletRequest request) {

        RoleInfo roleInfo1 = (RoleInfo) request.getSession().getAttribute("roleInfo");

        RoleInfo roleInfo = new RoleInfo();

        roleInfo.setRoleId(roleInfo1.getRoleId());

        roleInfo.setName(name);

        //将角色名添加到role_info表
        roleInfoService.updateByPrimaryKeySelective(roleInfo);

        roleInfoService.deleteRoleAndModuleByRoleId(roleInfo1.getRoleId());

        String[] split = moduleId.split(",");

        for (String s : split) {

            ModuleInfo moduleInfo = new ModuleInfo();

            moduleInfo.setModuleId(Integer.valueOf(s));

            //将roleId和module添加到中间表

            roleInfoService.insertIntoRoleAndModule(roleInfo.getRoleId(), Integer.valueOf(s));

        }

        return "role/role_list";
    }

    //删除一条资费
    @ResponseBody
    @RequestMapping(value = "/delRole")
    public AjaxResult deleteCost(@RequestParam("roleId") Integer roleId) {

        roleInfoService.deleteByPrimaryKey(roleId);

        roleInfoService.deleteRoleAndModuleByRoleId(roleId);

        PageInfo<RoleInfo> pageInfo = roleInfoService.findAllRolesWithPageInfo(1, 3);

        return new AjaxResult(pageInfo,0,roleId);
    }
}
