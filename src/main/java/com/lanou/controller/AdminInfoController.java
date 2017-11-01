package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.AdminInfo;
import com.lanou.bean.ModuleInfo;
import com.lanou.bean.RoleInfo;
import com.lanou.service.AdminInfoService;
import com.lanou.utils.AjaxResult;
import com.lanou.utils.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author dllo
 * @date 17/10/20
 */
@Controller
public class AdminInfoController {

    @Resource
    private AdminInfoService adminInfoService;

    @RequestMapping(value = "/admin_list")
    public String adminManager() {

        return "admin/admin_list";
    }

    //用来跳转到登录页面
    @RequestMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    //验证码的刷新与判定
    @ResponseBody
    @RequestMapping(value = "/verifyCode")
    public AjaxResult verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //用于处理真正的返回结果
        VerifyCode verifyCode = new VerifyCode();//创建工具类对象
        BufferedImage image = verifyCode.getImage();//验证码工具生成图片对象
        //保存生成的验证码的字符串对象
        //将验证码保存到session中
        request.getSession().setAttribute("verifyCode", verifyCode.getText());
        //获得response对象的输出流用于图像的写入
        ServletOutputStream os = response.getOutputStream();
        VerifyCode.output(image, os);//将图片映射到输出流中
        return new AjaxResult();
    }

    @ResponseBody
    @RequestMapping(value = "/login1")
    public AjaxResult login(AdminInfo adminUser, @RequestParam("verifyCode") String verifyCode, HttpServletRequest request) {
        if ("".equals(adminUser.getName().trim()) &&
                "".equals(adminUser.getPassword().trim()) ||
                adminInfoService.findByName(adminUser) == null) {
            return new AjaxResult(1);
        }

        if (!adminInfoService.findByName(adminUser).getPassword().equals(adminUser.getPassword())) {
            return new AjaxResult(2);
        }
        //获取生成验证码图片时保存的验证字符串对象
        String srcVerifyCode = (String) request.getSession().getAttribute("verifyCode");
        //表单提交的验证码与session保存的进行比较,如果不一致,加入错误信息.
        if (!verifyCode.equalsIgnoreCase(srcVerifyCode)) {
            return new AjaxResult(3);
        }

        request.getSession().setAttribute("adminUser", adminUser);

        return new AjaxResult(4);
    }

    //用来跳转到导航页面
    @RequestMapping(value = "/index")
    public String indexPage() {
        return "index";
    }

    //用来显示当前用户
    @ResponseBody
    @RequestMapping(value = "/showName")
    public AjaxResult showName(HttpServletRequest request) {

        AdminInfo adminUser = (AdminInfo) request.getSession().getAttribute("adminUser");

        return new AjaxResult(adminUser);
    }

    //退出当前用户
    @RequestMapping(value = "/quit")
    public String quitName(HttpServletRequest request) {

        request.getSession().removeAttribute("adminUser");

        return "login";
    }

    //显示全部信息并分页
    @ResponseBody
    @RequestMapping(value = "/admin_pageInfo")
    public PageInfo<AdminInfo> adminInfoList(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                                             @RequestParam(value = "moduleId", required = false) String moduleId, @RequestParam(value = "name", required = false) String name,
                                             @RequestParam("flag") Integer flag) {
        if (flag == 0) {
            return adminInfoService.findAllAdminInfoWithPageInfo(pageNo, pageSize);
        } else {

            Integer moduleId1 = Integer.valueOf(moduleId);

            return adminInfoService.findAllBySearch(pageNo, pageSize, moduleId1, name);
        }
    }

    //跳转到添加角色页面
    @RequestMapping(value = "/admin_add")
    public String roleAdd() {

        return "admin/admin_add";

    }

    //添加管理员
    @ResponseBody
    @RequestMapping(value = "/admin_add1", method = RequestMethod.POST)
    public AjaxResult addAdmin(AdminInfo adminInfo, @RequestParam(value = "roleIds", required = false) Integer[] roleIds) {

        adminInfo.setEnrolldate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        int i = adminInfoService.insertSelective(adminInfo);

        for (Integer roleId : roleIds) {

            adminInfoService.insertIntoAdminAndRole(adminInfo.getAdminId(), roleId);
        }

        if (i > 0) {

            return new AjaxResult(true);

        } else {

            return new AjaxResult(false);
        }

    }


    //查找当前所选的管理员对象
    @ResponseBody
    @RequestMapping(value = "/admin_modify")
    public AjaxResult adminModify(@RequestParam("adminId") Integer adminId, HttpServletRequest request) {

        AdminInfo adminInfo = adminInfoService.selectByPrimaryKey(adminId);

        request.getSession().setAttribute("adminInfo", adminInfo);

        return new AjaxResult(adminInfo);
    }

    //跳转到修改界面
    @RequestMapping(value = "/admin_modify1")
    public String adminModify1() {

        return "admin/admin_modify";

    }

    //找到当前所选管理员对象用来回显
    @ResponseBody
    @RequestMapping(value = "/admin_modify2")
    public AjaxResult feeModify2(HttpServletRequest request) {

        AdminInfo adminInfo = (AdminInfo) request.getSession().getAttribute("adminInfo");

        return new AjaxResult(adminInfo);
    }

    //修改一条数据
    @RequestMapping(value = "/updateAdmin", method = RequestMethod.POST)
    public String update(AdminInfo adminInfo, @RequestParam(value = "roleIds", required = false) Integer[] roleIds, HttpServletRequest request) {

        AdminInfo adminInfo1 = (AdminInfo) request.getSession().getAttribute("adminInfo");

        adminInfo.setAdminId(adminInfo1.getAdminId());

        adminInfoService.updateByPrimaryKeySelective(adminInfo);

        adminInfoService.deleteAdminAndRoleByAdminId(adminInfo1.getAdminId());

        for (Integer roleId : roleIds) {

            adminInfoService.insertIntoAdminAndRole(adminInfo.getAdminId(), roleId);
        }

        return "admin/admin_list";
    }

    //删除一条资费
    @ResponseBody
    @RequestMapping(value = "/delAdmin")
    public AjaxResult deleteCost(@RequestParam("adminId") Integer adminId) {

        adminInfoService.deleteByPrimaryKey(adminId);

        adminInfoService.deleteAdminAndRoleByAdminId(adminId);

        return new AjaxResult(adminId);
    }
}
