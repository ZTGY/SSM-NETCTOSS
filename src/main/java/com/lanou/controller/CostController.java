package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.service.CostService;
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
import java.util.List;


/**
 * @author dllo
 * @date 17/10/20
 */
@Controller
public class CostController {

    @Resource
    private CostService costService;

    //跳转到显示全部资费页面
    @RequestMapping(value = "/fee_list")
    public String findAllCosts() {
        return "fee/fee_list";
    }


    //查找当前所选的资费对象
    @ResponseBody
    @RequestMapping(value = "/fee_modify")
    public AjaxResult feeModify(@RequestParam("costId") Integer costId, HttpServletRequest request) {

        Cost cost = costService.selectByPrimaryKey(costId);

        request.getSession().setAttribute("cost", cost);

        return new AjaxResult(cost);
    }

    //跳转到修改界面
    @RequestMapping(value = "/fee_modify1")
    public String feeModify1() {
        return "fee/fee_modify";
    }

    //找到当前所选资费对象用来回显
    @ResponseBody
    @RequestMapping(value = "/fee_modify2")
    public AjaxResult feeModify2(HttpServletRequest request) {

        Cost cost = (Cost) request.getSession().getAttribute("cost");

        return new AjaxResult(cost);
    }


    //修改一条数据
    @ResponseBody
    @RequestMapping(value = "/updateCost",method = RequestMethod.POST)
    public AjaxResult update(Cost cost) {

        int i = costService.updateByPrimaryKeySelective(cost);

        if (i > 0) {

            return new AjaxResult(true);

        } else {

            return new AjaxResult(false);
        }

    }

    //开通此条资费服务
    @ResponseBody
    @RequestMapping(value = "/startCost")
    public AjaxResult startCost(@RequestParam("costId") Integer costId) {

        Cost cost = costService.selectByPrimaryKey(costId);

        cost.setStatus("开通");

        cost.setStartime((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        costService.updateByPrimaryKeySelective(cost);

        return new AjaxResult(cost);
    }

    //跳转到添加页面
    @RequestMapping(value = "/fee_add")
    public String feeAdd() {
        return "fee/fee_add";
    }

    //添加一条资费
    @ResponseBody
    @RequestMapping(value = "/addCost")
    public AjaxResult addCost(Cost cost) {

        cost.setStatus("暂停");

        cost.setCreatime((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        int i = costService.insertSelective(cost);

        if (i > 0) {
            return new AjaxResult(true);
        } else {
            return new AjaxResult(false);
        }
    }

    //删除一条资费
    @ResponseBody
    @RequestMapping(value = "/delCost")
    public AjaxResult deleteCost(@RequestParam("costId") Integer costId) {

        costService.deleteByPrimaryKey(costId);

        return new AjaxResult(costId);
    }


    //查找当前所选的资费对象
    @ResponseBody
    @RequestMapping(value = "/fee_detail")
    public AjaxResult feeDetail(@RequestParam("costId") Integer costId, HttpServletRequest request) {

        Cost cost = costService.selectByPrimaryKey(costId);

        request.getSession().setAttribute("cost", cost);

        return new AjaxResult(cost);
    }


    //跳转到详情页面
    @RequestMapping(value = "/fee_detail1")
    public String feeDetail1() {
        return "fee/fee_detail";
    }


    //找到当前所选资费对象用来回显
    @ResponseBody
    @RequestMapping(value = "/fee_detail2")
    public AjaxResult feeDetail2(HttpServletRequest request) {

        Cost cost = (Cost) request.getSession().getAttribute("cost");

        return new AjaxResult(cost);
    }

    //获取分页信息,显示全部资费
    @ResponseBody
    @RequestMapping(value = "/pageInfo")

    public PageInfo<Cost> costList(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam("flag") Integer flag) {

        return costService.findWithPageInfo(pageNo, pageSize, flag);
    }

    //获取全部资费类型
    @ResponseBody
    @RequestMapping(value = "/findAllCosts")
    public AjaxResult costs() {

        List<Cost> allCosts = costService.findAllCosts();

        return new AjaxResult(allCosts);
    }

}
