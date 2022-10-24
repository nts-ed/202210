package com.northsea.controller;

import com.northsea.entity.Unit;
import com.northsea.result.ResultBean;
import com.northsea.service.UnitService;
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
@RequestMapping("/units")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @GetMapping("{id}")
    public ResultBean<Unit> getById(@PathVariable int id) {
        return new ResultBean<Unit>(unitService.getById(id));
    }

    @GetMapping
    public ResultBean<Collection<Unit>> getAll(){
        return new ResultBean<Collection<Unit>>(unitService.list());
    }

    @PostMapping("/add")
    public ResultBean<Boolean> add(@RequestBody Unit unit){
        return new ResultBean<Boolean>(unitService.save(unit));
    }
}
