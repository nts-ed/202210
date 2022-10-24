package com.northsea.dto;

import com.northsea.entity.User;
import lombok.Data;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/18
 * @Description
 */
@Data
public class LoginDto {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String userId;
    private String password;


    public Object getUserId(LoginDto loginDto) {
        return userId;
    }

    public Object getUserId(User user) {
        return userId;
    }
}
