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
public class CodeServiceTest {

    @Autowired
    private CodeService codeService;

    @Test
    void getById(){
        System.out.println(codeService.getById(1));
    }

    @Test
    void getAll(){
        System.out.println(codeService.list());
    }
}
