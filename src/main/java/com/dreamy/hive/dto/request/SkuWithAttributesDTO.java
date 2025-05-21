package com.dreamy.hive.dto.request;

import com.dreamy.hive.entity.Sku;
import com.dreamy.hive.entity.SkuAttribute;
import lombok.Data;

import java.util.List;

/**
 * SKU与属性组合DTO
 */
@Data
public class SkuWithAttributesDTO {
    /**
     * SKU信息
     */
    private Sku sku;
    
    /**
     * 属性列表
     */
    private List<SkuAttribute> attributes;
} 