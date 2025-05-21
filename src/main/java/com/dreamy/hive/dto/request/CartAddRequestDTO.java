package com.dreamy.hive.dto.request;

import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class CartAddRequestDTO {
    /**
     * 商品SKU ID
     */
    @NotNull(message = "商品SKU ID不能为空")
    private Long skuId;
    
    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空")
    @Min(value = 1, message = "商品数量不能小于1")
    private Integer quantity;
} 