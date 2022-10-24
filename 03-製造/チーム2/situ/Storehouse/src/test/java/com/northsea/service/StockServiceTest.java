package com.northsea.service;

import com.northsea.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */
@SpringBootTest
public class StockServiceTest {

    @Autowired
    private StockService stockService;

    @Test
    public void getStockItemByIdTest(){
        System.out.println(stockService.getById("0003"));
    }

    @Test
    public void getAllStockItemsTest(){
        System.out.println(stockService.list());
    }

    @Test
    public void insertStockItemTest(){
        Stock stock = new Stock();
        stock.setId("0005");
        stock.setName("ぶどう");
        stock.setStockNum(0);
        stock.setUnitId(6);
        stock.setCreateUser("00001");
        stock.setUpdateUser("00001");
        stock.setRemarks("巨峰");
        stockService.save(stock);
    }

    @Test
    public void deleteStockItemTest(){
        stockService.removeById("0005");
    }

    @Test
    public void updateStockItemTest(){
        Stock stock = stockService.getById("0003");
        stock.setCreateUser("00001");
        stock.setUpdateUser("00001");
        stock.setRemarks("巨峰");
        stockService.updateById(stock);
    }
}
