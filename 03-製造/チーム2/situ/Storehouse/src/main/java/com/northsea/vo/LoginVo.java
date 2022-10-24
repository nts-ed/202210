package com.northsea.vo;

import com.northsea.entity.User;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/18
 * @Description
 */
@Data
public class LoginVo implements Serializable {
    private String userId;
    private String token;
    private User user;
}
