package com.northsea.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.northsea.entity.Stock;
import com.northsea.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */
@SpringBootTest
public class StockDaoTest {

    @Autowired
    private StockDao stockDao;

    @Test
    public void getByIdTest(){
        System.out.println(stockDao.selectById("0001"));
    }

    @Test
    public void getAllTest(){
        System.out.println(stockDao.selectList(null));
    }

    @Test
    public void insertTest(){
        Stock stock = new Stock();
        stock.setId("0004");
        stock.setName("ぶどう");
        stock.setStockNum(0);
        stock.setUnitId(6);
        stock.setCreateUser("00001");
        stock.setUpdateUser("00001");
        stockDao.insert(stock);
    }

    @Test
    public  void updateByIdTest(){
        Stock stock = new Stock();
        stock.setId("0003");
        stock.setRemarks("普通なぶどう");
        stockDao.updateById(stock);
    }

    @Test
    public void deleteByIdTest(){
        stockDao.deleteById("0004");
    }

    @Test
    void getPageTest() {
        IPage<Stock> page = new Page<Stock>(1, 10);
        stockDao.selectPage(page, null);
        System.out.println(page.getCurrent());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
        System.out.println(page.getSize());
    }

    //条件查询
    @Test
    void getByCondition() {
        String name = "";
        IPage<Stock> page = new Page<Stock>(1, 10);
        LambdaQueryWrapper<Stock> lambdaQueryWrapper = new LambdaQueryWrapper<Stock>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(name), Stock::getName, name);
        stockDao.selectPage(page, lambdaQueryWrapper);
    }

}
