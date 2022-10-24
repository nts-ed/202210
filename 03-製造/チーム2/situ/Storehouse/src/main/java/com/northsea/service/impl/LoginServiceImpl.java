package com.northsea.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.northsea.dao.LoginDao;
import com.northsea.dao.UserDao;
import com.northsea.dto.LoginDto;
import com.northsea.entity.User;
import com.northsea.result.ResultBean;
import com.northsea.service.LoginService;
import com.northsea.vo.LoginVo;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/18
 * @Description
 */
@Service
public class LoginServiceImpl extends ServiceImpl<UserDao, User> implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public ResultBean login(LoginDto loginDto) {
        if (ObjectUtils.isEmpty(loginDto.getUserId())){
            return new ResultBean<LoginDto>(400, "ユーザーIDを入力してください", loginDto);
        }
        if (ObjectUtils.isEmpty(loginDto.getPassword())){
            return new ResultBean<LoginDto>(400, "パスワードを入力してください", loginDto);
        }
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>();
        lambdaQueryWrapper.eq(Strings.isNotEmpty(loginDto.getUserId()), loginDto::getUserId, loginDto.getUserId());
        User user = loginDao.selectOne(lambdaQueryWrapper);

//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("user_id", loginDto.getUserId());
//        User user = userDao.selectOne(wrapper);


        if ((user!=null && user.getPassword().equals(loginDto.getPassword()))){
            LoginVo loginVo = new LoginVo();
            loginVo.setUserId(user.getUserId());

            loginVo.setToken(UUID.randomUUID().toString());
            loginVo.setUser(user);
            return new ResultBean(200, "", user);

        }
        return new ResultBean(401, "ログインできません","");
    }
}
