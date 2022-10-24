package com.northsea.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.northsea.entity.Stock;
import com.northsea.result.ResultBean;
import com.northsea.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/{id}")
    public ResultBean<Stock> getById(@PathVariable String id){
        return new ResultBean<Stock>(stockService.getById(id));
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public ResultBean<IPage<Stock>> getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        IPage<Stock> page = stockService.getPage(currentPage, pageSize);
        if (currentPage > page.getPages()){
            page = stockService.getPage((int) page.getPages(), pageSize);
        }
        return new ResultBean<IPage<Stock>>(page);
    }

    @GetMapping("/search/{currentPage}/{pageSize}")
    public ResultBean<IPage<Stock>> getAllBySearchDate(@PathVariable Integer currentPage, @PathVariable Integer pageSize, @RequestParam(required = false) String name, @RequestParam(required = false) String[] date, @RequestParam(required = false) Integer type) {
        IPage<Stock> page = stockService.getAllIOBySearchDate(currentPage, pageSize, name, date, type);
        if (currentPage > page.getPages()) {
            page = stockService.getAllIOBySearchDate((int) page.getPages(), pageSize, name, date, type);
        }
        return new ResultBean<IPage<Stock>>(page);
    }

    @DeleteMapping("/delete/{id}")
    public ResultBean<Boolean> deleteStockItem(@PathVariable String id ){
        return new ResultBean<Boolean>(stockService.removeById(id));
    }

    @PostMapping
    public ResultBean<Boolean> addStockItem(@RequestBody Stock stock){
        return new ResultBean<Boolean>(stockService.save(stock));
    }

    @PutMapping("/updates")
    public ResultBean<Boolean> updateExistingStockItem(@RequestBody Stock stock) {
        return new ResultBean<Boolean>(stockService.updateById(stock));
    }
}
