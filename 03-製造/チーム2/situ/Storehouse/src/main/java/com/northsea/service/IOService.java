package com.northsea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.northsea.entity.IO;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/17
 * @Description
 */

public interface IOService extends IService<IO> {
    IPage<IO> getPage(int currentPage, int pageSize);

    IPage<IO> getPageWithStockId(String stock_id, int currentPage, int pageSize);

    IPage<IO> getPageWithStockIdByParam(int currentPage, int pageSize, IO iO);

    IPage<IO> getAllIOBySearch(String stock_id, Integer currentPage, Integer pageSize, String[] date, Integer ioType);

    IPage<IO> getIOInfoWithUpdateUser(String stock_id, int currentPage, int pageSize, IO iO);

    Boolean saveIOInfo(IO iO);

    IO getIOItemByInOutNo(String stock_id, Integer in_out_no);

    IO getLatestIOItemByStockId(String stock_id);
}
