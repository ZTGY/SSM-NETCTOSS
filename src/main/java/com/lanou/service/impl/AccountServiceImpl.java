package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;
import com.lanou.mapper.AccountMapper;
import com.lanou.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/23.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public int deleteByPrimaryKey(Integer accountId) {
        return accountMapper.deleteByPrimaryKey(accountId);
    }

    @Override
    public int insert(Account record) {
        return accountMapper.insert(record);
    }

    @Override
    public int insertSelective(Account record) {
        return accountMapper.insertSelective(record);
    }

    @Override
    public Account selectByPrimaryKey(Integer accountId) {
        return accountMapper.selectByPrimaryKey(accountId);
    }

    @Override
    public int updateByPrimaryKeySelective(Account record) {
        return accountMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Account record) {
        return accountMapper.updateByPrimaryKey(record);
    }

    //用PageInfo进行分页
    @Override
    public PageInfo<Account> findWithPageInfo(Integer pageNo, Integer pageSize) {

        //判断参数的合法性

        pageNo = pageNo == null ? 1 : pageNo;

        pageSize = pageSize == null ? 8 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        //获取全部的账务账号

        List<Account> accountList = accountMapper.findAll();

        //用PageInfo对结果进行包装
        PageInfo<Account> pageInfo = new PageInfo<Account>(accountList);

        return pageInfo;
    }

    @Override
    public PageInfo<Account> findWithPageInfo1(Integer pageNo, Integer pageSize, String idcardNo, String realName, String loginName, String status) {

        System.out.println(accountMapper.findAllBySearch(idcardNo,realName,loginName,status));

        pageNo = pageNo == null ? 1 : pageNo;

        pageSize = pageSize == null ? 8 : pageSize;

        PageHelper.startPage(pageNo, pageSize);

        //获取全部的账务账号

        List<Account> accountList = accountMapper.findAllBySearch(idcardNo,realName,loginName,status);

        //用PageInfo对结果进行包装
        PageInfo<Account> pageInfo = new PageInfo<Account>(accountList);

        return pageInfo;
    }

}
