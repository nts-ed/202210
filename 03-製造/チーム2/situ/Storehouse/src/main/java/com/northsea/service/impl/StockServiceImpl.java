package com.northsea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.northsea.dao.StockDao;
import com.northsea.entity.Stock;
import com.northsea.service.StockService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockDao, Stock> implements StockService {

    @Autowired
    private StockDao stockDao;

    @Transactional
    @Override
    public IPage<Stock> getPage(int currentPage, int pageSize) {
        IPage<Stock> page = new Page<Stock>(currentPage, pageSize);
        stockDao.selectPage(page, null);
        return page;
    }

    @Transactional
    @Override
    public IPage<Stock> getAllIOBySearchDate(Integer currentPage, Integer pageSize, String name, String[] date, Integer stockType) {
        LambdaQueryWrapper<Stock> lambdaQueryWrapper = new LambdaQueryWrapper<Stock>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(name), Stock::getName, name);
        if (date != null){
            lambdaQueryWrapper.between( Stock::getUpdateDate, date[0], date[1] );
        }
        if (stockType != null){
            if (stockType == 1){
                lambdaQueryWrapper.ge(Strings.isNotEmpty(Integer.toString(stockType)), Stock::getStockNum, stockType);
            } else if (stockType == 0){
                lambdaQueryWrapper.eq(Strings.isNotEmpty(Integer.toString(stockType)),Stock::getStockNum, stockType);
            }
        }
        Page<Stock> page = new Page<>(currentPage, pageSize);
        return stockDao.selectPage(page, lambdaQueryWrapper);
    }
}
