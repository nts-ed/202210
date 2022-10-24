package com.northsea.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.northsea.entity.IO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/17
 * @Description
 */
@SpringBootTest
public class IOServiceTest {

    @Autowired
    private IOService ioService;

    @Test
    public void getIOItemByIOIdTest(){
        int inOutNo = 2;
        LambdaQueryWrapper<IO> lambdaQueryWrapper = new LambdaQueryWrapper<IO>();
        lambdaQueryWrapper.eq(IO::getInOutNo, inOutNo);
        ioService.getOne(lambdaQueryWrapper);
    }

    @Test
    public void getAllIOItemByGoodsIdTest(){
        String id = "0001";
        LambdaQueryWrapper<IO> lambdaQueryWrapper = new LambdaQueryWrapper<IO>();
        lambdaQueryWrapper.eq(IO::getId, id);
        ioService.list(lambdaQueryWrapper);
    }

    @Test
    public void getIOItemByInOutNoTest() {
        String id = "0001";
        ioService.getLatestIOItemByStockId(id);
    }
}
