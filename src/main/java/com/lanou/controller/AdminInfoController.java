package com.lanou.controller;

import com.lanou.bean.AdminInfo;
import com.lanou.service.AdminInfoService;
import com.lanou.utils.AjaxResult;
import com.lanou.utils.VerifyCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @author dllo
 * @date 17/10/20
 */
@Controller
public class AdminInfoController {

    @Resource
    private AdminInfoService adminInfoService;


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
    //
}
