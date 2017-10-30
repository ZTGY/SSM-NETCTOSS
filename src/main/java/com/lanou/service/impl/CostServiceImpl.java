package com.lanou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lanou.bean.Cost;
import com.lanou.mapper.CostMapper;
import com.lanou.service.CostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dllo
 * @date 17/10/20
 */
@Service
public class CostServiceImpl implements CostService {

    @Resource
    private CostMapper costMapper;

    @Override
    public int deleteByPrimaryKey(Integer costId) {
        return costMapper.deleteByPrimaryKey(costId);
    }

    @Override
    public int insert(Cost record) {
        return costMapper.insert(record);
    }

    @Override
    public int insertSelective(Cost record) {
        return costMapper.insertSelective(record);
    }

    @Override
    public Cost selectByPrimaryKey(Integer costId) {
        return costMapper.selectByPrimaryKey(costId);
    }

    @Override
    public int updateByPrimaryKeySelective(Cost record) {
        return costMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Cost record) {
        return costMapper.updateByPrimaryKey(record);
    }


    //获取分页信息
    @Override
    public PageInfo<Cost> findWithPageInfo(Integer pageNo, Integer pageSize, Integer flag) {

        //目标PageInfo对象
        PageInfo<Cost> pageInfo = queryCostByPage(pageNo, pageSize, flag);

        return pageInfo;
    }

    @Override
    public List<Cost> findAllCosts() {
        return costMapper.findAllCosts();
    }

    @Override
    public Cost selectByCostType(String costType) {
        return costMapper.selectByCostType(costType);
    }


    public PageInfo<Cost> queryCostByPage(Integer pageNo, Integer pageSize, Integer flag) {

        //判断参数的合法性

        pageNo = pageNo == null ? 1 : pageNo;

        pageSize = pageSize == null ? 8 : pageSize;

        PageHelper.startPage(pageNo, pageSize);


        if (flag == 0) {

            //获取全部的资费
            List<Cost> costList = costMapper.findAll();

            //使用PageInfo对结果进行包装
            PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);

            return pageInfo;
        }
        if (flag == 1) {
            //获取全部的资费
            List<Cost> costList = costMapper.findAllByCostAsc();

            //使用PageInfo对结果进行包装
            PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);

            return pageInfo;
        }
        if (flag == 2) {
            //获取全部的资费
            List<Cost> costList = costMapper.findAllByCostDesc();

            //使用PageInfo对结果进行包装
            PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);

            return pageInfo;
        }
        if (flag == 3) {
            //获取全部的资费
            List<Cost> costList = costMapper.findAllByTimeAsc();

            //使用PageInfo对结果进行包装
            PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);

            return pageInfo;
        }
        if (flag == 4) {
            //获取全部的资费
            List<Cost> costList = costMapper.findAllByTimeDesc();

            //使用PageInfo对结果进行包装
            PageInfo<Cost> pageInfo = new PageInfo<Cost>(costList);

            return pageInfo;
        }
        return null;
    }
}
