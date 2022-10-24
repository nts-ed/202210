package com.northsea.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.northsea.entity.IO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/17
 * @Description
 */

@Mapper
public interface IODao extends BaseMapper<IO> {

    public void getIOInfoWithUpdateUser(@Param(value = "io") IO iO);

    public int saveIOInfo(IO iO);

    public IO getLastIOInfoRecordByIOID(String id);
}
