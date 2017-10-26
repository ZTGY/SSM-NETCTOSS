package com.lanou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dllo on 17/10/23.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/role_list")
    public String roleManager() {

        return "role/role_list";
    }

    @RequestMapping(value = "/admin_list")
    public String adminManager() {

        return "admin/admin_list";
    }

    @RequestMapping(value = "/bill_list")
    public String billList() {
        return "bill/bill_list";
    }
    @RequestMapping(value = "/report_list")
    public String reportLIst() {
        return "report/report_list";
    }
    @RequestMapping(value = "/user_info")
    public String userInfo() {
        return "user/user_info";
    }
    @RequestMapping(value = "/user_modify_pwd")
    public String userModifyPwd() {
        return "user/user_modify_pwd";
    }
}
