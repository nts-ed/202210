package com.northsea.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/12
 * @Description 定义User类的JavaBean
 */

@Data
@TableName(value = "t_user")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId     //告诉MP这是ID
    private String userId;
    private String userName;
    //@TableField(select = false)      //password不参与SELECT
    private String password;
    //private String role;
    @TableLogic         //告诉MP这个del_flg是逻辑删除标签，默认0为正常，1为删除
    private Integer delFlg;
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
    private String createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
    private String updateUser;

}
