package com.northsea.controller;

import com.northsea.dto.LoginDto;
import com.northsea.result.ResultBean;
import com.northsea.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/17
 * @Description
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    @CrossOrigin
    public ResultBean<LoginDto> login(@RequestBody LoginDto loginDto){
        return loginService.login(loginDto);
    }
}
