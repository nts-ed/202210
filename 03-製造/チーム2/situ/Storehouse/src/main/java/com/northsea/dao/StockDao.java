package com.northsea.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.northsea.entity.Stock;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */
@Mapper
public interface StockDao extends BaseMapper<Stock> {
}
