package com.northsea.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.northsea.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/18
 * @Description
 */
@Mapper
public interface LoginDao extends BaseMapper<User> {
}
