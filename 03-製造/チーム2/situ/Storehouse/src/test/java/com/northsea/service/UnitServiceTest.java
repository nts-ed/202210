package com.northsea.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */
@SpringBootTest
public class UnitServiceTest {

    @Autowired
    private UnitService unitService;

    @Test
    void getByIdTest(){
        System.out.println(unitService.getById(1));
    }

    @Test
    void getAllTest(){
        System.out.println(unitService.list());
    }
}
