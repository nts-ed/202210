package com.northsea.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/17
 * @Description
 */
@Data
@TableName(value = "t_stock_io")
public class IO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId
    private String id;
    @TableField()
    private Integer inOutNo;
    private Integer ioType;
    private Integer ioNum;
    private String remarks;
    @TableLogic         //告诉MP这个del_flg是逻辑删除标签，默认0为正常，1为删除
    private Integer delFlg;
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
    private String createUser;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
    private String updateUser;
    @Version
    private Integer version;

}
