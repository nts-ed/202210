package com.northsea.service;

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
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void getByIdTest() {
        System.out.println(userService.getById("00001"));
    }

//    @Test
//    void getUserByNameTest(){
//        System.out.println(userService.getUserByName("佐藤"));
//    }

    @Test
    void getUserTest() {
        System.out.println(userService.getById("00001"));
    }

    @Test
    void insertUserTest() {
        User user = new User();
        user.setUserId("00007");
        user.setUserName("李雷");
        user.setPassword("12345");
        user.setCreateUser("00001");
        user.setUpdateUser("00001");
        userService.save(user);
    }

    @Test
    void updateUserTest() {
        User user = userService.getById("00007");
        user.setCreateUser("00002");
        userService.update(user);
    }

    @Test
    void deleteUserTest() {
        userService.delete("00003");
    }

    // queryWrapper为查询条件
    @Test
    void getAllUserTest() {
        System.out.println(userService.getAll());
    }

    @Test
    void getPageTest() {
        IPage<User> page = userService.getPage(2, 5);
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
        int currentPage = 1;
        int pageSize = 10;
        IPage<User> page = userService.getByCondition(currentPage, pageSize, name);
        System.out.println(page.getCurrent());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
        System.out.println(page.getSize());
    }

}
