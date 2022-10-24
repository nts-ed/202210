package com.northsea.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.northsea.dto.LoginDto;
import com.northsea.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/12
 * @Description
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    //MyBatis 原始方法，用注释写SQL
    @Select("SELECT * FROM t_user WHERE user_id = #{id}")
    User getUserById(@Param("id") String id);

    @Select("SELECT * FROM t_user WHERE user_name like CONCAT('%',#{name},'%')")
    User getUserByName(@Param("name") String name);

}
