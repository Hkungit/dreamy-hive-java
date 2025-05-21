package com.dreamy.hive.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单详情响应DTO
 */
@Data
public class OrderDetailResponseDTO {
    /**
     * 订单详情ID
     */
    private Long id;
    
    /**
     * 订单ID
     */
    private Long orderId;
    
    /**
     * 商品ID
     */
    private Long spuId;
    
    /**
     * SKU ID
     */
    private Long skuId;
    
    /**
     * SKU编码
     */
    private String skuCode;
    
    /**
     * 商品名称
     */
    private String productName;
    
    /**
     * 商品规格
     */
    private String specs;
    
    /**
     * 商品价格
     */
    private BigDecimal price;
    
    /**
     * 商品原价
     */
    private BigDecimal originalPrice;
    
    /**
     * 购买数量
     */
    private Integer quantity;
    
    /**
     * 小计金额
     */
    private BigDecimal subtotal;
    
    /**
     * 商品图片
     */
    private String pic;
    
    /**
     * 创建时间
     */
    private Date createTime;
} 