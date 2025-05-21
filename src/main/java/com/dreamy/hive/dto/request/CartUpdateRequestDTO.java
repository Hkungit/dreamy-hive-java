package com.dreamy.hive.dto.request;

import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class CartUpdateRequestDTO {
    /**
     * 购物车ID
     */
    @NotNull(message = "购物车ID不能为空")
    private Long id;
    
    /**
     * 商品数量
     */
    @NotNull(message = "商品数量不能为空")
    @Min(value = 1, message = "商品数量不能小于1")
    private Integer quantity;
} 