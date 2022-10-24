package com.northsea.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */
@Data
@TableName(value = "t_stock")
public class Stock implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId
    private String id;
    private String name;
    private Integer unitId;
    @Min(value = 0, message = "<0")
    private Integer stockNum;
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
