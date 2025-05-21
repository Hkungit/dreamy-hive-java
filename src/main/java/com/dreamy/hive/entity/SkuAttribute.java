package com.dreamy.hive.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("sku_attribute")
public class SkuAttribute {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long skuId;
    private Long attributeId;
    private String attributeValue;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @TableLogic
    private Integer deleted;
} 