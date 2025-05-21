package com.dreamy.hive.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 购物车项响应DTO
 */
@Data
public class CartItemResponseDTO {
    /**
     * 购物车ID
     */
    private Long id;
    
    /**
     * 用户ID
     */
    private Long userId;
    
    /**
     * SKU ID
     */
    private Long skuId;
    
    /**
     * SPU ID
     */
    private Long spuId;
    
    /**
     * 商品数量
     */
    private Integer quantity;
    
    /**
     * 是否选中（0-未选中，1-选中）
     */
    private Integer checked;
    
    /**
     * 商品名称
     */
    private String productName;
    
    /**
     * SKU名称
     */
    private String skuName;
    
    /**
     * 商品图片
     */
    private String pic;
    
    /**
     * 商品价格
     */
    private BigDecimal price;
    
    /**
     * 商品规格，JSON格式
     */
    private String specs;
    
    /**
     * 小计金额（价格 * 数量）
     */
    private BigDecimal subtotal;
    
    /**
     * 库存数量
     */
    private Integer stock;
} 