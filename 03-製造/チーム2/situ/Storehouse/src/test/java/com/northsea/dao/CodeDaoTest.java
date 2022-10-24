package com.northsea.dao;

import com.northsea.entity.Code;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */
@SpringBootTest
public class CodeDaoTest {

    @Autowired
    private CodeDao codeDao;

    @Test
    void getByIdTest(){
        System.out.println(codeDao.selectById(1));
    }

    @Test
    void getAllTest(){
        System.out.println(codeDao.selectList(null));
    }

    @Test
    public void addCodeTest(){
        Code code = new Code();
        code.setCodeId(7);
        code.setName("Test");
        code.setCreateUser("00001");
        codeDao.insert(code);
    }

    @Test
    public void updateCodeTest(){
        Code code = codeDao.selectById(7);
        code.setName("Test7");
        codeDao.updateById(code);
    }
}
