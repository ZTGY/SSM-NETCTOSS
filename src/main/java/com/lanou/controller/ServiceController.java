package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.bean.Cost;
import com.lanou.bean.Services;
import com.lanou.service.AccountService;
import com.lanou.service.CostService;
import com.lanou.service.ServicesService;
import com.lanou.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dllo on 17/10/25.
 */
@Controller
public class ServiceController {
    @Resource
    private ServicesService servicesService;


    //跳转到显示全部业务账号页面
    @RequestMapping(value = "/service_list")
    public String service() {
        return "service/service_list";
    }

    //获取分页信息,显示全部业务账号
    @ResponseBody
    @RequestMapping(value = "/service_PageInfo")
    public PageInfo<Services> serviceList(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                                          @RequestParam(value="osUsername", required = false)String osUsername,@RequestParam(value="unixHost", required = false)String unixHost,
                                          @RequestParam(value="idcardNo", required = false)String idcardNo,@RequestParam(value = "status",required = false)String status, Integer flag) {

        if (flag == 0) {
            return servicesService.findAllServicesWithPageInfo(pageNo, pageSize);
        } else {
            if (status.equals("全部")) {

                status = null;

            }

            return servicesService.findAllServicesWithPageInfo1(pageNo, pageSize, osUsername,unixHost,idcardNo,status);

        }

    }

    //跳转到添加业务账号页面
    @RequestMapping(value = "/service_add")
    public String addService() {

        return "service/service_add";

    }

    //添加一条业务账号
    @ResponseBody
    @RequestMapping(value = "/service_add1")
    public AjaxResult addService1(Services services,@RequestParam("loginPasswd2")String loginPasswd2) {

        if(!services.getLoginPasswd().equals(loginPasswd2)){

            return new AjaxResult(2);
        }

        services.setStatus("开通");
        //设置日期的创建格式
        services.setCreateDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        int i = servicesService.insertSelective(services);

        if (i > 0) {
            return new AjaxResult(1);
        } else {
            return new AjaxResult(3);
        }
    }

    //开通一条业务
    @ResponseBody
    @RequestMapping(value = "/startService")
    public AjaxResult startService(@RequestParam("serviceId") Integer serviceId) {

        Services services = servicesService.selectByPrimaryKey(serviceId);

        services.setStatus("开通");

        services.setCreateDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        services.setPauseDate("0000-00-00");

        servicesService.updateByPrimaryKeySelective(services);

        return new AjaxResult(services);
    }

    //暂停一条业务
    @ResponseBody
    @RequestMapping(value = "/pauseService")
    public AjaxResult PauseService(@RequestParam("serviceId") Integer serviceId) {

        Services services = servicesService.selectByPrimaryKey(serviceId);

        services.setStatus("暂停");

        services.setPauseDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        servicesService.updateByPrimaryKeySelective(services);

        return new AjaxResult(services);
    }

    //删除一条业务账号信息,数据库中还存在,只是页面显示为删除
    @ResponseBody
    @RequestMapping(value = "/delService")
    public AjaxResult deleteService(@RequestParam("serviceId") Integer serviceId) {

        Services services = servicesService.selectByPrimaryKey(serviceId);

        services.setStatus("删除");

        services.setCloseDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        servicesService.updateByPrimaryKeySelective(services);

        return new AjaxResult(services);
    }

    //查找当前所选的业务对象
    @ResponseBody
    @RequestMapping(value = "/service_modify")
    public AjaxResult serviceModify(@RequestParam("serviceId") Integer serviceId, HttpServletRequest request) {

        Services services = servicesService.selectByPrimaryKey(serviceId);

        request.getSession().setAttribute("services", services);

        return new AjaxResult(services);
    }

    //跳转到修改页面
    @RequestMapping(value = "/service_modify1")
    public String serviceModify1() {

        return "service/service_modify";

    }

    //找到当前所选的业务对象进行回显
    @ResponseBody
    @RequestMapping(value = "/service_modify2")
    public AjaxResult serviceModify2(HttpServletRequest request) {

        Services services = (Services) request.getSession().getAttribute("services");

        return new AjaxResult(services);
    }

    //修改一条业务账号
    @RequestMapping(value = "/updateService", method = RequestMethod.POST)
    public String updateService(Services services) {

        servicesService.updateByPrimaryKeySelective(services);

        return "service/service_list";
    }

    //查找当前所选的业务对象
    @ResponseBody
    @RequestMapping(value = "/service_detail")
    public AjaxResult accountDetail1(@RequestParam("serviceId") Integer serviceId, HttpServletRequest request) {

        Services services = servicesService.selectByPrimaryKey(serviceId);

        request.getSession().setAttribute("services", services);

        return new AjaxResult(services);
    }


    //跳转到显示详情页面
    @RequestMapping(value = "/service_detail1")
    public String serviceDetail1() {

        return "service/service_detail";
    }

    //找到当前所选资费对象用来回显
    @ResponseBody
    @RequestMapping(value = "/service_detail2")
    public AjaxResult feeDetail2(HttpServletRequest request) {

        Services services = (Services) request.getSession().getAttribute("services");

        return new AjaxResult(services);
    }
}
