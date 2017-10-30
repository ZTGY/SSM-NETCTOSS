package com.lanou.controller;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.service.AccountService;
import com.lanou.utils.AjaxResult;
import com.lanou.utils.IDCard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by dllo on 17/10/23.
 */
@Controller
public class AccountController {

    @Resource
    private AccountService accountService;

    //跳转到显示全部账户账号页面
    @RequestMapping(value = "/account_list")
    public String account() {
        return "account/account_list";
    }

    //获取分页信息,显示全部账务账号,高级查询显示全部账务账号
    @ResponseBody
    @RequestMapping(value = "/account_pageInfo")

    public PageInfo<Account> accountList(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, Account account, @RequestParam("flag") Integer flag) {

        if (flag == 0) {
            return accountService.findWithPageInfo(pageNo, pageSize);
        } else {
            if (account.getStatus().equals("全部")) {
                account.setStatus(null);
            }
            return accountService.findWithPageInfo1(pageNo, pageSize, account);
        }
    }

    //跳转到添加账户账号页面
    @RequestMapping(value = "/account_add")
    public String addAccount() {

        return "account/account_add";

    }

    //添加一条账号信息
    @ResponseBody
    @RequestMapping(value = "/account_add1")
    public AjaxResult addAccount1(Account account, @RequestParam("recommenderIdcardNo") String recommenderIdcardNo) {
//
//        if (!new IDCard().Verify(account.getIdcardNo())) {
//            return new AjaxResult(1);
//        }
//        if (!account.getLoginName().matches("^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?![0-9a-z]+$)(?![0-9A-Z]+$)(?![a-zA-Z]+$)" + "[0-9A-Za-z]{0,30}$")) {
//            return new AjaxResult(3);
//        }
//        if (!account.getLoginPasswd().matches("^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?![0-9a-z]+$)(?![0-9A-Z]+$)(?![a-zA-Z]+$)" + "[0-9A-Za-z]{6,10}$")) {
//            return new AjaxResult(4);
//        }
//
//        if (!(account.getTelephone().matches("^(0[0-9]{2,3}/-)?([2-9][0-9]{6,7})+(/-[0-9]{1,4})?$") || account.getTelephone().matches("^((/(/d{3}/))|(/d{3}/-))?13[0-9]/d{8}|15[89]/d{8}"))) {
//
//            return new AjaxResult(6);
//        }
//        if (new IDCard().Verify(recommenderIdcardNo)) {
//            return new AjaxResult(7);
//        }
//        if (account.getEmail().matches("^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")) {
//            return new AjaxResult(8);
//        }
//        if (account.getMailaddress().matches("^(?=.*?[\\u4E00-\\u9FA5])[\\d\\u4E00-\\u9FA5]+")) {
//            return new AjaxResult(9);
//        }
//        if (account.getZipcode().matches("[1-9]\\d{5}(?!\\d)")) {
//            return new AjaxResult(10);
//        }
//        if (account.getQq().matches("[1-9][0-9]{4,14}")) {
//            return new AjaxResult(11);
//        }

        account.setStatus("开通");

        //设置日期的创建格式
        account.setCreateDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        int i = accountService.insertSelective(account);

        if (i > 0) {
            return new AjaxResult(true);
        } else {
            return new AjaxResult(false);
        }

    }

    //开通一条账户
    @ResponseBody
    @RequestMapping(value = "/startAccount")
    public AjaxResult startAccount(@RequestParam("accountId") Integer accountId) {

        Account account = accountService.selectByPrimaryKey(accountId);

        account.setStatus("开通");

        account.setCreateDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        account.setPauseDate("0000-00-00");

        accountService.updateByPrimaryKeySelective(account);

        return new AjaxResult(account);
    }

    //暂停一条账户
    @ResponseBody
    @RequestMapping(value = "/pauseAccount")
    public AjaxResult PauseAccount(@RequestParam("accountId") Integer accountId) {

        Account account = accountService.selectByPrimaryKey(accountId);

        account.setStatus("暂停");

        account.setPauseDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        accountService.updateByPrimaryKeySelective(account);

        return new AjaxResult(account);
    }

    //删除一条账号信息,数据库中还存在,只是页面显示为删除
    @ResponseBody
    @RequestMapping(value = "/delAccount")
    public AjaxResult deleteAccount(@RequestParam("accountId") Integer accountId) {

        Account account = accountService.selectByPrimaryKey(accountId);

        account.setStatus("删除");

        account.setCloseDate((new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

        accountService.updateByPrimaryKeySelective(account);

        return new AjaxResult(account);
    }

    //查找当前所选的账户对象
    @ResponseBody
    @RequestMapping(value = "/account_modify")
    public AjaxResult accountModify(@RequestParam("accountId") Integer accountId, HttpServletRequest request) {

        Account account = accountService.selectByPrimaryKey(accountId);

        request.getSession().setAttribute("account", account);

        return new AjaxResult(account);
    }

    //跳转到修改页面
    @RequestMapping(value = "/account_modify1")
    public String accountModify1() {

        return "account/account_modify";

    }

    //找到当前所选的账户对象进行回显
    @ResponseBody
    @RequestMapping(value = "/account_modify2")
    public AjaxResult accountModify2(HttpServletRequest request) {

        Account account = (Account) request.getSession().getAttribute("account");

        return new AjaxResult(account);
    }

    //修改一条账户账号
    @RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
    public String updateAccount(Account account) {

        accountService.updateByPrimaryKeySelective(account);

        return "account/account_list";
    }

    //查找当前所选的账户对象
    @ResponseBody
    @RequestMapping(value = "/account_detail")
    public AjaxResult accountDetail1(@RequestParam("accountId") Integer accountId, HttpServletRequest request) {

        Account account = accountService.selectByPrimaryKey(accountId);

        request.getSession().setAttribute("account", account);

        return new AjaxResult(account);
    }


    //跳转到显示详情页面
    @RequestMapping(value = "/account_detail1")
    public String accountDetail1() {

        return "account/account_detail";
    }

    //找到当前所选资费对象用来回显
    @ResponseBody
    @RequestMapping(value = "/account_detail2")
    public AjaxResult feeDetail2(HttpServletRequest request) {

        Account account = (Account) request.getSession().getAttribute("account");

        return new AjaxResult(account);
    }
    //通过身份账号查询账务账号
    @ResponseBody
    @RequestMapping(value = "/searchAccountId")
    public AjaxResult searchAccountId(@RequestParam("idcardNo")String idcardNo) {

        Account account = accountService.selectByIdcardNo(idcardNo);

        return new AjaxResult(account);
    }
}
