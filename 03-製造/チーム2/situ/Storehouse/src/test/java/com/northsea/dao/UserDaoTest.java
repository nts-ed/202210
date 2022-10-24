package com.northsea.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.northsea.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/14
 * @Description
 */
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    void getUserByIdTest() {
        System.out.println(userDao.getUserById("00001"));
    }

    @Test
    void getUserByNameTest() {
        System.out.println(userDao.getUserByName("佐藤"));
    }

    @Test
    void getUserTest() {
        System.out.println(userDao.selectById("00001"));
    }

    @Test
    void insertUserTest() {
        User user = new User();
        user.setUserId("00004");
        user.setUserName("韩梅梅");
        user.setPassword("12345");
        user.setCreateUser("00001");
        user.setUpdateUser("00001");
        userDao.insert(user);
    }

    @Test
    void updateUserTest() {
        User user = new User();
        user.setUserId("00002");
        user.setCreateUser("00002");
        userDao.updateById(user);
    }

    @Test
    void deleteUserTest() {
        userDao.deleteById("00003");
    }

    // queryWrapper为查询条件
    @Test
    void getAllUserTest() {
        System.out.println(userDao.selectList(null));
    }

    @Test
    void getPageTest() {
        IPage<User> page = new Page<User>(1, 10);
        userDao.selectPage(page, null);
        System.out.println(page.getCurrent());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
        System.out.println(page.getSize());
    }

    //条件查询
    @Test
    void getByCondition() {
        String name = "李雷";
        IPage<User> page = new Page<User>(1, 10);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(name), User::getUserName, name);
        userDao.selectPage(page, lambdaQueryWrapper);
    }

}
