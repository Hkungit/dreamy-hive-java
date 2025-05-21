package com.dreamy.hive.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("spu")
public class Spu {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String subTitle;
    private Long categoryId;
    private String description;
    private String detailHtml;
    private Integer status;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    private Long createBy;
    private Long updateBy;
    @TableLogic
    private Integer deleted;
}