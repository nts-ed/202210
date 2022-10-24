package com.northsea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.northsea.entity.User;

import java.util.List;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/14
 * @Description 业务层接口的操作，按实际用途命名，非业务按增删改查命名
 */

public interface UserService extends IService<User> {
    Boolean add(User user);

    Boolean delete(String id);

    Boolean update(User user);

    User getById(String id);

    List<User> getAll();

    IPage<User> getPage(int currentPage, int pageSize);

    IPage<User> getByCondition(int currentPage, int pageSize, String name);
}
