package com.northsea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.northsea.dao.UserDao;
import com.northsea.entity.User;
import com.northsea.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author BenSitu
 * @CreateDate 2022/10/14
 * @Description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Boolean add(User user) {
        return userDao.insert(user) > 0;
    }

    @Override
    public Boolean delete(String id) {
        return userDao.deleteById(id) > 0;
    }

    @Override
    public Boolean update(User user) {
        return userDao.updateById(user) > 0;
    }

    @Override
    public User getById(String id) {
        return userDao.selectById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.selectList(null);
    }

    @Override
    public IPage<User> getPage(int currentPage, int pageSize) {
        IPage<User> page = new Page<User>(currentPage, pageSize);
        userDao.selectPage(page, null);
        return page;
    }

    @Override
    public IPage<User> getByCondition(int currentPage, int pageSize, String name) {
        IPage<User> page = new Page<User>(currentPage, pageSize);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>();
        lambdaQueryWrapper.like(Strings.isNotEmpty(name), User::getUserName, name);
        userDao.selectPage(page, lambdaQueryWrapper);
        return page;
    }
}
