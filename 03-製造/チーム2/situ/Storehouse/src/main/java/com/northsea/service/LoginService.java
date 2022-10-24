package com.northsea.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.northsea.dto.LoginDto;
import com.northsea.entity.User;
import com.northsea.result.ResultBean;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/18
 * @Description
 */

public interface LoginService {
    public ResultBean<LoginDto> login(LoginDto loginDto);
}
