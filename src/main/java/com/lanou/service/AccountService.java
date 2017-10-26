package com.lanou.service;

import com.github.pagehelper.PageInfo;
import com.lanou.bean.Account;

/**
 * Created by dllo on 17/10/23.
 */
public interface AccountService {
    int deleteByPrimaryKey(Integer accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    PageInfo<Account> findWithPageInfo(Integer pageNo, Integer pageSize);

    PageInfo<Account> findWithPageInfo1(Integer pageNo, Integer pageSize, Account account);
}
