package com.northsea.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.northsea.entity.IO;
import com.northsea.result.ResultBean;
import com.northsea.service.IOService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/17
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/stocks/io")
public class IOController {

    @Autowired
    private IOService ioService;

    //获取以货物id（stock_id）为参数的最新的出入库记录
    @GetMapping("/{stock_id}")
    public ResultBean<IO> getAllIOItemByStockId(@PathVariable String stock_id) {
        return new ResultBean<IO>(ioService.getLatestIOItemByStockId(stock_id));
    }

    //获取该货物的指定以 in_out_no 为参数的一条记录
    @GetMapping("/{stock_id}/iono/{in_out_no}")
    public ResultBean<IO> getIOItemByInOutNo(@PathVariable String stock_id, @PathVariable Integer in_out_no) {
        return new ResultBean<IO>(ioService.getIOItemByInOutNo(stock_id, in_out_no));
    }

    // 获取以货物id（stock_id）为url参数的所有出入库记录，并分页
    @GetMapping("/{stock_id}/{currentPage}/{pageSize}")
    public ResultBean<IPage<IO>> getPageWithStockId(@PathVariable String stock_id, @PathVariable int currentPage, @PathVariable int pageSize) {
        IPage<IO> page = ioService.getPageWithStockId(stock_id, currentPage, pageSize);
        if ((currentPage > page.getPages())) {
            page = ioService.getPageWithStockId(stock_id, (int) page.getPages(), pageSize);
        }
        return new ResultBean<IPage<IO>>(page);
    }

    // 获取以货物id（stock_id）为?=参数的所有出入库记录，并分页
    @GetMapping("/{currentPage}/{pageSize}")
    public ResultBean<IPage<IO>> getPageWithStockIdByParam(@PathVariable int currentPage, @PathVariable int pageSize, @RequestParam IO io) {
        IPage<IO> page = ioService.getPageWithStockIdByParam(currentPage, pageSize, io);
        if ((currentPage > page.getPages())) {
            page = ioService.getPageWithStockIdByParam((int) page.getPages(), pageSize, io);
        }
        return new ResultBean<IPage<IO>>(page);
    }

    // 搜索，获取以货物id（stock_id）为url参数的所有出入库记录，并分页
    @GetMapping("/{stock_id}/search/{currentPage}/{pageSize}/test/")
    public ResultBean<IPage<IO>> getAllBySearch(@PathVariable String stock_id, @PathVariable int currentPage, @PathVariable int pageSize, @RequestParam IO io) {
//        IPage<IO> page = ioService.getAllIOBySearch(stock_id, currentPage, pageSize, io);
//        if (currentPage > page.getPages()) {
//            page = ioService.getAllIOBySearch(stock_id, (int) page.getPages(), pageSize, io);
//        }
        return new ResultBean<IPage<IO>>();
    }

    // 搜索，获取以货物id（stock_id）为url参数的所有出入库记录，并分页
    @GetMapping("/{stock_id}/search/{currentPage}/{pageSize}")
    public ResultBean<IPage<IO>> getAllBySearchDate(@PathVariable String stock_id, @PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestParam(required = false) String[] date, @RequestParam(required = false) Integer type) {
        IPage<IO> page = ioService.getAllIOBySearch(stock_id, currentPage, pageSize, date, type);
        if (currentPage > page.getPages()) {
            page = ioService.getAllIOBySearch(stock_id, (int) page.getPages(), pageSize, date, type);
        }
        return new ResultBean<IPage<IO>>(page);
    }

    @PostMapping("/add")
    public ResultBean<Boolean> addIORecord(@RequestBody IO io) {
        return new ResultBean<Boolean>(ioService.saveIOInfo(io));
    }

    @PutMapping("/update")
    public ResultBean<Boolean> updateIORecord(@RequestBody IO io){
        return new ResultBean<Boolean>(ioService.updateById(io));
    }

    @DeleteMapping("/delete/{id}")
    public ResultBean<Boolean> deleteIORecord(@PathVariable String id ){
        return new ResultBean<Boolean>(ioService.removeById(id));
    }

}
