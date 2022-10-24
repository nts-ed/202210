package com.northsea.dao;

import com.northsea.entity.IO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/17
 * @Description
 */
@SpringBootTest
public class IODaoTest {
    @Autowired
    private IODao ioDao;

    @Test
    public void getIOItemByIdTest(){
        System.out.println(ioDao.selectById("0001"));
    }

    @Test
    public void getAllIOItemsTest(){
        System.out.println(ioDao.selectList(null));
    }

    @Test
    public void insertIOItemTest(){
        IO io = new IO();
        io.setId("0001");
        io.setInOutNo(3);
        io.setIoNum(10);
        io.setIoType(1);
        ioDao.insert(io);
    }

    @Test
    public void deleteByIdTest(){
       // ioDao.deleteById("0001");
    }

    @Test
    public void getLastIOInfoRecordByIOIDTest(){
        System.out.println(ioDao.getLastIOInfoRecordByIOID("0001"));
    }
}
