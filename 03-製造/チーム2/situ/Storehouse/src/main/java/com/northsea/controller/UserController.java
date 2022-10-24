package com.northsea.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.northsea.entity.User;
import com.northsea.result.ResultBean;
import com.northsea.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/14
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResultBean<List<User>> getAll(){
        return new ResultBean<List<User>>(userService.list());
    }

    @GetMapping("{id}")
    public ResultBean<User> getById(@PathVariable("id") String id) {
        return new ResultBean<User>(userService.getById(id));
    }

    @PostMapping
    public ResultBean<Boolean> save(@RequestBody User user){
        return new ResultBean<Boolean>(userService.save(user));

    }

    @PutMapping
    public ResultBean<Boolean> update(@RequestBody User user){
        return new ResultBean<Boolean>(userService.update(user));
    }

    @DeleteMapping("{id}")
    public ResultBean<Boolean> delete(@PathVariable("id") String id){
        return new ResultBean<Boolean>(userService.delete(id));
    }

    @GetMapping("{currentPage}/{pageSize}")
    public ResultBean<IPage<User>> getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        IPage<User> page = userService.getPage(currentPage, pageSize);
        if (currentPage > page.getPages()){
            page = userService.getPage((int) page.getPages(), pageSize);
        }
        return new ResultBean<IPage<User>>(page);
    }
}
