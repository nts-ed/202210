package com.northsea.controller;

import com.northsea.entity.Code;
import com.northsea.result.ResultBean;
import com.northsea.service.CodeService;
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
@RequestMapping("/codes")
public class CodeController {

    @Autowired
    private CodeService codeService;

    @GetMapping("{id}")
    public ResultBean<Code> getById(@PathVariable int id) {
        return new ResultBean<Code>(codeService.getById(id));
    }

    @GetMapping
    public ResultBean<Collection<Code>> getAllCodes() {
        return new ResultBean<Collection<Code>>(codeService.list());
    }

    @PostMapping("add")
    public ResultBean<Boolean> insertCode(@RequestBody Code code) {
        return new ResultBean<Boolean>(codeService.save(code));
    }
}
