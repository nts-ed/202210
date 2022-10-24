package com.northsea.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.northsea.dao.CodeDao;
import com.northsea.entity.Code;
import com.northsea.service.CodeService;
import org.springframework.stereotype.Service;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */
@Service
public class CodeServiceImpl extends ServiceImpl<CodeDao, Code> implements CodeService {
}
